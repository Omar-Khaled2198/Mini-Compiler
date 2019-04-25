package parser.rules;

import lexer.Token;

public class Var_Decl implements Decl{


    public Token type_spec;
    public Token id;
    public Var_Decl_Dash var_decl_dash;

    @Override
    public void printNode() {

        System.out.println("Var_Decl");
        System.out.println("Terminals: "+type_spec.getValue()+", "+id.getValue());
        System.out.println();
        var_decl_dash.printNode();
        System.out.println();



    }
}
