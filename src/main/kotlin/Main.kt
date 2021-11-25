fun main() {
    val code = "Целое apple Равно 2'Если (apple Меньше 3) Тогда { apple Равно 8' } Иначе { apple Равно 0' }" +
            "Дробное knife Равно 5.5'Дробное appleFloat Равно ((apple Умножить 2) Делить (apple Плюс knife))" +
            "Минус knife' Пока (appleFloat Больше 0) { appleFloat Равно appleFloat Минус 1' }"
    val lex = Lexer(code)
    lex.lexAnalysis()
    val parser = Parser(lex.tokensList)
    val rootNode = parser.parseCode()
    println("*****************************************************************")
    println(rootNode.toPascal())
    println("*****************************************************************")
    println(rootNode.toC())
}