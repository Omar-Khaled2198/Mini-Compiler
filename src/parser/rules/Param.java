package parser.rules;

import lexer.Token;

public class Param {

    public Token type_spec;
    public Token id;
    public Param_Dash param_dash;


    public void printNode(){
        System.out.println("Param");
        System.out.println("Terminals: "+type_spec.getValue()+", "+id.getValue());
        System.out.println();
        if(param_dash!=null){
            param_dash.printNode();
            System.out.println();
        }
    }
}
