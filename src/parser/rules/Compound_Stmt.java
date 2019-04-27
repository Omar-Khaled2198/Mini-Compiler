package parser.rules;

import lexer.Token;

public class Compound_Stmt implements Stmt{

    public Token LC;
    public Local_Decals local_decals;
    public Stmt_List stmt_list;
    public Token RC;

    @Override
    public void printNode() {
        System.out.println("Compound_Stmt");
        System.out.println("Terminal: "+LC.getValue());
        if(local_decals!=null){
            local_decals.printNode();
        }
        if(stmt_list!=null){
            stmt_list.printNode();
            System.out.println();
        }
        System.out.println("Terminal: "+RC.getValue());
        System.out.println();
    }
}
