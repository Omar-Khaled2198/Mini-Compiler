import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		String code = "bool isPowerOfTwo(int x)/*fuck*/return x && (!(x & (x - 1)));}";
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(code);
		lexicalAnalyzer.tokenize();
	}

}
