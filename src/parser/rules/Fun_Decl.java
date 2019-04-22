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

    }
}
