import AST.*

class Parser(var tokenList: Array<Tokens>, var pos: Int = 0, var text: String = "") {

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
            return NumbersNode(number)
        }
        val variable = this.match(variable)
        if (variable != null) {
            return VariablesNode(variable)
        }
        throw Exception("Ожидается число или переменная на позиции ${this.pos}")
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
        var operator = this.match(minus,plus,delit,multiply,assign,largest,smallest)
        while (operator != null) {
            val rightNode = parseParentheses()
            leftNode = BinarOperations(operator,leftNode, rightNode)
            operator = this.match(minus,plus,delit,multiply,assign,largest,smallest)
        }
        return leftNode
    }

    fun parseDeclaration():SuperNode {
        val variableNode = this.parseVariableOrNumber()
        val assignOperator = this.match(assign)
        if (assignOperator != null) {
            val rightFormulaNode = this.parseFormula()
            val binaryNode = BinarOperations(assignOperator,variableNode,rightFormulaNode)
            return binaryNode
        }
        return variableNode
    }

    fun parseElse():SuperNode {
        val elseToken = this.match(togda)
        val elsePart = parseGreatPar()
        if (elseToken != null) {
            return ElseStatementNode(elseToken,elsePart)
        }
        throw Exception("После блока Если ожидается оператор Иначе на позиции ${this.pos}")
    }

    fun parseIf():SuperNode {
        this.pos -=1
        val ifToken = match(esli)
        if (ifToken != null) {
            val condition = parseFormula()
            val thenPart = parseGreatPar()
            val elsePart = parseElse()
            return IfStatementNode(ifToken,condition,thenPart,elsePart)
        }
        throw Exception("Ожидается оператор Если на позиции ${this.pos}")
    }

    fun parseGreatPar(): SuperNode {
        if (match(lGreatPar) != null) {
            val formula = parseSuperNode()
            require(rGreatPar)
            return formula
        }
        return parseFormula()
    }

    fun parseWhile(): SuperNode {
        this.pos -+1
        val whileToken = this.match(poka)
        val condition = parseParentheses()
        val thenPart = parseGreatPar()
        if (whileToken != null) {
            return WhileStatementNode(whileToken,condition,thenPart)
        }
        throw Exception ("Ожидается оператор Пока на позиции ${this.pos}")
    }

    fun parseSuperNode(): SuperNode {
        val declaration = match(int,float)
        if (declaration != null) {
            return DeclarationsNode(declaration,parseDeclaration())
        } else if (match(esli) != null) {
            val ifNode = this.parseIf()
            return ifNode
        } else if (match(poka) != null) {
            val whileNode = this.parseWhile()
            return whileNode
        } else if (match(variable) != null) {
            pos -=1
            return parseFormula()
        }
        throw Exception ("После переменной ожидается оператор присваивания на позиции ${this.pos}")
    }

    fun parseCode() : SuperNode {
        val root = StatementsNode()
        while (this.pos < tokenList.size){
            val codeStringNode = this.parseSuperNode()
            this.require(semicolon,rGreatPar)
            root.addNode(codeStringNode)
        }
        return root
    }
    /*fun run(node: SuperNode): Any {
        if (node is NumbersNode) {
            return node.number.text
        }
        if (node is DeclarationsNode) {
            when (node.declaration.type.name) {
                int.name -> text = node.declaration.text
                float.name -> text = node.declaration.text
            }
        }
        if (node is BinarOperations) {
            when (node.operator.type.name) {
                plus.name -> text = node.operator.text
                minus.name -> text = node.operator.text
                multiply.name -> text = node.operator.text
                delit.name -> text = node.operator.text
                assign.name -> text = node.operator.text
                smallest.name -> text = node.operator.text
                largest.name -> text = node.operator.text
            }
        }
        if (node is VariablesNode) {
            return node.variable.text
        }
        if (node is ElseStatementNode) {
            return node.elseToken.text
        }
        if (node is ElseStatementNode) {
            return node.elseToken.text
        }
        if (node is StatementsNode) {
            node.codeStrings.forEach { codeString -> this.run(codeString) }
        }
        throw Exception("Ошибка")
    }*/
}