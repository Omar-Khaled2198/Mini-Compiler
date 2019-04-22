package parser;

import lexer.Token;
import parser.rules.*;

import java.util.*;

public class Parser {

    public Deque<Token> tokens;
    public Stack<Token> stack;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = new LinkedList<>(tokens);
        this.stack = new Stack<>();


    }

    public void parse() {
        Program program = program_function();
        // System.out.println(program);
//        program.printNode();
        //program_function();

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

            int stack_size = stack.size();
            Var_Decl var_decl = var_decl_function();

            if (var_decl != null) {
                return var_decl;
            }

            for (int i = 0; i <= stack.size() - stack_size; i++) {
                tokens.addFirst(stack.pop());
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
            if (type_spec != null) {
                var_decl.type_spec = type_spec;
                stack.push(var_decl.type_spec);
                if (tokens.peek() != null && tokens.peek().getType().equals("ID")) {
                    var_decl.id = tokens.poll();
                    stack.push(var_decl.id);
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
                stack.push(var_decl_dash1.simicolon);
                return var_decl_dash1;
            }

            if (tokens.peek().getValue().equals("[")) {
                Var_Decl_Dash2 var_decl_dash2 = new Var_Decl_Dash2();
                var_decl_dash2.LB = tokens.poll();
                stack.push(var_decl_dash2.LB);
                if (tokens.peek() != null && tokens.peek().getValue().equals("]")) {
                    var_decl_dash2.RB = tokens.poll();
                    stack.push(var_decl_dash2.RB);
                    if (tokens.peek() != null && tokens.peek().getValue().equals(";")) {
                        var_decl_dash2.simicolon = tokens.poll();
                        stack.push(var_decl_dash2.simicolon);
                        return var_decl_dash2;

                    } else {

                        System.out.println("Error: ; is missing");
                    }
                } else {

                    System.out.println("Error: ] is missing");
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
        if (tokens.peek() != null) {
            Fun_Decl fun_decl = new Fun_Decl();
            Token type_spec = type_spec_function();

            if (type_spec != null) {
                fun_decl.type_spec = type_spec;
                stack.push(fun_decl.type_spec);

                if (tokens.peek() != null && tokens.peek().getType().equals("ID")) {
                    fun_decl.id = tokens.poll();
                    stack.push(fun_decl.id);
                    if (tokens.peek() != null && tokens.peek().getValue().equals("(")) {
                        fun_decl.LB = tokens.poll();
                        stack.push(fun_decl.LB);
                        Params params = params_function();
                        if (params != null) {
                            fun_decl.params = params;
                        }
                        if (tokens.peek() != null && tokens.peek().getValue().equals(")")) {
                            fun_decl.RB = tokens.poll();
                            stack.push(fun_decl.RB);
                            Compound_Stmt compound_stmt = compound_stmt_function();
                            if (compound_stmt != null) {
                                fun_decl.compound_stmt = compound_stmt;
                                return fun_decl;
                            }
                        } else {

                            System.out.println("Error: ) is missing");
                        }

                    } else {

                        System.out.println("Error: can't resolve '" + tokens.peek().getValue() + "'");
                    }
                } else {

                    System.out.println("Error: can't resolve '" + tokens.peek().getValue() + "'");
                }

            } else {
                System.out.println("Error5: can't resolve '" + tokens.peek().getValue() + "'");
            }
        }

        return null;
    }

    public Params params_function() {
        if (tokens.peek() != null) {
            Param_List param_list = param_list_function();
            if (param_list != null) {
                Params1 params1 = new Params1();
                params1.param_list = param_list;
                return params1;
            }

            if (tokens.peek().getType().equals("VOID")) {
                Params2 params2 = new Params2();
                params2.Void = tokens.poll();
                stack.push(params2.Void);
                return params2;
            }

        }

        return null;
    }

    public Param_List param_list_function() {
        if (tokens.peek() != null) {
            Param param = param_function();
            if (param != null) {
                Param_List param_list = new Param_List();
                param_list.param = param;
                param_list.param_list_dash = param_list_dash_function();
                return param_list;
            }
        }

        return null;

    }

    public Param_List_Dash param_list_dash_function() {
        if (tokens.peek() != null) {
            if (tokens.peek().getValue().equals(",")) {
                Param_List_Dash param_list_dash = new Param_List_Dash();
                param_list_dash.comma = tokens.poll();
                stack.push(param_list_dash.comma);
                Param param = param_function();
                if (param != null) {
                    param_list_dash.param = param;
                    param_list_dash.param_list_dash = param_list_dash_function();
                    return param_list_dash;
                }
            }
        }

        return null;

    }

    public Param param_function() {

        if (tokens.peek() != null) {

            Param param = new Param();
            Token type_spec = type_spec_function();
            if (type_spec != null) {

                param.type_spec = type_spec;
                stack.push(param.type_spec);
                if (tokens.peek() != null && tokens.peek().getType().equals("ID")) {
                    param.id = tokens.poll();
                    stack.push(param.id);
                    param.param_dash = param_dash_function();
                    return param;
                }
            }


        }

        return null;

    }

    public Param_Dash param_dash_function() {

        if (tokens.peek() != null) {
            Param_Dash param_dash = new Param_Dash();
            if (tokens.peek().getValue().equals("[")) {
                param_dash.LS = tokens.poll();
                stack.push(param_dash.LS);
                if (tokens.peek() != null && tokens.peek().getValue().equals("]")) {
                    param_dash.RS = tokens.poll();
                    stack.push(param_dash.RS);
                    return param_dash;
                } else {
                    System.out.println("Error: ] is missing");
                }
            }
        }

        return null;

    }

    public Compound_Stmt compound_stmt_function() {
        if (tokens.peek() != null) {
            Compound_Stmt compound_stmt = new Compound_Stmt();
            if (tokens.peek().getValue().equals("{")) {
                compound_stmt.LC = tokens.poll();
                stack.push(compound_stmt.LC);
                if (tokens.peek() != null && tokens.peek().getValue().equals("}")) {
                    compound_stmt.RC = tokens.poll();
                    stack.push(compound_stmt.RC);
                    return compound_stmt;
                } else {
                    System.out.println("Error: } is missing");
                }
            }
        }

        return null;
    }


}
