package parser;

import lexer.Token;
import parser.rules.*;

import java.util.*;

public class Parser {

    public Queue<Token> tokens;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = new LinkedList<>(tokens);


    }

    public void parse() {
//        Program program=program_function();
//        program.printNode();
        program_function();
    }

    public Program program_function() {

        if (tokens.peek() != null) {
            Decl_List decl_list = decl_list_function();
            if (decl_list != null) {
                Program program = new Program();
                program.decl_list = decl_list;
                return program;
            }


        }

        return null;
    }

    public Decl_List decl_list_function() {

        if (tokens.peek() != null) {
            Decl_List decl_list = new Decl_List();
            Decl decl = decl_function();
            if (decl != null) {
                decl_list.decl = decl;
                decl_list.decl_list_dash = decl_list_dash_function();
                return decl_list;

            }

        }

        return null;

    }

    public Decl_List_Dash decl_list_dash_function() {
        if (tokens.peek() != null) {
            Decl_List_Dash decl_list_dash = new Decl_List_Dash();
            Decl decl = decl_function();
            if (decl != null) {
                decl_list_dash.decl = decl;
                decl_list_dash.decl_list_dash = decl_list_dash_function();
                return decl_list_dash;
            }
        }

        return null;
    }

    public Decl decl_function() {
        if (tokens.peek() != null) {
            Var_Decl var_decl = var_decl_function();
            if (var_decl != null) {
                return var_decl;
            }

            Fun_Decl fun_decl = fun_decl_function();

            return fun_decl;

        }

        return null;

    }

    public Var_Decl var_decl_function() {
        if (tokens.peek() != null) {
            Var_Decl var_decl = new Var_Decl();
            Token type_spec = type_spec_function();
            var_decl.type_spec = type_spec;
            if (type_spec != null) {
                if (tokens.peek() != null && tokens.peek().getType().equals("ID")) {
                    var_decl.id = tokens.poll();
                    Var_Decl_Dash var_decl_dash = var_decl_dash_function();
                    if (var_decl_dash != null) {
                        var_decl.var_decl_dash = var_decl_dash;
                        return var_decl;
                    }

                }
            }
        }
        return null;

    }

    public Var_Decl_Dash var_decl_dash_function() {
        if (tokens.peek() != null) {
            if (tokens.peek().getValue().equals(";")) {
                Var_Decl_Dash1 var_decl_dash1 = new Var_Decl_Dash1();
                var_decl_dash1.simicolon = tokens.poll();
                return var_decl_dash1;
            }

            if (tokens.peek().getValue().equals("[")) {
                Var_Decl_Dash2 var_decl_dash2 = new Var_Decl_Dash2();
                var_decl_dash2.LB = tokens.poll();
                if (tokens.peek() != null && tokens.peek().getValue().equals("]")) {
                    var_decl_dash2.RB = tokens.poll();
                    if (tokens.peek() != null && tokens.peek().getValue().equals(";")) {
                        var_decl_dash2.simicolon = tokens.poll();
                        return var_decl_dash2;
                    }
                }
            }

        }
        return null;
    }

    public Token type_spec_function() {
        if (tokens.peek() != null) {
            if (tokens.peek().getType().equals("VOID") ||
                    tokens.peek().getType().equals("BOOL") ||
                    tokens.peek().getType().equals("INT") ||
                    tokens.peek().getType().equals("FLOAT")) {
                return tokens.poll();
            }
        }
        return null;

    }

    public Fun_Decl fun_decl_function() {

        return null;
    }


}
