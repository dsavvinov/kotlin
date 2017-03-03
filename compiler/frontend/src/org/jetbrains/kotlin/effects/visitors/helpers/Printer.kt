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

import org.jetbrains.kotlin.effects.structure.effects.EsThrows
import org.jetbrains.kotlin.effects.structure.schema.operators.EsIs
import org.jetbrains.kotlin.effects.structure.schema.operators.EsNot
import org.jetbrains.kotlin.effects.structure.schema.operators.EsOr
import org.jetbrains.kotlin.effects.structure.effects.EsReturns
import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsType
import org.jetbrains.kotlin.effects.structure.general.EsVariable
import org.jetbrains.kotlin.effects.structure.lift
import org.jetbrains.kotlin.effects.structure.schema.Cons
import org.jetbrains.kotlin.effects.structure.schema.EffectSchema
import org.jetbrains.kotlin.effects.structure.schema.Nil
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor
import org.jetbrains.kotlin.effects.structure.schema.operators.EsAnd
import org.jetbrains.kotlin.effects.structure.schema.operators.EsEqual

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

    private fun inBrackets(body: () -> Unit): Unit {
        sb.append("(")
        body()
        sb.append(")")
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
        sb.append(" is ${esIsOperator.type}")
    }

    override fun visit(esEqualOperator: EsEqual): Unit {
        inBrackets {
            esEqualOperator.left.accept(this)

            // Write "x" instead of something like "x == true"
            if ((esEqualOperator.right is EsConstant && esEqualOperator.right == true.lift()).not()) {
                sb.append(" == ")
                esEqualOperator.right.accept(this)
            }
        }
    }

    override fun visit(throws: EsThrows): Unit {
        sb.append("Throws ${throws.exception}")
    }

    override fun visit(esOr: EsOr): Unit {
        inBrackets { esOr.left.accept(this) }
        sb.append(" OR ")
        inBrackets { esOr.right.accept(this) }
    }

    override fun visit(and: EsAnd): Unit {
        inBrackets { and.left.accept(this) }
        sb.append(" AND ")
        inBrackets { and.right.accept(this) }
    }

    override fun visit(esNot: EsNot): Unit {
        sb.append("NOT")
        inBrackets { esNot.arg.accept(this) }
    }

    override fun visit(variable: EsVariable): Unit {
        sb.append(variable.reference)
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

    override fun visit(nil: Nil) {
        super.visit(nil)
    }
}

fun (EffectSchema).print() : String {
    val printer = EffectSchemaPrinter()
    printer.visit(this)
    return printer.toString()
}