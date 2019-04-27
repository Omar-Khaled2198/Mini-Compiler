package parser.rules;

import lexer.Token;

public class Expr1 implements Expr{

    public Token id;
    public Expr_D_Dash expr_d_dash;
    public Expr_Q_Dash expr_q_dash;

    @Override
    public void printNode() {
        System.out.println("Expr");
        System.out.println("Terminal: "+id.getValue());
        if(expr_d_dash!=null){
            expr_d_dash.printNode();
        }
        if(expr_q_dash!=null){
            expr_q_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
