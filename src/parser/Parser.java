package parser;

import lexer.Token;
import parser.rules.*;

import java.util.*;

public class Parser {

    public Deque<Token> tokens;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = new LinkedList<>(tokens);

    }

    public void parse() {
        Program program = program_function();
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
            if (type_spec != null) {
                var_decl.type_spec = type_spec;
                if (tokens.peek() != null && tokens.peek().getType().equals("ID")) {
                    var_decl.id = tokens.poll();
                    Var_Decl_Dash var_decl_dash = var_decl_dash_function();
                    if (var_decl_dash != null) {
                        var_decl.var_decl_dash = var_decl_dash;
                        return var_decl;
                    } else if (tokens.peek() == null || !tokens.peek().getValue().equals("(")) {
                        System.out.println("Error: ; is missing");
                    }

                } else {
                    System.out.println("Error: can't resolve '" + tokens.peek().getValue() + "'");
                }

            } else {
                System.out.println("Error: can't resolve '" + tokens.peek().getValue() + "'");
            }
            if (var_decl.id != null)
                tokens.addFirst(var_decl.id);
            if (var_decl.type_spec != null)
                tokens.addFirst(var_decl.type_spec);

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

                    } else {

                        System.out.println("Error: ; is missing");
                    }
                } else {

                    System.out.println("Error: ] is missing");
                }
                if (var_decl_dash2.RB != null)
                    tokens.addFirst(var_decl_dash2.RB);
                if (var_decl_dash2.LB != null)
                    tokens.addFirst(var_decl_dash2.LB);

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

                if (tokens.peek() != null && tokens.peek().getType().equals("ID")) {
                    fun_decl.id = tokens.poll();
                    if (tokens.peek() != null && tokens.peek().getValue().equals("(")) {
                        fun_decl.LB = tokens.poll();
                        Params params = params_function();
                        if (params != null) {
                            fun_decl.params = params;
                        }
                        if (tokens.peek() != null && tokens.peek().getValue().equals(")")) {
                            fun_decl.RB = tokens.poll();
                            Compound_Stmt compound_stmt = compound_stmt_function();
                            if (compound_stmt != null) {
                                fun_decl.compound_stmt = compound_stmt;
                                return fun_decl;
                            }
                        } else {

                            if (tokens.peek() != null)
                                System.out.println("Error: can't resolve '" + tokens.peek().getValue() + "'");
                            else
                                System.out.println("Error: ) is missing");
                        }

                    } else {

                    }
                } else {


                }

            }
            if (fun_decl.RB != null)
                tokens.addFirst(fun_decl.RB);
            if (fun_decl.LB != null)
                tokens.addFirst(fun_decl.LB);
            if (fun_decl.id != null)
                tokens.addFirst(fun_decl.id);
            if (fun_decl.type_spec != null)
                tokens.addFirst(fun_decl.type_spec);


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
            Param_List_Dash param_list_dash = new Param_List_Dash();
            if (tokens.peek().getValue().equals(",")) {
                param_list_dash.comma = tokens.poll();
                Param param = param_function();
                if (param != null) {
                    param_list_dash.param = param;
                    param_list_dash.param_list_dash = param_list_dash_function();
                    return param_list_dash;
                }
            }
            if (param_list_dash.comma != null)
                tokens.addFirst(param_list_dash.comma);
        }

        return null;

    }

    public Param param_function() {

        if (tokens.peek() != null) {

            Param param = new Param();
            Token type_spec = type_spec_function();
            if (type_spec != null) {

                param.type_spec = type_spec;
                if (tokens.peek() != null && tokens.peek().getType().equals("ID")) {
                    param.id = tokens.poll();
                    param.param_dash = param_dash_function();
                    return param;
                }

            }
            if (param.type_spec != null)
                tokens.addFirst(param.type_spec);


        }

        return null;

    }

    public Param_Dash param_dash_function() {

        if (tokens.peek() != null) {
            Param_Dash param_dash = new Param_Dash();
            if (tokens.peek().getValue().equals("[")) {
                param_dash.LS = tokens.poll();
                if (tokens.peek() != null && tokens.peek().getValue().equals("]")) {
                    param_dash.RS = tokens.poll();
                    return param_dash;
                } else {
                    System.out.println("Error: ] is missing");
                }
            }
            if (param_dash.LS != null)
                tokens.addFirst(param_dash.LS);
        }

        return null;

    }

    public Compound_Stmt compound_stmt_function() {
        if (tokens.peek() != null) {

            Compound_Stmt compound_stmt = new Compound_Stmt();

            if (tokens.peek().getValue().equals("{")) {

                compound_stmt.LC = tokens.poll();

                compound_stmt.local_decals = local_decals_function();

                if (tokens.peek() != null && tokens.peek().getValue().equals("}")) {
                    compound_stmt.RC = tokens.poll();
                    return compound_stmt;

                }
            }
            if (compound_stmt.LC != null)
                tokens.addFirst(compound_stmt.LC);

        } else {

            System.out.println("Error: {} is missing");
        }

        return null;
    }

    public Local_Decals local_decals_function() {

        if (tokens.peek() != null) {
            Local_Decals local_decals = new Local_Decals();
            local_decals.local_decals_dash = local_decals_dash_function();
            return local_decals;
        }

        return null;

    }

    public Local_Decals_Dash local_decals_dash_function() {

        if (tokens.peek() != null) {
            Local_Decals_Dash local_decals_dash = new Local_Decals_Dash();
            Local_Decal local_decal = local_decal_function();
            if (local_decal != null) {
                local_decals_dash.local_decal = local_decal;
                local_decals_dash.local_decals_dash = local_decals_dash_function();
                return local_decals_dash;
            }
        }

        return null;

    }

    public Local_Decal local_decal_function() {

        if (tokens.peek() != null) {
            Token type_spec = type_spec_function();
            Local_Decal local_decal = new Local_Decal();
            if (type_spec != null) {
                local_decal.type_spec = type_spec;
                if (tokens.peek() != null && tokens.peek().getType().equals("ID")) {
                    local_decal.id = tokens.poll();
                    Local_Decal_Dash local_decal_dash = local_decal_dash_function();
                    if (local_decal_dash != null) {
                        local_decal.local_decal_dash = local_decal_dash;
                        return local_decal;
                    }
                } else {
                    System.out.println("Error: can't resolve '" + tokens.peek().getValue() + "'");
                }

            }

            if (local_decal.id != null)
                tokens.addFirst(local_decal.id);
            if (local_decal.type_spec != null)
                tokens.addFirst(local_decal.type_spec);
        }


        return null;

    }

    public Local_Decal_Dash local_decal_dash_function() {

        if (tokens.peek() != null) {
            if (tokens.peek().getValue().equals(";")) {
                Local_Decal_Dash1 local_decal_dash1 = new Local_Decal_Dash1();
                local_decal_dash1.simicolon = tokens.poll();

                return local_decal_dash1;
            }

            if (tokens.peek().getValue().equals("[")) {
                Local_Decal_Dash2 local_decal_dash2 = new Local_Decal_Dash2();
                local_decal_dash2.LB = tokens.poll();
                if (tokens.peek() != null && tokens.peek().getValue().equals("]")) {
                    local_decal_dash2.RB = tokens.poll();
                    if (tokens.peek() != null && tokens.peek().getValue().equals(";")) {
                        local_decal_dash2.simicolon = tokens.poll();
                        return local_decal_dash2;

                    } else {

                        System.out.println("Error: ; is missing");
                    }
                } else {

                    System.out.println("Error: ] is missing");
                }
                if (local_decal_dash2.RB != null)
                    tokens.addFirst(local_decal_dash2.RB);
                if (local_decal_dash2.LB != null)
                    tokens.addFirst(local_decal_dash2.LB);

            } else {
                System.out.println("Error: ; is missing");
            }
        }
        return null;

    }

    public Expr expr_function() {

        if(tokens.peek()!=null){
            Token token = tokens.peek();

            if(token.getType().equals("ID")){
                Expr1 expr1 = new Expr1();
                expr1.id=tokens.poll();
                expr1.expr_d_dash=expr_d_dash_function();
                expr1.expr_q_dash=expr_q_dash_function();
                return expr1;
            }

            if(token.getValue().equals("!")||token.getValue().equals("-")||token.getValue().equals("+")){
                Expr2 expr2 = new Expr2();
                expr2.op=tokens.poll();
                expr2.expr_q_dash=expr_q_dash_function();
                return expr2;
            }

            if(token.getValue().equals("(")){
                Expr3 expr3 = new Expr3();
                expr3.LS=tokens.poll();
                Expr expr = expr_function();
                if(expr!=null){
                    expr3.expr=expr;
                    if(tokens.peek()!=null&&tokens.peek().getValue().equals(")")){
                        expr3.RS=tokens.poll();
                        expr3.expr_q_dash=expr_q_dash_function();
                        return expr3;
                    }
                }
                return null;
            }

            if(token.getType().equals("INTEGRAL_LITERAL")||
                    token.getType().equals("FlOAT_LITERAL")||
                    token.getType().equals("CHAT_LITERAL")||
                    token.getType().equals("STRING_LITERAL")||
                    token.getType().equals("BOOL_LITERAL")){
                Expr4 expr4 = new Expr4();
                expr4.literal=tokens.poll();
                expr4.expr_q_dash=expr_q_dash_function();
                return expr4;

            }

            if(token.getValue().equals("new")){
                Expr5 expr5 = new Expr5();
                expr5.New=tokens.poll();
                Token type_spec = type_spec_function();
                if(type_spec!=null){
                    expr5.type_spec=type_spec;
                    if(tokens.peek()!=null&&tokens.peek().getValue().equals("(")){
                        expr5.LB=tokens.poll();
                        Expr expr = expr_function();
                        if(expr!=null){
                            expr5.expr=expr;
                            if(tokens.peek()!=null&&tokens.peek().getValue().equals(")")){
                                expr5.RB=tokens.poll();
                                expr5.expr_q_dash=expr_q_dash_function();
                                return expr5;
                            }
                        }
                    }
                }
                return null;
            }
        }

        return null;

    }

    public Expr_S_Dash expr_s_dash_function() {

        if (tokens.peek() != null) {
            Expr_S_Dash expr_s_dash = new Expr_S_Dash();
            if (tokens.peek().getValue().equals("=")) {
                expr_s_dash.Eq = tokens.poll();
                expr_s_dash.expr = expr_function();
                return expr_s_dash;
            }
        }

        return null;
    }

    public Expr_D_Dash expr_d_dash_function() {

        if (tokens.peek() != null) {
            Token token = tokens.peek();
            if (token.getValue().equals("[")) {
                Expr_D_Dash1 expr_d_dash1 = new Expr_D_Dash1();
                expr_d_dash1.LB = tokens.poll();
                Expr expr = expr_function();
                if (expr != null) {
                    expr_d_dash1.expr = expr;
                    if (tokens.peek() != null&&tokens.peek().getValue().equals("]")) {
                        expr_d_dash1.RB = tokens.poll();
                        expr_d_dash1.expr_s_dash = expr_s_dash_function();
                        return expr_d_dash1;
                    }

                    return null;
                }
            }


            if (token.getValue().equals("=")) {
                Expr_D_Dash2 expr_d_dash2 = new Expr_D_Dash2();
                expr_d_dash2.Eq = tokens.poll();
                Expr expr = expr_function();
                if (expr != null) {
                    expr_d_dash2.expr = expr;
                    return expr_d_dash2;
                }

                return null;

            }

            if (token.getValue().equals("(")) {
                Expr_D_Dash3 expr_d_dash3 = new Expr_D_Dash3();
                expr_d_dash3.LS = tokens.poll();
                expr_d_dash3.args = args_function();
                if (tokens.peek() != null && tokens.peek().getValue().equals(")")) {
                    expr_d_dash3.RS = tokens.poll();
                    return expr_d_dash3;
                }

                return null;
            }

            if(token.getValue().equals(".")){
                Expr_D_Dash4 expr_d_dash4 = new Expr_D_Dash4();
                expr_d_dash4.dot=tokens.poll();
                if(tokens.peek()!=null&&tokens.peek().getValue().equals("size")){
                    expr_d_dash4.size=tokens.poll();
                    return expr_d_dash4;
                }

                return null;
            }

        }

        return null;

    }

    public Expr_T_Dash expr_t_dash_function() {

        if (tokens.peek() != null) {
            Token token = tokens.peek();
            Expr_T_Dash expr_t_dash = new Expr_T_Dash();
            if (token.getType().equals("OR") ||
                    token.getType().equals("AND") ||
                    token.getType().equals("EQUAL") ||
                    token.getType().equals("NOT_EQUAL") ||
                    token.getType().equals("LESSTHAN") ||
                    token.getType().equals("GREATERTHAN") ||
                    token.getType().equals("LESS_EQ") ||
                    token.getType().equals("GREATER_EQ") ||
                    token.getType().equals("PLUS") ||
                    token.getType().equals("MINUS") ||
                    token.getType().equals("DIVIDE") ||
                    token.getType().equals("MULTIPLY") ||
                    token.getType().equals("MOD")) {
                expr_t_dash.op = tokens.poll();
                Expr expr = expr_function();
                if (expr != null) {
                    expr_t_dash.expr = expr;
                    return expr_t_dash;
                }
            }
        }

        return null;

    }

    public Expr_Q_Dash expr_q_dash_function() {

        if (tokens.peek() != null) {
            Expr_Q_Dash expr_q_dash = new Expr_Q_Dash();
            Expr_T_Dash expr_t_dash = expr_t_dash_function();
            if (expr_t_dash != null) {
                expr_q_dash.expr_t_dash = expr_t_dash;
                expr_q_dash.expr_q_dash = expr_q_dash_function();
                return expr_q_dash;
            }
        }
        return null;
    }

    public Args args_function() {

        if(tokens.peek()!=null){

        }

        return null;
    }


}
