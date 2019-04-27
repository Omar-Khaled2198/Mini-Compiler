package parser.rules;

public class Local_Decals implements Stmt{

    public Local_Decals_Dash local_decals_dash;

    @Override
    public void printNode() {
        System.out.println("Local_Decals");
        if(local_decals_dash!=null){
            local_decals_dash.printNode();
            System.out.println();

        } else
            System.out.println();
    }
}
