import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		FileHandler fileHandler= FileHandler.getInstance();
		String code = fileHandler.readInput();
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(code);
		ArrayList<Token>tokens=lexicalAnalyzer.generateTokens();
		fileHandler.writeOutput(tokens);

	}

}
