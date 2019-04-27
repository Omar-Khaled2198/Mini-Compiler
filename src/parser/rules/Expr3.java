package parser.rules;

import lexer.Token;

public class Expr3 implements Expr{
    public Token LS;
    public Expr expr;
    public Token RS;
    public Expr_Q_Dash expr_q_dash;

    @Override
    public void printNode() {
        System.out.println("Expr");
        System.out.println("Terminal: "+LS.getValue());
        if(expr!=null){
            expr.printNode();
        }
        System.out.println("Terminal: "+RS.getValue());
        if(expr_q_dash!=null){
            expr_q_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
