package utils;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DiagramGenerator {

    private String page;

    public DiagramGenerator(String nodes){

        String nodeEscaped = escape(nodes);

        page="<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "\n" +
                "<title>Tree Diagram</title>\n" +
                "\n" +
                "<link rel=\"stylesheet\" href=\"css/vtree.css\" type=\"text/css\"/>\n" +
                "\n" +
                "<script src=\"dist/d3.v3.min.js\" charset=\"utf-8\"></script>\n" +
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
                "    var s = '" + nodeEscaped + "';\n" +
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
    }

    public void generate(){

        File file = new File("./vtree/index.html");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(page);
            bw.close();
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String escape(String string) {
        String escapes[][] = new String[][]{
                {"\\", "\\\\"},
                {"\"", "\\\""},
                {"\n", "\\n"},
                {"\r", "\\r"},
                {"\b", "\\b"},
                {"\f", "\\f"},
                {"\t", "\\t"}
        };
        for (String[] esc : escapes) {
            string = string.replace(esc[0], esc[1]);
        }
        return string;
    }
}
