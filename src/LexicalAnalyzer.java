import java.util.ArrayList;
import java.util.Arrays;

public class LexicalAnalyzer {

	private String code;
	private ArrayList<Token> tokens;
	private RegTable regTable;

	public LexicalAnalyzer(String code) {
		this.tokens = new ArrayList<Token>();
		this.code = code;
		this.regTable = new RegTable();
	}

	public void tokenize() {
		tokens=regTable.tokenize(code);
		for(int i=0;i<tokens.size();i++) {
			System.out.println(tokens.get(i).toString());
		}
//		for (int i = 0; i < lexemes.size(); i++) {
//			System.out.println(lexemes.get(i));
////			regTable.tokenize(lexemes.get(i));
//		}
	}

}