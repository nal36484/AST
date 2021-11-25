package AST

import Tokens
import TokenType

class BinarOperations(val operator: Tokens,val leftNode: SuperNode,val rightNode: SuperNode): SuperNode() {
    override fun toPascal(): String {
        return when (operator.type.name) {
            "plus" -> "(" + leftNode.toPascal() + " + " + rightNode.toPascal() + ")"
            "minus" -> leftNode.toPascal() + " - " + rightNode.toPascal()
            "multiply" -> leftNode.toPascal() + " * " + rightNode.toPascal()
            "delit" -> leftNode.toPascal() + " / " + rightNode.toPascal()
            ">" -> leftNode.toPascal() + " > " + rightNode.toPascal()
            "<" -> leftNode.toPascal() + " < " + rightNode.toPascal()
            else -> leftNode.toPascal() + " := " + rightNode.toPascal()
        }
    }
    override fun toC(): String {
        return when (operator.type.name) {
            "plus" -> "(" + leftNode.toC() + " + " + rightNode.toC() + ")"
            "minus" -> leftNode.toC() + " - " + rightNode.toC()
            "multiply" -> leftNode.toC() + " * " + rightNode.toC()
            "delit" -> leftNode.toC() + " / " + rightNode.toC()
            ">" -> leftNode.toC() + " > " + rightNode.toC()
            "<" -> leftNode.toC() + " < " + rightNode.toC()
            else -> leftNode.toC() + " = " + rightNode.toC()
        }
    }
}