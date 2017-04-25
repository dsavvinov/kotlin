/*
 * Copyright 2010-2017 JetBrains s.r.o.
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

package org.jetbrains.kotlin.effects.visitors.helpers

import org.jetbrains.kotlin.effects.structure.effects.EsCallsEffect
import org.jetbrains.kotlin.effects.structure.effects.EsHints
import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.effects.EsThrows
import org.jetbrains.kotlin.effects.structure.general.*
import org.jetbrains.kotlin.effects.structure.schema.*
import org.jetbrains.kotlin.effects.structure.schema.operators.*
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.resolve.calls.smartcasts.IdentifierInfo

class EffectSchemaPrinter : SchemaVisitor<Unit> {
    override fun toString(): String {
        return sb.toString()
    }

    private val sb = StringBuilder()
    private var indent = ""
    private val indentStep = "  "

    private fun indent() {
        indent += indentStep
    }

    private fun outdent() {
        indent = indent.removeSuffix(indentStep)
    }

    private fun nested(body: () -> Unit): Unit {
        sb.appendln("{")
        indent()
        body()
        outdent()
        sb.append("$indent}")
    }

    private fun inBrackets(father: EsNode, child: EsNode, body: () -> Unit): Unit {
        val needsBrackets = child is EsIs || getPriority(child) < getPriority(father)
        if (needsBrackets) sb.append("(")
        body()
        if (needsBrackets) sb.append(")")
    }

    override fun visit(schema: EffectSchema) {
        nested {
            schema.clauses.forEach { it.accept(this); sb.appendln() }
        }
    }

    override fun visit(imply: org.jetbrains.kotlin.effects.structure.schema.operators.Imply): Unit {
        sb.append(indent)
        imply.left.accept(this)
        sb.append(" => ")
        imply.right.accept(this)
    }

    override fun visit(esIsOperator: EsIs): Unit {
        esIsOperator.arg.accept(this)
        sb.append(" is ${esIsOperator.type.unwrap()}")
    }

    override fun visit(esEqualOperator: EsEqual): Unit {
        // Write "x" instead of something like "x == true"
        if (esEqualOperator.right is EsConstant && esEqualOperator.right == true.lift()) {
            esEqualOperator.left.accept(this)
            return
        }

        inBrackets(esEqualOperator, esEqualOperator.left) {
            esEqualOperator.left.accept(this)
        }
        sb.append(" == ")
        inBrackets(esEqualOperator, esEqualOperator.right) {
            esEqualOperator.right.accept(this)
        }
    }

    override fun visit(throws: EsThrows): Unit {
        sb.append("Throws ${throws.exception.unwrap()}")
    }

    override fun visit(esOr: EsOr): Unit {
        inBrackets(esOr, esOr.left) { esOr.left.accept(this) }
        sb.append(" OR ")
        inBrackets(esOr, esOr.right) { esOr.right.accept(this) }
    }

    override fun visit(esAnd: EsAnd): Unit {
        inBrackets(esAnd, esAnd.left) { esAnd.left.accept(this) }
        sb.append(" AND ")
        inBrackets(esAnd, esAnd.right) { esAnd.right.accept(this) }
    }

    override fun visit(esNot: EsNot): Unit {
        sb.append("NOT")
        inBrackets(esNot, esNot.arg) { esNot.arg.accept(this) }
    }

    override fun visit(variable: EsVariable): Unit {
        if (variable.value == DataFlowValue.ERROR) {
            sb.append(variable.surrogateId)
            return
        }
        sb.append((variable.value.identifierInfo as? IdentifierInfo.Variable)?.variable?.name ?: variable.value.identifierInfo.toString())
    }

    override fun visit(constant: EsConstant): Unit {
        sb.append("${constant.value}")
    }

    override fun visit(esReturns: EsReturns) {
        sb.append("returns ${esReturns.value}")
    }

    override fun visit(cons: Cons) {
        cons.head.accept(this)
        if (cons.tail !is Nil) {
            sb.append(" :: ")
            cons.tail.accept(this)
        }
    }

    override fun visit(esHints: EsHints) {
        if (esHints.types.size == 1) {
            val (variable, types) = esHints.types.toList()[0]
            if (types.size == 1) {
                sb.append("Hints(")
                variable.accept(this)
                sb.append(", ")
                types.toList()[0].accept(this)
                sb.append(")")
                return
            }
        }
        sb.append("Hints(<non-trivial>)")
    }

    override fun visit(esCallsEffect: EsCallsEffect) {
        sb.append("EsCallsEffect(${esCallsEffect.callCounts})")
    }

    override fun visit(nil: Nil) = Unit

    override fun visit(esAt: EsAt) {
        esAt.left.accept(this)
        sb.append(" at ")
        esAt.right.accept(this)
    }

    override fun visit(esCall: EsCall) {
        esCall.callable.accept(this)
        sb.append("(")
        for ((index, arg) in esCall.arguments.withIndex()) {
            arg.accept(this)
            if (index != esCall.arguments.size - 1) sb.append(", ")
        }
    }

    override fun visit(esTypeOf: EsTypeOf) {
        sb.append("(")
        esTypeOf.left.accept(this)
        sb.append(")")

        sb.append(" typeOf ")
        esTypeOf.right.accept(this)
    }

    override fun visit(esType: EsType) {
        sb.append(esType.type)
    }

    override fun visit(esGenericType: EsGenericType) {
        if (esGenericType.isConstructed) {
            visit(esGenericType.type as EsType)
            return
        }

        sb.append ("${esGenericType.bareTypeName} < ")
        esGenericType.typeParameters.forEach { it.accept(this); sb.append(", ") }
        sb.append(">")

    }

    private fun getPriority(node: EsNode): Int {
        return when (node) {
            is EsOr -> 0
            is EsAnd -> 1
            is EsEqual -> 2
            is EsNot -> 5
            else -> 999
        }
    }


}

fun EsNode?.print() : String {
    this ?: return "null"

    val printer = EffectSchemaPrinter()
    this.accept(printer)
    return printer.toString()
}