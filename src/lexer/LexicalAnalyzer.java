package lexer;

import utils.FileHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {

	private String code;
	private ArrayList<Token> tokens;
	private Map<String, String> table;

	public LexicalAnalyzer(String code) {

		this.tokens = new ArrayList<Token>();
		this.code = code;
		FileHandler fileHandler = FileHandler.getInstance();
		table = fileHandler.readRegexTable("files/regex.txt");
	}

	public ArrayList<Token> generateTokens() {

		for (Map.Entry<String, String> entry : table.entrySet()) {

			Pattern pattern = Pattern.compile(entry.getValue());
			Matcher matcher = pattern.matcher(code);

			while (matcher.find()) {
				Token token = new Token(entry.getKey(), matcher.group(), matcher.start(), matcher.end());
				tokens.add(token);
			}
		}

		Collections.sort(tokens);

		for (int i = 0; i < tokens.size() - 1; i++) {
			
			while (i+1<tokens.size()&&tokens.get(i).getStart() == tokens.get(i + 1).getStart()
					&& tokens.get(i).getEnd() == tokens.get(i + 1).getEnd()) {
				if (tokens.get(i).getType().equals("ID"))
					tokens.remove(i);
				else
					tokens.remove(i + 1);
			}
			
			while (i+1<tokens.size()&&tokens.get(i).getStart() == tokens.get(i + 1).getStart()) {
				if (tokens.get(i).getEnd() < tokens.get(i + 1).getEnd())
					tokens.remove(i);
				else if (tokens.get(i).getEnd() > tokens.get(i + 1).getEnd())
					tokens.remove(i + 1);
			}

			

			while (i+1<tokens.size()&&tokens.get(i).getEnd() > tokens.get(i + 1).getStart()) {
				tokens.remove(i + 1);
			}
			
		
			if(i+1<tokens.size()&&tokens.get(i+1).getStart()-tokens.get(i).getEnd()>1) {
				Pattern pattern = Pattern.compile("\\S+");
				Matcher matcher = pattern.matcher(code.substring(tokens.get(i).getEnd(), tokens.get(i+1).getStart()));
				while (matcher.find()) {
					System.out.println("Undefined: "+ matcher.group());
					System.exit(0);
				}
			}

		}
		return tokens;
	}

}