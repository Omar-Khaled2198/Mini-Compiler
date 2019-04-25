package parser.rules;

import lexer.Token;

public class Param_Dash {

    public Token LS;
    public Token RS;

    public void printNode(){
        System.out.println("Param_Dash");
        System.out.println("Terminals: "+LS.getValue()+", "+RS.getValue());
        System.out.println();
    }
}
