package org.jetbrains.kotlin.effects.structure.schema.operators

import org.jetbrains.kotlin.effects.structure.general.EsConstant
import org.jetbrains.kotlin.effects.structure.general.EsNode
import org.jetbrains.kotlin.effects.structure.lift
import org.jetbrains.kotlin.effects.structure.schema.SchemaVisitor

data class EsAnd(override val left: EsNode, override val right: EsNode) : BinaryOperator {
    override fun <T> accept(visitor: SchemaVisitor<T>): T = visitor.visit(this)
    override fun newInstance(left: EsNode, right: EsNode): BinaryOperator = EsAnd(left, right)

    override fun reduce(): EsNode {
        if (left is EsConstant) {
            if (left.value == false) {
                return false.lift()
            }
            return right
        }

        if (right is EsConstant) {
            if (right.value == false) {
                return false.lift()
            }
            return left
        }

        return this
    }
}

infix fun (EsNode).and(node: EsNode) : EsNode {
    if (this == true.lift()) return node
    if (node == true.lift()) return this
    return EsAnd(this, node)
}