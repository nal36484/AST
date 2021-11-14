import AST.*

class Parser(var tokenList: Array<Tokens>, var pos: Int = 0, var scope: Any = 0) {

    fun match(vararg tokenTypeList: TokenType): Tokens? {
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

    fun require(vararg tokenTypeList: TokenType): Tokens {
        return match(*tokenTypeList) ?: throw Exception("На позиции ${this.pos} ожидается ${tokenTypeList[0].name}")
    }

    fun parseVariableOrNumber() : SuperNode {
        val number = this.match(number)
        if (number != null) {
            return Numbers(number)
        }
        val variable = this.match(variable)
        if (variable != null) {
            return Variables(variable)
        }
        throw Exception("Ожидается число или переменная на позиции ${this.pos}")
    }

    fun parsePrint(): SuperNode {
        val token = this.match(int,float,esli,togda,gde,poka)
        if (token != null) {
            return UnarOperations(token,this.parseFormula())
        }
        throw Exception ("Ожидается унарный оператор на позиции ${this.pos}")
    }

    fun parseParentheses(): SuperNode {
        if (this.match(lpar) != null) {
            val node = this.parseFormula()
            this.require(rpar)
            return node
        } else {
            return this.parseVariableOrNumber()
        }
    }

    fun parseFormula(): SuperNode {
        var leftNode = this.parseParentheses()
        var operator = this.match(minus,plus,delit,multiply)
        while (operator != null) {
            var rightNode = parseParentheses()
            leftNode = BinarOperations(operator,leftNode, rightNode)
            operator = this.match(minus,plus,delit,multiply)
        }
        return leftNode
    }

    fun parseSuperNode(): SuperNode {
        if(match(variable)==null) {
            val printNode = this.parsePrint()
            return printNode
        }
        this.pos -= 1
        var variableNode = parseVariableOrNumber()
        val assignOperator = this.match(assign)
        if (assignOperator != null) {
            val rightFormulaNode = this.parseFormula()
            return BinarOperations(assignOperator, variableNode, rightFormulaNode)
        }
        throw Exception ("После переменной ожидается оператор присваивания на позиции ${this.pos}")
    }

    fun parseCode() : SuperNode {
        val root = StatementsNode()
        while (this.pos < tokenList.size){
            val codeStringNode = this.parseSuperNode()
            this.require(semicolon)
            root.addNode(codeStringNode)
        }
        return root
    }
    fun run(node: SuperNode): Any {
        if (node is Numbers) {
            return node.number.text
        }
        if (node is UnarOperations) {
            when (node.operator.type.name) {
                int.name -> return node.operator.text
                float.name -> return node.operator.text
                esli.name -> return  node.operator.text
                togda.name -> return node.operator.text
                togda.name -> return node.operator.text
                gde.name -> return  node.operator.text
            }
        }
        if (node is BinarOperations) {
            when (node.operator.type.name) {
                plus.name -> return node.operator.text
                minus.name -> return node.operator.text
                multiply.name -> return node.operator.text
                delit.name -> return node.operator.text
                assign.name -> return node.operator.text
            }
        }
        if (node is Variables) {
            return node.variable.text
        }
        if (node is StatementsNode) {
            node.codeStrings.forEach { codeString -> this.run(codeString) }
        }
        throw Exception("Ошибка!")
    }
}