package parser.rules;

public class Param_List {

    public Param param;
    public Param_List_Dash param_list_dash;

    public void printNode(){
        System.out.println("Param_List");
        if(param!=null){
            param.printNode();
            System.out.println();
        }
        if(param_list_dash!=null){
            param_list_dash.printNode();
            System.out.println();
        }
    }
}
