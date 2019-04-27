package parser.rules;

public class Params1 implements Params {

    public Param_List param_list;

    @Override
    public void printNode() {

        System.out.println("Params");
        if(param_list!=null){
            param_list.printNode();
            System.out.println();
        } else
            System.out.println();

    }
}
