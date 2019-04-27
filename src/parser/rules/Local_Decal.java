package parser.rules;

import lexer.Token;

public class Local_Decal {

    public Token type_spec;
    public Token id;
    public Local_Decal_Dash local_decal_dash;

    public void printNode(){
        System.out.println("Local_Decal");
        System.out.println("Terminals: "+type_spec.getValue()+", "+id.getValue());
        if(local_decal_dash!=null){
            local_decal_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
