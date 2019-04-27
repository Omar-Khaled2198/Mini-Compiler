package parser.rules;

public class Expr_Q_Dash {

    public Expr_T_Dash expr_t_dash;
    public Expr_Q_Dash expr_q_dash;

    public void printNode(){
        System.out.println("Expr_Q_Dash");
        if(expr_t_dash!=null){
            expr_t_dash.printNode();
        }
        if(expr_q_dash!=null){
            expr_q_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
