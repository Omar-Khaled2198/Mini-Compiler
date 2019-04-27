import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lexer.LexicalAnalyzer;
import lexer.Token;
import parser.Parser;
import parser.rules.Program;
import utils.DiagramGenerator;
import utils.FileHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        FileHandler fileHandler = FileHandler.getInstance();
        String code = fileHandler.readInput("files/input.c");
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(code);
        ArrayList<Token> tokens = lexicalAnalyzer.generateTokens();
        fileHandler.writeOutput(tokens, "files/output.txt");
        Parser parser = new Parser(tokens);
        Program program = parser.parse();
        Gson gson = new Gson();
        String programJSON = gson.toJson(program);
        DiagramGenerator diagramGenerator = new DiagramGenerator(programJSON);
        diagramGenerator.generate();

    }

}
