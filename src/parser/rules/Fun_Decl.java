package parser.rules;

import lexer.Token;

public class Fun_Decl implements Decl{

    public Token type_spec;
    public Token id;
    public Token LB;
    public Params params;
    public Token RB;
    public Compound_Stmt compound_stmt;

    @Override
    public void printNode() {
        System.out.println("Fun_Decl");
        System.out.println("Terminals: "+type_spec.getValue()+", "+id.getValue()+", "+
                LB.getValue());
        if(params!=null){
            params.printNode();
        }
        System.out.println("Terminal: "+RB.getValue());
        if(compound_stmt!=null){
            compound_stmt.printNode();
            System.out.println();
        } else
            System.out.println();
    }
}
