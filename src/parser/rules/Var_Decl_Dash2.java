package parser.rules;

import lexer.Token;

public class Var_Decl_Dash2 implements Var_Decl_Dash{

    public Token LB;
    public Token RB;
    public Token simicolon;

    @Override
    public void printNode() {
        System.out.println("Var_Decl_Dash");
        System.out.println("Terminals: "+LB.getValue()+", "+RB.getValue()+", "+simicolon.getValue());
        System.out.println();

    }
}
