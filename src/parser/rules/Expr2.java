package parser.rules;

import lexer.Token;

public class Expr2 implements Expr{

    public Token op;
    public Expr expr;
    public Expr_Q_Dash expr_q_dash;

    @Override
    public void printNode() {
        System.out.println("Expr");
        System.out.println("Terminal: "+op.getValue());
        if(expr!=null){
            expr.printNode();
        }
        if(expr_q_dash!=null){
            expr_q_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
