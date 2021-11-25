import AST.*

class Parser(var tokenList: Array<Tokens>, var pos: Int = 0) {

    private fun match(vararg tokenTypeList: TokenType): Tokens? {
       if (this.pos < this.tokenList.size) {
           val currentToken = this.tokenList[this.pos]
           for (token in tokenTypeList){
               if (token.name===currentToken.type.name) {
                   this.pos += 1
                   return currentToken
               }
           }
       }
        return null
    }
    private fun require(vararg tokenTypeList: TokenType): Tokens {
        return match(*tokenTypeList) ?: throw Exception("На позиции ${this.pos} ожидается ${tokenTypeList[0].name}")
    }
    private fun parseVariableOrNumber() : SuperNode {
        val number = this.match(number)
        if (number != null) {
            return NumbersNode(number)
        }
        val variable = this.match(variable)
        if (variable != null) {
            return VariablesNode(variable)
        }
        throw Exception("Ожидается число или переменная на позиции ${this.pos}")
    }
    private fun parseParentheses(): SuperNode {
        return if (this.match(lpar) != null) {
            val node = this.parseFormula()
            this.require(rpar)
            node
        } else {
            this.parseVariableOrNumber()
        }
    }
    private fun parseFormula(): SuperNode {
        var leftNode = this.parseParentheses()
        var operator = this.match(minus,plus,delit,multiply,assign,largest,smallest)
        while (operator != null) {
            val rightNode = parseParentheses()
            leftNode = BinarOperations(operator,leftNode, rightNode)
            operator = this.match(minus,plus,delit,multiply,assign,largest,smallest)
        }
        return leftNode
    }
    private fun parseDeclaration(): SuperNode? {
        val assignOperator = this.match(assign)
        if (assignOperator != null) {
            return parseFormula()
        }
        return null
    }
    private fun parseElse():SuperNode {
        val elseToken = this.match(inache)
        if (elseToken != null) {
            val elsePart = parseGreatPar()
            return ElseStatementNode(elseToken,elsePart)
        }
        throw Exception("После блока Если ожидается оператор Иначе на позиции ${this.pos}")
    }
    private fun parseIf():SuperNode {
        this.pos -=1
        val ifToken = match(esli)
        if (ifToken != null) {
            val condition = parseParentheses()
            require(togda)
            val thenPart = parseGreatPar()
            val elsePart = parseElse()
            return IfStatementNode(ifToken,condition,thenPart,elsePart)
        }
        throw Exception("Ожидается оператор Если на позиции ${this.pos}")
    }
    private fun parseGreatPar(): SuperNode {
        if (match(lGreatPar) != null) {
            val formula = parseSuperNode()
            require(semicolon)
            require(rGreatPar)
            return formula
        }
        return parseFormula()
    }
    private fun parseWhile(): SuperNode {
        this.pos -=1
        val whileToken = this.match(poka)
        val condition = parseParentheses()
        val thenPart = parseGreatPar()
        if (whileToken != null) {
            return WhileStatementNode(whileToken,condition,thenPart)
        }
        throw Exception ("Ожидается оператор Пока на позиции ${this.pos}")
    }
    private fun parseSuperNode(): SuperNode {
        val declaration = match(int,float)
        if (declaration != null) {
            val variable = parseVariableOrNumber()
            return DeclarationsNode(declaration,variable as VariablesNode,parseDeclaration())
        } else if (match(esli) != null) {
            val ifNode = this.parseIf()
            return ifNode
        } else if (match(poka) != null) {
            val whileNode = this.parseWhile()
            return whileNode
        } else if (match(variable) != null) {
            this.pos -=1
            return parseFormula()
        }
        throw Exception ("После переменной ожидается оператор присваивания на позиции ${this.pos}")
    }
    fun parseCode() : SuperNode {
        val root = StatementsNode()
        while (this.pos < tokenList.size){
            val codeStringNode = this.parseSuperNode()
            this.match(rGreatPar,semicolon,lGreatPar)
            root.addNode(codeStringNode)
        }
        return root
    }
}