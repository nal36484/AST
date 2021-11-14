class ToPascal(val tokenTypeList: Array<Tokens>,var pascal: String = "") {
    fun toPascal(): String {
        for (token in tokenTypeList) {
            when (token.type) {
                int -> pascal += "int "
                float -> pascal += "float "
                esli -> pascal += "if "
                togda -> pascal += "else "
                gde -> pascal += "when "
                poka -> pascal += "while "
                number -> pascal += token.text + " "
                semicolon -> pascal += token.text + "\n"
                assign -> pascal += "= "
                variable -> pascal += token.text + " "
                plus -> pascal += "+ "
                minus -> pascal += "- "
                multiply -> pascal += "* "
                delit -> pascal += "/ "
                lpar -> pascal += "("
                rpar -> pascal += ") "
            }
        }
        return pascal
    }
}