import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		FileHandler fileHandler= FileHandler.getInstance();
		String code = fileHandler.readInput();
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(code);
		ArrayList<Token>tokens=lexicalAnalyzer.generateTokens();
		fileHandler.writeOutput(tokens);


	}

}
