package AST

class StatementsNode(var codeStrings: Array<SuperNode> = arrayOf()): SuperNode() {
    fun addNode(node: SuperNode) {
        this.codeStrings += node
    }
    override fun toPascal(): String {
        var code = ""
       for (node in codeStrings)
           code += node.toPascal()
        return code
    }
    override fun toC(): String {
        var code = ""
        for (node in codeStrings)
            code += node.toC()
        return code
    }
}