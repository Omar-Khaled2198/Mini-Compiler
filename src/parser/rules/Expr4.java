package parser.rules;

import lexer.Token;

public class Expr4 implements Expr{

    public Token literal;
    public Expr_Q_Dash expr_q_dash;

    @Override
    public void printNode() {
        System.out.println("Expr");
        System.out.println("Terminal: "+literal.getValue());
        if(expr_q_dash!=null){
            expr_q_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
