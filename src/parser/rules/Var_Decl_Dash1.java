package parser.rules;

import lexer.Token;

public class Var_Decl_Dash1 implements Var_Decl_Dash{
    public Token simicolon;

    @Override
    public void printNode() {

        System.out.println("Var_Decl_Dash: ");
        System.out.println("Simicolon: "+simicolon.getValue());


    }
}
