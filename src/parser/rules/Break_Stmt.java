package parser.rules;

import com.sun.jndi.ldap.Ber;
import lexer.Token;

public class Break_Stmt implements Stmt{

    public Token Break;
    public Token simicolon;


    @Override
    public void printNode() {
        System.out.println("Break_Stmt");
        System.out.println("Terminals: "+ Break.getValue()+", "+simicolon.getValue());
        System.out.println();
    }
}
