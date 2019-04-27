package parser.rules;

import lexer.Token;

public class Local_Decal_Dash1 implements Local_Decal_Dash{

    public Token simicolon;

    @Override
    public void printNode() {
        System.out.println("Local_Decal_Dash");
        System.out.println("Terminal: "+simicolon.getValue());
    }
}

