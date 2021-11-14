package AST

class StatementsNode(var codeStrings: Array<SuperNode> = arrayOf()): SuperNode() {
    fun addNode(node: SuperNode) {
        this.codeStrings += node
    }
}