import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		//String code = "bool isPowerOfTwo(int x)/*fuck *x *y */return x && (!(x & (x - 1)));}";
		//String code = "int intvalue=10+5";
		String code = "if(x==5) /*fuck you*/ y=4 else y=5";
		LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(code);
		ArrayList<Token>tokens=lexicalAnalyzer.generateTokens();
		FileHandler fileHandler= FileHandler.getInstance();
		fileHandler.writeOutput(tokens);

	}

}
