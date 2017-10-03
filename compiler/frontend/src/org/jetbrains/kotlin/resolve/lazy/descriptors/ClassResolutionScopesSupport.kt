/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.resolve.lazy.descriptors

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor
import org.jetbrains.kotlin.psi.KtParameter
import org.jetbrains.kotlin.resolve.descriptorUtil.getAllSuperclassesWithoutAny
import org.jetbrains.kotlin.resolve.scopes.*
import org.jetbrains.kotlin.resolve.scopes.utils.ThrowingLexicalScope
import org.jetbrains.kotlin.storage.StorageManager
import org.jetbrains.kotlin.utils.addIfNotNull
import java.util.*

class ClassResolutionScopesSupport(
        private val classDescriptor: ClassDescriptor,
        storageManager: StorageManager,
        private val getOuterScope: () -> LexicalScope
) {
    private fun log(s: String) {
        println("[$classDescriptor] $s")
    }

    private fun scopeWithGenerics(parent: LexicalScope): LexicalScopeImpl {
        return LexicalScopeImpl(parent, classDescriptor, false, null, LexicalScopeKind.CLASS_HEADER) {
            classDescriptor.declaredTypeParameters.forEach { addClassifierDescriptor(it) }
        }
    }

    val scopeForClassHeaderResolution: () -> LexicalScope = storageManager.createLazyValue {
        log("Creating scope for class header resolution")
        log ("Getting outer scope")
        val parent = getOuterScope()
        log("Got outerScope=$parent, creating scope with generics")
        val scopeWithGenerics = scopeWithGenerics(parent)
        log ("Got scope with generics $scopeWithGenerics")
        scopeWithGenerics
    }

    val scopeForConstructorHeaderResolution: () -> LexicalScope = storageManager.createLazyValue {
        scopeWithGenerics(inheritanceScopeWithMe())
    }

    private val inheritanceScopeWithoutMe: () -> LexicalScope = storageManager.createLazyValue(onRecursion = createThrowingLexicalScope) {
        log("Creating inhertiance scope without me")
        log("Starting resolving superclasses")
        val superclasses = classDescriptor.getAllSuperclassesWithoutAny().asReversed()
        log("Finished resolving superclasses")
        val outerScope = getOuterScope()
        log("Initial outerscope = $outerScope")
        log("Starting folding inheritanceScopes")
        val fold = superclasses.fold(outerScope) { scope, currentClass ->
            log("parent=$scope, currentClass=$currentClass")
            val createInheritanceScope = createInheritanceScope(parent = scope, ownerDescriptor = classDescriptor, classDescriptor = currentClass)
            log("created inheritance scope $createInheritanceScope")
            createInheritanceScope
        }
        log("Folded inheritance scopes")
        fold
    }

    private val inheritanceScopeWithMe: () -> LexicalScope = storageManager.createLazyValue(onRecursion = createThrowingLexicalScope) {
        log("Creating inheritance scope with me")
        val createInheritanceScope = createInheritanceScope(parent = inheritanceScopeWithoutMe(), ownerDescriptor = classDescriptor, classDescriptor = classDescriptor)
        log("Created inheritance scope with me")
        createInheritanceScope
    }

    val scopeForCompanionObjectHeaderResolution: () -> LexicalScope = storageManager.createLazyValue(onRecursion = createThrowingLexicalScope) {
        log("Creating scope for companion object header resolution")
        createInheritanceScope(inheritanceScopeWithoutMe(), classDescriptor, classDescriptor, withCompanionObject = false)
    }

    val scopeForMemberDeclarationResolution: () -> LexicalScope = storageManager.createLazyValue {
        log("Creating scope for member declaration reoslution")
        val scopeWithGenerics = scopeWithGenerics(inheritanceScopeWithMe())
        log ("Created scope for member desclaration resolution")
        LexicalScopeImpl(scopeWithGenerics, classDescriptor, true, classDescriptor.thisAsReceiverParameter, LexicalScopeKind.CLASS_MEMBER_SCOPE)
    }

    val scopeForStaticMemberDeclarationResolution: () -> LexicalScope = storageManager.createLazyValue(onRecursion = createThrowingLexicalScope) {
        log("Creating scope for static member declaration resolution")
        if (classDescriptor.kind.isSingleton) {
            scopeForMemberDeclarationResolution()
        }
        else {
            inheritanceScopeWithMe()
        }
    }

    private fun createInheritanceScope(
            parent: LexicalScope,
            ownerDescriptor: DeclarationDescriptor,
            classDescriptor: ClassDescriptor,
            withCompanionObject: Boolean = true
    ): LexicalScope {
        log("Creating inheritance scope with parent=$parent, classDescriptor=$classDescriptor, withCompanionObject=$withCompanionObject")
        val staticScopes = ArrayList<MemberScope>(3)

        // todo filter fake overrides
        staticScopes.add(classDescriptor.staticScope)

        staticScopes.add(classDescriptor.unsubstitutedInnerClassesScope)

        val implicitReceiver: ReceiverParameterDescriptor?

        val parentForNewScope: LexicalScope

        if (withCompanionObject) {
            log("Doing dark magic because class has companion!")
            staticScopes.addIfNotNull(classDescriptor.companionObjectDescriptor?.unsubstitutedInnerClassesScope)
            implicitReceiver = classDescriptor.companionObjectDescriptor?.thisAsReceiverParameter

            parentForNewScope = classDescriptor.companionObjectDescriptor?.let {
                val scopes = it.getAllSuperclassesWithoutAny().asReversed()
                log("Got hierarchy of scopes for companion ${classDescriptor.companionObjectDescriptor}: ${scopes.joinToString()}")
                scopes.fold(parent) { scope, currentClass ->
                    log("Folding: $scope + $currentClass")
                    createInheritanceScope(parent = scope, ownerDescriptor = ownerDescriptor, classDescriptor = currentClass, withCompanionObject = false)
                }
            } ?: parent
        }
        else {
            implicitReceiver = null
            parentForNewScope = parent
        }
        log("Parent for new scope = $parentForNewScope")

        log("Returning inheritance scope")
        return LexicalChainedScope(parentForNewScope, ownerDescriptor, false,
                                   implicitReceiver,
                                   LexicalScopeKind.CLASS_INHERITANCE,
                                   memberScopes = staticScopes, isStaticScope = true)
    }

    private fun <T : Any> StorageManager.createLazyValue(onRecursion: ((Boolean) -> T), compute: () -> T) =
            createLazyValueWithPostCompute(compute, onRecursion, {})

    companion object {
        private val createThrowingLexicalScope: (Boolean) -> LexicalScope =  {
//            ThrowingLexicalScope()
            throw IllegalStateException("Found recursion in scopes")
        }
    }
}

fun scopeForInitializerResolution(
        classDescriptor: LazyClassDescriptor,
        parentDescriptor: DeclarationDescriptor,
        primaryConstructorParameters: List<KtParameter>
): LexicalScope {
    return LexicalScopeImpl(
            classDescriptor.scopeForMemberDeclarationResolution,
            parentDescriptor,
            false,
            null,
            LexicalScopeKind.CLASS_INITIALIZER
    ) {
        if (primaryConstructorParameters.isNotEmpty()) {
            val parameterDescriptors = classDescriptor.unsubstitutedPrimaryConstructor!!.valueParameters
            assert(parameterDescriptors.size == primaryConstructorParameters.size)
            for ((parameter, descriptor) in primaryConstructorParameters.zip(parameterDescriptors)) {
                if (!parameter.hasValOrVar()) {
                    addVariableDescriptor(descriptor)
                }
            }
        }
    }
}
