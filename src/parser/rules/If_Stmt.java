package parser.rules;

import lexer.Token;

public class If_Stmt implements Stmt{

    public Token If;
    public Token LS;
    public Expr expr;
    public Token RS;
    public Stmt stmt;
    public If_Stmt_Dash if_stmt_dash;

    @Override
    public void printNode() {
        System.out.println("If_Stmt");
        System.out.println("Terminals: "+If.getValue()+", "+LS.getValue());
        if(expr!=null){
            expr.printNode();
        }
        System.out.println("Terminal: "+RS.getValue());
        if(stmt!=null){
            stmt.printNode();
        }
        if(if_stmt_dash!=null){
            if_stmt_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
