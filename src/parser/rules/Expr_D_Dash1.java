package parser.rules;

import lexer.Token;

public class Expr_D_Dash1 implements Expr_D_Dash{

    public Token LB;
    public Expr expr;
    public Token RB;
    public Expr_S_Dash expr_s_dash;

    @Override
    public void printNode() {
        System.out.println("Expr_D_Dash");
        System.out.println("Terminal: "+LB.getValue());
        if(expr!=null){
            expr.printNode();
        }
        System.out.println("Terminal: "+RB.getValue());
        if(expr_s_dash!=null){
            expr_s_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
