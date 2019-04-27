package parser.rules;

import lexer.Token;

public class If_Stmt_Dash implements Stmt{

    public Token Else;
    public Stmt stmt;

    @Override
    public void printNode() {
        System.out.println("If_Stmt_Dash");
        System.out.println("Terminal: "+Else.getValue());
        if(stmt!=null){
            stmt.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
