class ToPascal(val tokenTypeList: Array<Tokens>,var pascal: String = "var\n") {
    fun toPascal(): String {
        for (token in tokenTypeList) {
            when (token.type) {
                int -> pascal += "Integer "
                float -> pascal += "Single "
                esli -> pascal += "if "
                togda -> pascal += "then\n"
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
                lGreatPar -> pascal += "\nbegin\n"
                rGreatPar -> pascal += "end;\n"
            }
        }
        return pascal
    }
}