class ToC(val tokenTypeList: Array<Tokens>,var pascal: String = "") {
    fun toC(): String {
        for (token in tokenTypeList) {
            when (token.type) {
                int -> pascal += "int "
                float -> pascal += "float "
                esli -> pascal += "if "
                togda -> pascal += ""
                inache -> pascal += "else\n"
                poka -> pascal += "while "
                number -> pascal += token.text + " "
                semicolon -> pascal += ";\n"
                assign -> pascal += "= "
                variable -> pascal += token.text + " "
                plus -> pascal += "+ "
                minus -> pascal += "- "
                multiply -> pascal += "* "
                delit -> pascal += "/ "
                lpar -> pascal += "("
                rpar -> pascal += ") "
                largest -> pascal += "> "
                smallest -> pascal += "< "
                lGreatPar -> pascal += "{\n"
                rGreatPar -> pascal += "} "
            }
        }
        return pascal
    }
}