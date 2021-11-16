package AST

import Tokens

class IfStatementNode(val IfToken: Tokens,val condition: SuperNode, val thenPart: SuperNode,val elsePart: SuperNode): SuperNode() {
}