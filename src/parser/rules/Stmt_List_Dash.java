package parser.rules;

public class Stmt_List_Dash {

    public Stmt stmt;
    public Stmt_List_Dash stmt_list_dash;

    public void printNode(){
        System.out.println("Stmt_List_Dash");
        if(stmt!=null){
            stmt.printNode();
        }
        if(stmt_list_dash!=null){
            stmt_list_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
