import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileHandler {

	private static FileHandler fileHandler = null;

	private String regex = "files/regex.txt";
	private String input = "files/input.txt";
	private String output = "files/output.txt";

	public static FileHandler getInstance() {
		if (fileHandler == null)
			fileHandler = new FileHandler();

		return fileHandler;
	}

	public Map<String, String> readRegexTable() {

		Map<String, String> table = new HashMap<String, String>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(regex));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] splited = line.split(" ");
			table.put(splited[0], splited[1]);
		}
		scanner.close();
		return table;
	}

	public void writeOutput(ArrayList<Token> tokens) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(new File(output));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
		for (int i = 0; i < tokens.size(); i++) {
			try {
				bufferedWriter.write(tokens.get(i).toString());
				bufferedWriter.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readInput() {

		String input = "";
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(this.input));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (scanner.hasNextLine()) {
			input += scanner.nextLine()+"\n";
		}
		scanner.close();
		return input;
	}
}
