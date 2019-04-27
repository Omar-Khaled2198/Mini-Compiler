package parser.rules;

import lexer.Token;

public class Return_Stmt_Dash2 implements Return_Stmt_Dash {

    public Expr expr;
    public Token simicolon;

    @Override
    public void printNode() {
        System.out.println("Return_Stmt_Dash");
        if(expr!=null){
            expr.printNode();
        }
        System.out.println("Terminal: "+simicolon.getValue());
        System.out.println();
    }
}
