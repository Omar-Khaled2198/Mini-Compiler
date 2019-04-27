package parser.rules;



public class Decl_List {

    public Decl decl;
    public Decl_List_Dash decl_list_dash;

    public void printNode(){

        System.out.println("Decl_List");
        decl.printNode();
        if(decl_list_dash!=null){
            decl_list_dash.printNode();
            System.out.println();
        } else
            System.out.println();

    }
}
