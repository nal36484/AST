class Lexer(val code: String, var pos: Int = 0, var tokensList: Array<Tokens> = arrayOf()){

    fun lexAnalysis(): Array<Tokens> {
        while (this.nextToken())
        this.tokensList = this.tokensList.filter { tokens -> tokens.type.name !== space.name}.toTypedArray()
        return tokensList
    }
    private fun nextToken(): Boolean {
        if (this.pos >= this.code.length) {
            return false
        }
        for (i in tokenTypeList){
            val regex = Regex("^" + i.regex)
            val result = regex.find(this.code.substring(this.pos))
                if (result?.value != null) {
                    val token = Tokens(i, result.value, this.pos)
                    this.pos += result.value.length
                    this.tokensList +=token
                    return true
                }
            }
        throw Exception ("На позиции ${this.pos} обнаружена ошибка ")
    }
}
