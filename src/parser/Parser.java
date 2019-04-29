package parser;

import com.sun.org.apache.xpath.internal.Arg;
import lexer.Token;
import parser.rules.*;

import java.util.*;

public class Parser {

    private Queue<Token> tokens;

    public Parser(ArrayList<Token> tokens) {
        for(int i=0;i<tokens.size();i++){
            if(tokens.get(i).getType().equals("SINGLE_COMMENT")||tokens.get(i).getType().equals("MULTI_COMMENT")){
                tokens.remove(i);
                i--;
            }
        }
        this.tokens = new LinkedList<>(tokens);

    }

    public Program parse() {
        Program program = program_function();
        return  program;
    }

    private Program program_function() {

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

    private Decl_List decl_list_function() {

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

    private Decl_List_Dash decl_list_dash_function() {
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

    private Decl decl_function() {
        if (tokens.peek() != null) {

            Token type_spec = type_spec_function();
            Token id;
            if (type_spec != null) {
                if (tokens.peek() != null && tokens.peek().getType().equals("ID")) {
                    id = tokens.poll();
                } else {
                    System.out.println("Error79: can't resolve '" + tokens.peek().getValue() + "'");
                    System.exit(0);
                    return null;
                }

            } else {
                System.out.println("Error85: can't resolve '" + tokens.peek().getValue() + "'");
                System.exit(0);
                return null;
            }
            if (tokens.peek() != null && !tokens.peek().getValue().equals("(")) {
                Var_Decl var_decl = var_decl_function();
                if (var_decl != null) {
                    var_decl.type_spec = type_spec;
                    var_decl.id = id;
                    return var_decl;
                }
            }

            if (tokens.peek() != null && tokens.peek().getValue().equals("(")) {

                Fun_Decl fun_decl = fun_decl_function();
                if (fun_decl != null) {
                    fun_decl.type_spec = type_spec;
                    fun_decl.id = id;
                    return fun_decl;
                }

            }

            System.out.println("Error110: ; is missing");
            System.exit(0);

        }


        return null;

    }

    private Var_Decl var_decl_function() {
        if (tokens.peek() != null) {
            Var_Decl var_decl = new Var_Decl();
            Var_Decl_Dash var_decl_dash = var_decl_dash_function();
            if (var_decl_dash != null) {
                var_decl.var_decl_dash = var_decl_dash;
                return var_decl;
            }

        }
        return null;

    }

    private Var_Decl_Dash var_decl_dash_function() {
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

                        System.out.println("Error154: ; is missing");
                        System.exit(0);

                    }
                } else {

                    System.out.println("Error160: ] is missing");
                    System.exit(0);
                }

            } else {
                System.out.println("Error165: ; is missing");
                System.exit(0);
            }
        }
        System.out.println("Error169: ; is missing");
        System.exit(0);
        return null;
    }

    private Token type_spec_function() {
        if (tokens.peek() != null) {
            if (tokens.peek().getType().equals("VOID") ||
                    tokens.peek().getType().equals("BOOL") ||
                    tokens.peek().getType().equals("INT") ||
                    tokens.peek().getType().equals("FLOAT")||
                    tokens.peek().getType().equals("STRING")||
                    tokens.peek().getType().equals("CHAR")) {
                return tokens.poll();
            }
        }
        return null;

    }

    private Fun_Decl fun_decl_function() {
        if (tokens.peek() != null) {
            Fun_Decl fun_decl = new Fun_Decl();

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
                    System.out.println("Error205: ) is missing");
                    System.exit(0);
                }
            }


        }

        return null;
    }

    private Params params_function() {
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

    private Param_List param_list_function() {
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

    private Param_List_Dash param_list_dash_function() {
        if (tokens.peek() != null) {
            Param_List_Dash param_list_dash = new Param_List_Dash();
            if (tokens.peek().getValue().equals(",")) {
                param_list_dash.comma = tokens.poll();
                Param param = param_function();
                if (param != null) {
                    param_list_dash.param = param;
                    param_list_dash.param_list_dash = param_list_dash_function();
                    return param_list_dash;
                } else {
                    System.out.println("Error252: can't resolve ',' ");
                    System.exit(0);
                }
            }

        }

        return null;

    }

    private Param param_function() {

        if (tokens.peek() != null) {

            Param param = new Param();
            Token type_spec = type_spec_function();
            if (type_spec != null) {

                param.type_spec = type_spec;
                if (tokens.peek() != null && tokens.peek().getType().equals("ID")) {
                    param.id = tokens.poll();
                    param.param_dash = param_dash_function();
                    return param;
                } else {
                    System.out.println("Error287: can't resolve '"+tokens.peek().getValue()+"'");
                }

            }


        }

        return null;

    }

    private Param_Dash param_dash_function() {

        if (tokens.peek() != null) {
            Param_Dash param_dash = new Param_Dash();
            if (tokens.peek().getValue().equals("[")) {
                param_dash.LS = tokens.poll();
                if (tokens.peek() != null && tokens.peek().getValue().equals("]")) {
                    param_dash.RS = tokens.poll();
                    return param_dash;
                } else {
                    System.out.println("Error309: ] is missing");
                    System.exit(0);
                }
            }

        }

        return null;

    }

    private Stmt_List stmt_list_function() {
        if (tokens.peek() != null) {
            Stmt_List stmt_list = new Stmt_List();
            stmt_list.stmt_list_dash = stmt_list_dash_function();
            return stmt_list;
        }

        return null;
    }

    private Stmt_List_Dash stmt_list_dash_function() {
        if (tokens.peek() != null) {
            Stmt_List_Dash stmt_list_dash = new Stmt_List_Dash();
            Stmt stmt = stmt_function();
            if (stmt != null) {
                stmt_list_dash.stmt = stmt;
                stmt_list_dash.stmt_list_dash = stmt_list_dash_function();
                return stmt_list_dash;
            }
        }

        return null;

    }

    private Stmt stmt_function() {

        if (tokens.peek() != null) {
            if (tokens.peek().getValue().equals("{")) {
                return compound_stmt_function();
            }

            if (tokens.peek().getValue().equals("if")) {

                return if_stmt_function();
            }

            if (tokens.peek().getValue().equals("while")) {
                return while_stmt_function();
            }

            if (tokens.peek().getValue().equals("return")) {
                return return_stmt_function();
            }

            if (tokens.peek().getValue().equals("break")) {
                Break_Stmt break_stmt = new Break_Stmt();
                break_stmt.Break = tokens.poll();
                if(tokens.peek()!=null&&tokens.peek().getValue().equals(";")){
                    break_stmt.simicolon=tokens.poll();
                    return break_stmt;
                } else {
                    System.out.println("Error373: ; is missing");
                    System.exit(0);
                }

            }

            Local_Decals local_decals = local_decals_function();

            if(local_decals!=null){
                return local_decals;
            }


            Expr_Stmt expr_stmt = expr_stmt_function();
            if(expr_stmt!=null){
                return expr_stmt;
            }

//            System.out.println("Error: can't resolve '"+tokens.peek().getValue()+"'");
//            System.exit(0);
        }

        return null;

    }

    private Return_Stmt return_stmt_function() {

        if (tokens.peek() != null) {
            if (tokens.peek().getValue().equals("return")) {
                Return_Stmt return_stmt = new Return_Stmt();
                return_stmt.Return = tokens.poll();
                Return_Stmt_Dash return_stmt_dash = return_stmt_dash_function();
                if (return_stmt_dash != null) {
                    return_stmt.return_stmt_dash = return_stmt_dash;
                    return return_stmt;
                }
            }
        }

        return null;

    }

    private Return_Stmt_Dash return_stmt_dash_function() {

        if (tokens.peek() != null) {
            if (tokens.peek().getValue().equals(";")) {
                Return_Stmt_Dash1 return_stmt_dash1 = new Return_Stmt_Dash1();
                return_stmt_dash1.simicolon = tokens.poll();
                return return_stmt_dash1;
            } else {
                Expr expr = expr_function();
                Return_Stmt_Dash2 return_stmt_dash2 = new Return_Stmt_Dash2();
                if (expr != null) {
                    return_stmt_dash2.expr = expr;
                    if (tokens.peek() != null && tokens.peek().getValue().equals(";")) {
                        return_stmt_dash2.simicolon = tokens.poll();
                        return return_stmt_dash2;
                    }
                }
            }
        }

        System.out.println("Error416: ; is missing");
        System.exit(0);
        return null;
    }

    private Expr_Stmt expr_stmt_function() {

        if (tokens.peek() != null) {
            if (tokens.peek().getValue().equals(";")) {
                Expr_Stmt2 expr_stmt2 = new Expr_Stmt2();
                expr_stmt2.simicolon = tokens.poll();
                return expr_stmt2;
            } else {
                Expr expr = expr_function();
                Expr_Stmt1 expr_stmt1 = new Expr_Stmt1();
                if (expr != null) {
                    expr_stmt1.expr = expr;
                    if (tokens.peek() != null && tokens.peek().getValue().equals(";")) {
                        expr_stmt1.simicolon = tokens.poll();
                        return expr_stmt1;
                    } else {

                        System.out.println("Error444: ; is missing");
                        System.exit(0);
                    }
                }
            }
        }


        return null;

    }

    private While_Stmt while_stmt_function() {

        if (tokens.peek() != null) {
            if (tokens.peek().getValue().equals("while")) {
                While_Stmt while_stmt = new While_Stmt();
                while_stmt.While = tokens.poll();
                if (tokens.peek() != null && tokens.peek().getValue().equals("(")) {
                    while_stmt.LS = tokens.poll();
                    Expr expr = expr_function();
                    if (expr != null) {
                        while_stmt.expr = expr;
                        if (tokens.peek() != null && tokens.peek().getValue().equals(")")) {
                            while_stmt.RS = tokens.poll();
                            Stmt stmt = stmt_function();
                            if (stmt != null) {
                                while_stmt.stmt = stmt;
                                return while_stmt;
                            }
                        } else {
                            System.out.println("Error466: ) is missing");
                            System.exit(0);
                        }
                    } else {
                        System.out.println("Error472: expression can't be empty");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Error471: ( is missing");
                    System.exit(0);
                }
            }
        }

        return null;

    }

    private Compound_Stmt compound_stmt_function() {
        if (tokens.peek() != null) {

            Compound_Stmt compound_stmt = new Compound_Stmt();

            if (tokens.peek().getValue().equals("{")) {

                compound_stmt.LC = tokens.poll();

                //compound_stmt.local_decals = local_decals_function();

                compound_stmt.stmt_list = stmt_list_function();

                if (tokens.peek() != null && tokens.peek().getValue().equals("}")) {
                    compound_stmt.RC = tokens.poll();
                    return compound_stmt;

                } else {
                    System.out.println("Error498: can't resolve '"+tokens.peek().getValue()+"'");
                    System.exit(0);
                }
            }


        } else {

            System.out.println("Error506: {} is missing");
        }

        return null;
    }

    private If_Stmt if_stmt_function() {
        if (tokens.peek() != null) {
            if (tokens.peek().getValue().equals("if")) {
                If_Stmt if_stmt = new If_Stmt();
                if_stmt.If = tokens.poll();
                if (tokens.peek() != null && tokens.peek().getValue().equals("(")) {
                    if_stmt.LS = tokens.poll();
                    Expr expr = expr_function();
                    if (expr != null) {
                        if_stmt.expr = expr;
                        if (tokens.peek() != null && tokens.peek().getValue().equals(")")) {
                            if_stmt.RS = tokens.poll();
                            Stmt stmt = stmt_function();
                            if (stmt != null) {
                                if_stmt.stmt = stmt;
                                if_stmt.if_stmt_dash = if_stmt_dash_function();
                                return if_stmt;
                            }
                        } else {
                            System.out.println("Error531: ) is missing");
                            System.exit(0);
                        }
                    }
                    else {
                        System.out.println("Error537: expression can't be empty");
                        System.exit(0);
                    }

                } else {
                    System.out.println("Error537: ( is missing");
                    System.exit(0);
                }
            }
        }

        return null;
    }

    private If_Stmt_Dash if_stmt_dash_function() {

        if (tokens.peek() != null) {
            if (tokens.peek().getValue().equals("else")) {
                If_Stmt_Dash if_stmt_dash = new If_Stmt_Dash();
                if_stmt_dash.Else = tokens.poll();
                Stmt stmt = stmt_function();
                if (stmt != null) {
                    if_stmt_dash.stmt = stmt;
                    return if_stmt_dash;
                }
            }
        }

        return null;

    }

    private Local_Decals local_decals_function() {

        if (tokens.peek() != null) {
            Local_Decals local_decals = new Local_Decals();
            Local_Decals_Dash local_decals_dash = local_decals_dash_function();
            if(local_decals_dash!=null){
                local_decals.local_decals_dash=local_decals_dash;
                return local_decals;
            }

        }

        return null;

    }

    private Local_Decals_Dash local_decals_dash_function() {

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

    private Local_Decal local_decal_function() {

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
                    System.out.println("Error607: can't resolve '" + tokens.peek().getValue() + "'");
                    System.exit(0);
                }

            }

        }


        return null;

    }

    private Local_Decal_Dash local_decal_dash_function() {

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

                        System.out.println("Error641: ; is missing");
                        System.exit(0);
                    }
                } else {

                    System.out.println("Error646: ] is missing");
                    System.exit(0);
                }

            } else {
                System.out.println("Error651: ; is missing");
                System.exit(0);
            }
        }
        System.out.println("Error655: ; is missing");
        System.exit(0);
        return null;

    }

    private Expr expr_function() {

        if (tokens.peek() != null) {
            Token token = tokens.peek();

            if (token.getType().equals("ID")) {
                Expr1 expr1 = new Expr1();
                expr1.id = tokens.poll();
                expr1.expr_d_dash = expr_d_dash_function();
                expr1.expr_q_dash = expr_q_dash_function();
                return expr1;
            }

            if (token.getValue().equals("!") || token.getValue().equals("-") || token.getValue().equals("+")) {
                Expr2 expr2 = new Expr2();
                expr2.op = tokens.poll();
                Expr expr = expr_function();
                if(expr!=null){
                    expr2.expr=expr;
                    expr2.expr_q_dash = expr_q_dash_function();
                    return expr2;
                }

            }

            if (token.getValue().equals("(")) {
                Expr3 expr3 = new Expr3();
                expr3.LS = tokens.poll();
                Expr expr = expr_function();
                if (expr != null) {
                    expr3.expr = expr;
                    if (tokens.peek() != null && tokens.peek().getValue().equals(")")) {
                        expr3.RS = tokens.poll();
                        expr3.expr_q_dash = expr_q_dash_function();
                        return expr3;
                    }
                }
                return null;
            }

            if (token.getType().equals("INTEGRAL_LITERAL") ||
                    token.getType().equals("FlOAT_LITERAL") ||
                    token.getType().equals("CHAT_LITERAL") ||
                    token.getType().equals("STRING_LITERAL") ||
                    token.getType().equals("BOOL_LITERAL")) {
                Expr4 expr4 = new Expr4();
                expr4.literal = tokens.poll();
                expr4.expr_q_dash = expr_q_dash_function();
                return expr4;

            }

            if (token.getValue().equals("new")) {
                Expr5 expr5 = new Expr5();
                expr5.New = tokens.poll();
                Token type_spec = type_spec_function();
                if (type_spec != null) {
                    expr5.type_spec = type_spec;
                    if (tokens.peek() != null && tokens.peek().getValue().equals("[")) {
                        expr5.LB = tokens.poll();
                        Expr expr = expr_function();
                        if (expr != null) {
                            expr5.expr = expr;
                            if (tokens.peek() != null && tokens.peek().getValue().equals("]")) {
                                expr5.RB = tokens.poll();
                                expr5.expr_q_dash = expr_q_dash_function();
                                return expr5;
                            } else {
                                System.out.println("Error724: ] is missing");
                                System.exit(0);
                            }
                        } else {
                            System.out.println("Error728: expression can't be empty");
                            System.exit(0);
                        }
                    } else {
                        System.out.println("Error732: [ is missing");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Error736: can't resolve '"+token.getValue()+"'");
                    System.exit(0);
                }
                return null;
            }
        }

        return null;

    }

    private Expr_S_Dash expr_s_dash_function() {

        if (tokens.peek() != null) {
            Expr_S_Dash expr_s_dash = new Expr_S_Dash();
            if (tokens.peek().getValue().equals("=")) {
                expr_s_dash.Eq = tokens.poll();
                Expr expr = expr_function();
                if(expr!=null) {
                    expr_s_dash.expr = expr_function();
                    return expr_s_dash;
                } else {
                    System.out.println("Error758: expression can't be empty");
                    System.exit(0);
                }
            }
        }

        return null;
    }

    private Expr_D_Dash expr_d_dash_function() {

        if (tokens.peek() != null) {
            Token token = tokens.peek();
            if (token.getValue().equals("[")) {
                Expr_D_Dash1 expr_d_dash1 = new Expr_D_Dash1();
                expr_d_dash1.LB = tokens.poll();
                Expr expr = expr_function();
                if (expr != null) {
                    expr_d_dash1.expr = expr;
                    if (tokens.peek() != null && tokens.peek().getValue().equals("]")) {
                        expr_d_dash1.RB = tokens.poll();
                        expr_d_dash1.expr_s_dash = expr_s_dash_function();
                        return expr_d_dash1;
                    } else {
                        System.out.println("Error782: ] is missing");
                        System.exit(0);
                    }

                    return null;
                } else {
                    System.out.println("Error788: expression can't be empty");
                    System.exit(0);
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
                } else {
                    System.out.println("Error815: ) is missing");
                    System.exit(0);
                }

                return null;
            }

            if (token.getValue().equals(".")) {
                Expr_D_Dash4 expr_d_dash4 = new Expr_D_Dash4();
                expr_d_dash4.dot = tokens.poll();
                if (tokens.peek() != null && tokens.peek().getValue().equals("size")) {
                    expr_d_dash4.size = tokens.poll();
                    return expr_d_dash4;
                } else {
                    System.out.println("Error829: function is missing");
                    System.exit(0);
                }

                return null;
            }

        }

        return null;

    }

    private Expr_T_Dash expr_t_dash_function() {

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
                    token.getType().equals("MOD")||
                    token.getType().equals("BITWISE_AND")||
                    token.getType().equals("BITWISE_OR")||
                    token.getType().equals("BITWISE_NOT")) {
                expr_t_dash.op = tokens.poll();
                Expr expr = expr_function();
                if (expr != null) {
                    expr_t_dash.expr = expr;
                    return expr_t_dash;
                } else {
                    System.out.println(tokens);
                    System.out.println("Error866: expression can't be empty");
                    System.exit(0);
                }
            }
        }

        return null;

    }

    private Expr_Q_Dash expr_q_dash_function() {

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

    private Args args_function() {

        if (tokens.peek() != null) {
            Args args = new Args();
            args.args_list = args_list_function();
            return args;
        }

        return null;
    }

    private Args_List args_list_function() {

        if (tokens.peek() != null) {
            Args_List args_list = new Args_List();
            Expr expr = expr_function();
            if (expr != null) {
                args_list.expr = expr;
                args_list.args_list_dash = args_list_dash_function();
                return args_list;
            }
        }

        return null;
    }

    private Args_List_Dash args_list_dash_function() {

        if (tokens.peek() != null) {
            if (tokens.peek().getValue().equals(",")) {
                Args_List_Dash args_list_dash = new Args_List_Dash();
                args_list_dash.comma = tokens.poll();
                Expr expr = expr_function();
                if (expr != null) {
                    args_list_dash.expr = expr;
                    args_list_dash.args_list_dash = args_list_dash_function();
                    return args_list_dash;
                } else {
                    System.out.println("Error928: arguments is missing");
                    System.exit(0);
                }
            }
        }

        return null;

    }


}
