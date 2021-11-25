package AST

import Tokens

class ElseStatementNode(val elseToken: Tokens,val formula: SuperNode): SuperNode() {
    override fun toPascal(): String {
        return "else\n  " + formula.toPascal() + ";\n"
    }
    override fun toC(): String {
        return "else {\n  " + formula.toC() + ";\n}\n"
    }
}