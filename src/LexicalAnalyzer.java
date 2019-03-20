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
		table = fileHandler.readRegexTable();
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

			while (tokens.get(i).getStart() == tokens.get(i + 1).getStart()) {
				if (tokens.get(i).getEnd() < tokens.get(i + 1).getEnd())
					tokens.remove(i);
				else if (tokens.get(i).getEnd() > tokens.get(i + 1).getEnd())
					tokens.remove(i + 1);
				else
					break;
			}

			while (tokens.get(i).getStart() == tokens.get(i + 1).getStart()
					&& tokens.get(i).getEnd() == tokens.get(i + 1).getEnd()) {
				if (tokens.get(i).getType().equals("ID"))
					tokens.remove(i);
				else
					tokens.remove(i + 1);
			}

			while (tokens.get(i).getEnd() > tokens.get(i + 1).getStart()) {
				tokens.remove(i + 1);
			}
		}
		return tokens;
	}

}