package parser.rules;

import lexer.Token;

public class Return_Stmt implements Stmt{

    public Token Return;
    public Return_Stmt_Dash return_stmt_dash;

    @Override
    public void printNode() {
        System.out.println("Return_Stmt");
        System.out.println("Terminal: "+Return.getValue());
        if(return_stmt_dash!=null){
            return_stmt_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
