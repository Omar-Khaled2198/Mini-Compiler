package parser.rules;

import lexer.Token;

public class Expr_S_Dash {

    public Token Eq;
    public Expr expr;

    public void printNode(){
        System.out.println("Expr_S_Dash");
        System.out.println("Terminal: "+Eq.getValue());
        if(expr!=null){
            expr.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
