package parser.rules;

public class Args_List {

    public Expr expr;
    public Args_List_Dash args_list_dash;

    public void printNode(){

        System.out.println("Args_List");
        if(expr!=null){
            expr.printNode();
        }
        if(args_list_dash!=null){
            args_list_dash.printNode();
            System.out.println();
        }

    }
}
