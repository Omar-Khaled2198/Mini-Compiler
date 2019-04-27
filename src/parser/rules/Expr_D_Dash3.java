package parser.rules;

import lexer.Token;

public class Expr_D_Dash3 implements Expr_D_Dash{

    public Token LS;
    public Args args;
    public Token RS;

    @Override
    public void printNode() {
        System.out.println("Expr_D_Dash");
        System.out.println("Terminal: "+LS.getValue());
        if(args!=null){
            args.printNode();
        }
        System.out.println("Terminal: "+RS.getValue());
        System.out.println();
    }
}
