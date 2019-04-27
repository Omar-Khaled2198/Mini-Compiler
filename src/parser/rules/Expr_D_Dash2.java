package parser.rules;

import lexer.Token;

public class Expr_D_Dash2 implements Expr_D_Dash{

    public Token Eq;
    public Expr expr;

    @Override
    public void printNode() {
        System.out.println("Expr_D_Dash");
        System.out.println("Terminal: "+Eq.getValue());
        if(expr!=null){
            expr.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
