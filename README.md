# Mini C Compiler
Compilers can be decomposed into several phases, each of which converts one form of source program into another
There are different phases of compiler as follow:
 - **Lexical analysis (Implemented)**
 - **Syntax analysis (Implemenetd)**
 - Semantic analysis
 - Intermediate code generation
 - Code optimization
 - Code generation

## Lexical analysis

 Source program is scanned to read the stream of characters and those characters are grouped to form a sequence called lexemes which produces token as output.
- **Token:**  Token is a sequence of characters that represent lexical unit, which matches with the pattern, such as keywords, operators, identifiers etc.
- **Lexeme:**  Lexeme is instance of a token i.e., group of characters forming a token.
- **Pattern:**  Pattern describes the rule that the lexemes of a token takes. It is the structure that must be matched by strings.

    ....
    ....
    SINGLE_COMMENT \/\/.+
    MULTI_COMMENT \/\*(.|\n)*?\\*\/ ?
    INTEGRAL_LITERAL \b0|[1-9][0-9]*\b
    FlOAT_LITERAL [0-9]+\.[0-9]+
    CHAT_LITERAL \'[a-zA-z0-9]\'
    STRING_LITERAL \".*\"
    ...
    ...
    
## Syntax Analysis
Syntax analysis is the second phase of compiler which is also called as parsing.
- Parser converts the tokens produced by lexical analyzer into a tree like representation called parse tree.
- A parse tree describes the syntactic structure of the input.

Parser follow production rules (**Grammer**) defined by means of [context free grammar](https://en.wikipedia.org/wiki/Context-free_grammar) (CFG). 
Grammer ex:

    program -> decl_list  
      
    decl_list -> decl decl_list'  
    decl_list' -> decl decl_list' | ϵ  
    decl -> var_decl | fun_decl  
      
    var_decl -> type_spec IDENT var_decl'  
    var_decl' -> ; | [ ] ;  
      
    type_spec -> VOID  
                | BOOL  
                | INT  
                | FLOAT
	...
	...
    ...

The way the production rules are implemented divides parsing into two types : top-down parsing and bottom-up parsing. I use [Recursive descent parser](https://www.geeksforgeeks.org/compiler-design-recursive-descent-parser/) is a kind of top-down parser.

## Example
**Input**

    int main(){  
        int x;  
    }
 **Genereted Tokens** from Lexical analyzer
 

    <INT>: int  
    <ID>: main  
    <LEFT_ROUND_B>: (  
    <RIGHT_ROUND_B>: )  
    <LEFT_CURLY_B>: {  
    <INT>: int  
    <ID>: x  
    <SEMICOLON>: ;  
    <RIGHT_CURLY_B>: }
   **Genereted Parse Tree** from Syntax Analyzer
   ![Parse Tree](parse_tree.png?raw=true)

