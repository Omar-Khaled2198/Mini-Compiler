package parser.rules;

import lexer.Token;

public class While_Stmt implements Stmt{

    public Token While;
    public Token LS;
    public Expr expr;
    public Token RS;
    public Stmt stmt;
}