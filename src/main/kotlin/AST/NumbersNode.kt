package AST

import Tokens

class NumbersNode(var number: Tokens):SuperNode() {
    override fun toPascal(): String {
        return number.text
    }
    override fun toC(): String {
        return number.text
    }
}