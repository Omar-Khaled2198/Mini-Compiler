package parser.rules;

import javax.xml.bind.annotation.XmlElement;

public class Decl_List {

    @XmlElement
    public Decl decl;
    @XmlElement
    public Decl_List_Dash decl_list_dash;

    public void printNode(){

        System.out.println("Decl_List");
        decl.printNode();
        System.out.println();
        if(decl_list_dash!=null){
            decl_list_dash.printNode();
            System.out.println();
        }


    }
}
