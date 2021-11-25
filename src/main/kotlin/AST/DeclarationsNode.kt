package AST

import TokenType
import Tokens

class DeclarationsNode(val declaration: Tokens, val variable: VariablesNode,val initialisation: SuperNode?) : SuperNode() {
    override fun toPascal(): String {
        if (initialisation!=null) {
            if (declaration.type.name == "int")
                return variable.toPascal() + ": Integer = " + initialisation.toPascal() + ";\n"
            if (declaration.type.name == "float")
                return variable.toPascal() + ": Single = " + initialisation.toPascal() + ";\n"
        }
        else if (declaration.type.name == "int")
            return variable.toPascal() + ": Integer;\n"
        else (declaration.type.name == "float")
            return variable.toPascal() + ": Single;\n"
    }

    override fun toC(): String {
        if (initialisation!=null) {
            if (declaration.type.name == "int")
                return "int " + variable.toC() + " = " + initialisation.toC() + ";\n"
            if (declaration.type.name == "float")
                return "float " + variable.toC() + " = " + initialisation.toC() + ";\n"
        }
        else if (declaration.type.name == "int")
            return "int " + variable.toC() + ";\n"
        else (declaration.type.name == "float")
            return "float " + variable.toC() + ";\n"
    }
}