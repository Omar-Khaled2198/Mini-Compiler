package parser.rules;

import lexer.Token;

public class Compound_Stmt implements Stmt{

    public Token LC;
    public Local_Decals local_decals;
    public Stmt_List stmt_list;
    public Token RC;
}
