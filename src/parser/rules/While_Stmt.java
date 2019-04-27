package parser.rules;

import lexer.Token;

public class While_Stmt implements Stmt{

    public Token While;
    public Token LS;
    public Expr expr;
    public Token RS;
    public Stmt stmt;

    @Override
    public void printNode() {
        System.out.println("While_Stmt");
        System.out.println("Terminal: "+LS.getValue());
        if(expr!=null){
            expr.printNode();
        }
        System.out.println("Terminal: "+RS.getValue());
        if(stmt!=null){
            stmt.printNode();
            System.out.println();
        }

    }
}
