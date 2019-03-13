import java.util.ArrayList;
import java.util.Arrays;

public class LexicalAnalyzer {

	private ArrayList<String> lexemes;
	private ArrayList<Token> tokens;
	private RegTable regTable;

	public LexicalAnalyzer(String code) {
		this.tokens = new ArrayList<Token>();
		this.lexemes = new ArrayList<String>(Arrays.asList(code.split("\\s+")));
		this.regTable = new RegTable();
	}

	public void tokenize() {
		for (int i = 0; i < lexemes.size(); i++)
			System.out.println(lexemes.get(i));
	}

}