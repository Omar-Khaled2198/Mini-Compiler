package parser.rules;

import lexer.Token;

public class Expr_Stmt2 implements Expr_Stmt{

    public Token simicolon;

    @Override
    public void printNode() {
        System.out.println("Expr_Stmt");
        System.out.println("Terminal: "+simicolon.getValue());
    }
}
