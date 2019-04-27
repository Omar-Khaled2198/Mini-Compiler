package parser.rules;

import lexer.Token;

public class Expr5 implements Expr{

    public Token New;
    public Token type_spec;
    public Token LB;
    public Expr expr;
    public Token RB;
    public Expr_Q_Dash expr_q_dash;

    @Override
    public void printNode() {
        System.out.println("Expr");
        System.out.println("Terminals: "+New.getValue()+", "+type_spec.getValue()+", "+LB.getValue());
        if(expr!=null){
            expr.printNode();
        }
        System.out.println("Terminal: "+RB.getValue());
        if(expr_q_dash!=null){
            expr_q_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
