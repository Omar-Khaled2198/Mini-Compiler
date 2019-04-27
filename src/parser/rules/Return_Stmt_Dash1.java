package parser.rules;

import lexer.Token;

public class Return_Stmt_Dash1 implements Return_Stmt_Dash {

    public Token simicolon;

    @Override
    public void printNode() {
        System.out.println("Return_Stmt_Dash");
        System.out.println("Terminal: "+simicolon.getValue());
    }
}
