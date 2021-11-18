package AST

import Tokens

class ElseStatementNode(val elseToken: Tokens,val formula: SuperNode): SuperNode() {
}