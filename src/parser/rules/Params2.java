package parser.rules;

import lexer.Token;

public class Params2 implements Params {

    public Token Void;

    @Override
    public void printNode() {

        System.out.println("Params");
        System.out.println("Terminals: "+Void.getValue());
    }
}
