package parser.rules;

import lexer.Token;

public class Expr_D_Dash4 implements Expr_D_Dash{

    public Token dot;
    public Token size;

    @Override
    public void printNode() {
        System.out.println("Expr_D_Dash");
        System.out.println("Terminals: "+dot.getValue()+", "+size.getValue());
    }
}
