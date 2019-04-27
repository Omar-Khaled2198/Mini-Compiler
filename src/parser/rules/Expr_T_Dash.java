package parser.rules;

import lexer.Token;

public class Expr_T_Dash {

    public Token op;
    public Expr expr;

    public void printNode(){
        System.out.println("Expr_T_Dash");
        System.out.println("Terminal: "+op.getValue());
        if(expr!=null){
            expr.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
