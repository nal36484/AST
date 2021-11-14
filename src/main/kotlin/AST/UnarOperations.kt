package AST

import Tokens

class UnarOperations(val operator: Tokens, val operation: SuperNode) : SuperNode() {
}