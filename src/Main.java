import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lexer.LexicalAnalyzer;
import lexer.Token;
import parser.Parser;
import parser.rules.Program;
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
        String code = fileHandler.readInput("files/input.txt");
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(code);
        ArrayList<Token> tokens = lexicalAnalyzer.generateTokens();
        fileHandler.writeOutput(tokens, "files/output.txt");
        Parser parser = new Parser(tokens);
        Program program = parser.parse();
        Gson gson = new Gson();
        String json = gson.toJson(program);
        String page = "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "\n" +
                "<title>Tree Diagram</title>\n" +
                "\n" +
                "<link rel=\"stylesheet\" href=\"css/vtree.css\" type=\"text/css\"/>\n" +
                "\n" +
                "<script src=\"https://d3js.org/d3.v3.min.js\" charset=\"utf-8\"></script>\n" +
                "<script src=\"dist/vtree.js\"></script>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "var vt;\n" +
                "\n" +
                "window.onload = function () {\n" +
                "  var container = document.getElementById(\"container\");\n" +
                "  vt = new VTree(container);\n" +
                "  var reader = new VTree.reader.Object();\n" +
                "\n" +
                "  function updateTree() {\n" +
                "    var s = '" + json + "';\n" +
                "    var jsonData = JSON.parse(s);\n" +
                "\n" +
                "\n" +
                "    var data = reader.read(jsonData);\n" +
                "\n" +
                "    vt.data(data)\n" +
                "      .update();\n" +
                "  }\n" +
                "\n" +
                "  function createSvgString() {\n" +
                "    document.getElementById(\"svg-text\").value = vt.createSvgString();\n" +
                "  }\n" +
                "\n" +
                "  updateTree();\n" +
                "};\n" +
                "\n" +
                "</script>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "  <div class=\"container\">\n" +
                "    <div id=\"container\"></div>\n" +
                "  </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";
        File file = new File("./vtree/index.html");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(page);
            bw.close();
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
//		jaxbObjectToXML(program);
//		try (Writer writer = new FileWriter("program.json")) {
//			Gson gson = new GsonBuilder().create();
//			gson.toJson(program, writer);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

    }

}
