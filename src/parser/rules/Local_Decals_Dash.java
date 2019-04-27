package parser.rules;

public class Local_Decals_Dash {

    public Local_Decal local_decal;
    public Local_Decals_Dash local_decals_dash;

    public void printNode(){
        System.out.println("Local_Decals_Dash");
        if(local_decal!=null){
            local_decal.printNode();
        }
        if(local_decals_dash!=null){
            local_decals_dash.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
