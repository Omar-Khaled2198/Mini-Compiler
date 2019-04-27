package parser.rules;

import lexer.Token;

public class Expr_Stmt1 implements Expr_Stmt {

    public Expr expr;
    public Token simicolon;

    @Override
    public void printNode() {
        System.out.println("Expr_Stmt");
        if(expr!=null){
            expr.printNode();
        }
        System.out.println("Terminal: "+simicolon.getValue());
    }
}
