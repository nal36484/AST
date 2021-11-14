package AST

import Tokens

class BinarOperations(val operator: Tokens,val leftNode: SuperNode,val rightNode: SuperNode): SuperNode() {
}