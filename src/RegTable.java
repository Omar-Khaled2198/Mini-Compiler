import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTable {

	private Map<String, String> table;

	public RegTable() {
		table = new HashMap<String, String>();
		table.put("NEW", "\\bnew\\b");
		table.put("WHILE", "\\bwhile\\b");
		table.put("INT", "\\bint\\b");
		table.put("STRING", "\\bString\\b");
		table.put("FLOAT", "\\bfloat\\b");
		table.put("CHARACTER", "\\bchar\\b");
		table.put("BOOL", "\\bbool\\b");
		table.put("IF", "\\bif\\b");
		table.put("ELSE", "\\belse\\b");
		table.put("MAIN", "\\bmain\\b");
		table.put("PRIVATE", "\\bprivate\\b");
		table.put("CLASS", "\\bclass\\b");
		table.put("COMMENT", "/\\*(.|[\\r\\n])*\\*/");
		table.put("INTEGRAL_LITERAL", "\\b[1-9][0-9]*\\b");
		table.put("STRING_LITERAL", "(\".*?(?<!\\\\)\")");
		table.put("TRUE", "\\btrue\\b");
		table.put("FALSE", "\\bfalse\\b");
		table.put("VOID", "\\bvoid\\b");
		table.put("PUBLIC", "\\bpublic\\b");
		table.put("PROTECTED", "\\bprotected\\b");
		table.put("static", "\\bstatic\\b");
		table.put("EXTERN", "\\bextern\\b");
		table.put("REGISTER", "\\bregister\\b");
		table.put("RETURN", "\\breturn\\b");
		table.put("ABSTRACT", "\\babstract\\b");
		table.put("BREAK", "\\bbreak\\b");
		table.put("CONTINUE", "\\bcontinue\\b");
		table.put("BYTE", "\\bbyte\\b");
		table.put("CASE", "\\bcase\\b");
		table.put("CATCH", "\\bcatch\\b");
		table.put("CONST", "\\bconst\\b");
		table.put("DEFAULT", "\\bdefault\\b");
		table.put("DO", "\\bdo\\b");
		table.put("DOUBLE", "\\bdouble\\b");
		table.put("ENUM", "\\benum\\b");
		table.put("FINAL", "\\bfinal\\b");
		table.put("FINALLY", "\\bfinally\\b");
		table.put("FOR", "\\bfor\\b");
		table.put("GOTO", "\\bgoto\\b");
		table.put("IMPLEMENTS", "\\bimplements\\b");
		table.put("IMPORT", "\\bimport\\b");
		table.put("PACKAGE", "\\bpackage\\b");
		table.put("INSTANCEOF", "\\binstanceof\\b");
		table.put("INTERFACE", "\\binterface\\b");
		table.put("SHORT", "\\bshort\\b");
		table.put("SUPER", "\\bsuper\\b");
		table.put("SWITCH", "\\bswitch\\b");
		table.put("THROW", "\\bthrow\\b");
		table.put("TRY", "\\btry\\b");
		table.put("PLUS", "\\+");
		table.put("ASSIGNMENT", "\\=");
		table.put("EQUAL", "\\==");
		table.put("LEFT_CURLY_B", "\\{");
		table.put("RIGHT_CURLY_B", "\\}");
		table.put("LEFT_SQUARE_B", "\\[");
		table.put("RIGHT_SQUARE_B", "\\]");
		table.put("LEFT_ROUND_B", "\\(");
		table.put("RIGHT_ROUND_B", "\\)");
		table.put("COMMA", ",");
		table.put("SEMICOLON", ";");
		table.put("DOT", "\\.");
		table.put("NOT", "!");
		table.put("PREPROCESSOR", "#");
		table.put("NOT_EQUAL", "\\b!=\\b");
		table.put("AND", "\\b\\&&\\b");
		table.put("OR", "\\b\\|\\|\\b");
		table.put("MINUS", "\\-");
		table.put("DIVIDE", "\\/");
		table.put("MULTIPLY", "\\*");
		table.put("MOD", "\\%");
		table.put("LESS_THAN", "<");
		table.put("GREATER_THAN", ">");
		table.put("LESS_EQ", "\\b<=\\b");
		table.put("GREATER_EQ", "\\b>=\\b");
		table.put("ID", "[_a-zA-Z][_a-zA-Z0-9]{0,30}");
		table.put("EOL", "\\n");
	}

	public ArrayList<Token> tokenize(String code) {
		ArrayList<Token> tokens=new ArrayList<Token>();
		for (Map.Entry<String, String> entry : table.entrySet()) {
			
			Pattern pattern = Pattern.compile(entry.getValue());
			Matcher matcher = pattern.matcher(code);
			
			while (matcher.find()) {
				Token token=new Token(entry.getKey(),matcher.group(),matcher.start(),matcher.end());
				tokens.add(token);
			}
		}
		Collections.sort(tokens);
		for(int i=0;i<tokens.size()-1;i++) {
			while(tokens.get(i).getStart()==tokens.get(i+1).getStart()) {
				if(tokens.get(i).getEnd()<tokens.get(i+1).getEnd())
					tokens.remove(i);
				else if(tokens.get(i).getEnd()>tokens.get(i+1).getEnd())
					tokens.remove(i+1);
				else
					break;
			}
			while(tokens.get(i).getStart()==tokens.get(i+1).getStart()
					&&tokens.get(i).getEnd()==tokens.get(i+1).getEnd()) {
				if(tokens.get(i).getType().equals("ID"))
					tokens.remove(i);
				else
					tokens.remove(i+1);
			}
			while(tokens.get(i).getEnd()>tokens.get(i+1).getStart()) {
				tokens.remove(i+1);
			}	
		}
		return tokens;
	}

}
