package AST

import Tokens

class WhileStatementNode(val whileToken: Tokens,val condition: SuperNode,val body: SuperNode): SuperNode() {
    override fun toPascal(): String {
        return "while " + condition.toPascal() + " do\nbegin\n  " + body.toPascal() + ";\nend;\n"
    }
    override fun toC(): String {
        return "while (" + condition.toC() + ") {\n  " + body.toC() + ";\n}\n"
    }
}