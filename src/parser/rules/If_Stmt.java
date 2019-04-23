package parser.rules;

import lexer.Token;

public class If_Stmt implements Stmt{

    public Token If;
    public Token LS;
    public Expr expr;
    public Token RS;
    public Stmt stmt;
    public If_Stmt_Dash if_stmt_dash;

}
