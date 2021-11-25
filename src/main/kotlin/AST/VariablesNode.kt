package AST

import Tokens

class VariablesNode(val variables: Tokens) : SuperNode() {
    override fun toPascal(): String {
        return variables.text
    }
    override fun toC(): String {
        return variables.text
    }
}