fun main() {
    val code = "int a; when = b;  + else / if ;- int  () float; * while aa; 10.21 13 BB; "
    val lex = Lexer(code)
    lex.lexAnalisis()
    for (i in lex.tokensList) {
       println(i.text)
    }
    val toPascal = ToPascal(lex.tokensList).toPascal()
    println(toPascal)
    //val parser = Parser(lex.tokensList)
    //val rootNode = parser.parseCode()
    //parser.run(rootNode)
    //var regex = Regex("int")
    //var result = regex.findAll(code)
    //for (i in result) {
        //println(i?.value)
    //}
    //for (i in tokenList)
    //println(i)
}