package AST

import Tokens

class TypeStatementsNode(val type: Tokens, val operation: SuperNode) : SuperNode() {
}