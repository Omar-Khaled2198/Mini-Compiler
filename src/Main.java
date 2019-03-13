public class Main {

	public static void main(String[] args) {
		String code = "int intvalue = 10+5;";
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(code);
		lexicalAnalyzer.tokenize();
	}

}
