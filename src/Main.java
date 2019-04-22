import lexer.LexicalAnalyzer;
import lexer.Token;
import parser.Parser;
import utils.FileHandler;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		FileHandler fileHandler= FileHandler.getInstance();
		String code = fileHandler.readInput("files/input.txt");
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(code);
		ArrayList<Token>tokens=lexicalAnalyzer.generateTokens();
		fileHandler.writeOutput(tokens,"files/output.txt");
		Parser parser=new Parser(tokens);
		parser.parse();
		System.out.println(parser.tokens.size());


	}

}
