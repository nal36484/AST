class TokenType(val name: String,val regex: String) {
}
val int = TokenType("int","Целое")
val float = TokenType("float","Дробное")
val esli = TokenType("esli","Если")
val togda = TokenType("togda","Тогда")
val inache = TokenType("gde","Иначе")
val poka = TokenType("poka","Пока")
val number = TokenType("Number","(\\d{1,16})+\\.+(\\d{1,16})")
val semicolon = TokenType("semicolon","\\'")
val space = TokenType("space","\\s")
val assign = TokenType("assign","Равно")
val variable = TokenType("variable","\\w{1,16}")
val plus = TokenType("plus","\\Плюс")
val minus = TokenType("minus","\\Минус")
val multiply = TokenType("multiply","Умножить")
val delit = TokenType("delit","Делить")
val lpar = TokenType("lpar","\\(")
val rpar = TokenType("rpar","\\)")
val largest = TokenType(">","Больше")
val smallest = TokenType("<","Меньше")
val lGreatPar = TokenType("{","\\{")
val rGreatPar = TokenType("}","\\}")

var tokenTypeList: Array<TokenType> = arrayOf(int,float,esli,togda,inache,poka,number,largest,smallest,semicolon,
    space, assign, variable,plus,minus,multiply,delit,lpar,rpar,lGreatPar,rGreatPar)


