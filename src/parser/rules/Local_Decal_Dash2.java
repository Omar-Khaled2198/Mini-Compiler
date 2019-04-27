package parser.rules;

import lexer.Token;

public class Local_Decal_Dash2 implements Local_Decal_Dash{

    public Token LB;
    public Token RB;
    public Token simicolon;

    @Override
    public void printNode() {
        System.out.println("Local_Decal_Dash");
        System.out.println("Terminals: "+LB.getValue()+", "+RB.getValue()+", "+simicolon.getValue());
        System.out.println();
    }
}
