package AST

import Tokens

class WhileStatementNode(val whileToken: Tokens,val condition: SuperNode,val body: SuperNode): SuperNode() {
}