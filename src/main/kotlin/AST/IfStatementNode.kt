package AST

import Tokens

class IfStatementNode(val IfToken: Tokens,val condition: SuperNode, val thenPart: SuperNode,val elsePart: SuperNode): SuperNode() {
    override fun toPascal(): String {
        return  "if " + condition.toPascal() + " then\n  " + thenPart.toPascal() +"\n" + elsePart.toPascal()
    }
    override fun toC(): String {
        return  "if (" + condition.toC() + ") {\n  " + thenPart.toC() +";\n} " + elsePart.toC()
    }
}