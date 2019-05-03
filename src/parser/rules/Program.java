package parser.rules;


public class Program {

    public Decl_List decl_list;

    public void printNode(){

        System.out.println("Program");
        decl_list.printNode();
        System.out.println();
    }
}
