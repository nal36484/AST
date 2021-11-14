class TokenType(val name: String,val regex: String) {
}
val int = TokenType("int","int")
val float = TokenType("float","float")
val esli = TokenType("esli","if")
val togda = TokenType("togda","else")
val gde = TokenType("gde","when")
val poka = TokenType("poka","while")
val number = TokenType("Number","(\\d{1,16})+\\.+(\\d{1,16})")
val semicolon = TokenType("semicolon",";")
val space = TokenType("space","\\s")
val assign = TokenType("assign","=")
val variable = TokenType("variable","\\w{1,16}")
val plus = TokenType("plus","\\+")
val minus = TokenType("minus","\\-")
val multiply = TokenType("multiply","\\*")
val delit = TokenType("delit","\\/")
val lpar = TokenType("lpar","\\(")
val rpar = TokenType("rpar","\\)")


var tokenTypeList: Array<TokenType> = arrayOf(int,float,esli,togda,gde,poka,number,semicolon,space,assign,
    variable,plus,minus,multiply,delit,lpar,rpar)


/*enum class TokenTypes {
    INT{val int = TokenType("int","int")},
    FLOAT{val float = TokenType("float","float")},
    NUMBER{val number = TokenType("Number","[0-9].*")},
    SEMICOLON{val semicolon = TokenType("semicolon",";")},
    SPACE{val space = TokenType("space","[\\n\\t\\r]")},
    ASSIGN{val assign = TokenType("assign","=")},
    VARIABLE{val log = TokenType("variable","[a-zA-z0-9].*")},
    PLUS{val plus = TokenType("plus","+")},
    MINUS{val minus = TokenType("minus","-")},
    MULTIPLY{val multiply = TokenType("multiply","*")},
    DELIT{val delit = TokenType("delit","/")},
    LPAR{val lpar = TokenType("lpar","\\(")},
    RPAR{val rpar = TokenType("rpar","\\)")},
    IF{val esli = TokenType("esli","if")},
    ELSE{val togda = TokenType("togda","else")},
    WHEN{val gde = TokenType("gde","when")},
    WHILE{val poka = TokenType("poka","while")},
}*/
/*enum class TokenTypes(val types: String) {
    INT("int"),
    FLOAT("float"),
    NUMBER("[0-9].*"),
    SEMICOLON(";"),
    SPACE("[\\n\\t\\r]"),
    ASSIGN("="),
    VARIABLE("[a-zA-z0-9].*"),
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DELIT("/"),
    LPAR("\\("),
    RPAR("\\)"),
    IF("if"),
    ELSE("else"),
    WHEN("when"),
    WHILE("while"),
}*/





