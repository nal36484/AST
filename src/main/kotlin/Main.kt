fun main() {
    val code = "Целое apple Равно 2'Если (apple Меньше 3) Тогда { apple Равно 8' } Иначе { apple Равно 0' }" +
            "Дробное knife Равно 5.5'Дробное appleFloat Равно (((apple Умножить 2) Делить (apple Минус knife)))" +
            "Плюс knife' Пока (appleFloat Больше 0) { appleFloat Равно appleFloat Минус 1' }"
    val lex = Lexer(code)
    lex.lexAnalisis()
    for (i in lex.tokensList) {
       println(i.text)
    }
    println("*****************************************************************")
    val toPascal = ToPascal(lex.tokensList).toPascal()
    println(toPascal)
    println("*****************************************************************")
    val toC = ToC(lex.tokensList).toC()
    println(toC)
    println("*****************************************************************")
    val parser = Parser(lex.tokensList)
    val rootNode = parser.parseCode()
    parser.run(rootNode)
    //println(parser.text)
    //var regex = Regex("int")
    //var result = regex.findAll(code)
    //for (i in result) {
        //println(i?.value)
    //}
    //for (i in tokenList)
    //println(i)
}