package parser.rules;

import lexer.Token;

public class Args_List_Dash {

    public Token comma;
    public Expr expr;
    public Args_List_Dash args_list_dash;

    public void printNode(){

        System.out.println("Args_List_Dash");
        System.out.println("Terminal: "+comma.getValue());
        if(expr!=null){
            expr.printNode();
        }
        if(args_list_dash!=null){
            args_list_dash.printNode();
            System.out.println();
        }

    }
}
