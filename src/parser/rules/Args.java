package parser.rules;

public class Args {

    public Args_List args_list;

    public void printNode(){

        System.out.println("Args");
        if(args_list!=null){
            args_list.printNode();
            System.out.println();
        }
    }
}
