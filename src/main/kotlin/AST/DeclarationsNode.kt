package AST

import Tokens

class DeclarationsNode(val declaration: Tokens, val variable: SuperNode) : SuperNode() {
}