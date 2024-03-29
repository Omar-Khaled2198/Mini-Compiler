package parser.rules;

import lexer.Token;

public class Param_List_Dash {

    public Token comma;
    public Param param;
    public Param_List_Dash param_list_dash;


    public void printNode(){
        System.out.println("Param_List_Dash");
        System.out.println("Terminals: "+comma.getValue());
        if(param!=null){
            param.printNode();
        }
        if(param_list_dash!=null){
            param_list_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
