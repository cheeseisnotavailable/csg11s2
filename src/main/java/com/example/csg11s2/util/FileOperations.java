package com.example.csg11s2.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileOperations {

    //Read a file from the path and return it
    public static String readFile(String path){
        Path file = Path.of(path);
        //Charset charset = Charset.forName("US-ASCII");
        Charset charset = StandardCharsets.UTF_8; //Unicode!
        String data = "";
        try {
            BufferedReader reader = Files.newBufferedReader(file, charset);
            String line = reader.readLine();
            while (line != null) {
                data+=(line);
                line = reader.readLine();
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return data;
    }

    static String htmlHeader = "<!DOCTYPE HTML>\n" + "<html xmlns:th=\"http://www.thymeleaf.org\">\n";

    public static void writeOver(String fileName, String newContent){

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeOver(String fileName, String newContent, String title){

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append(htmlHeader);
            writer.append("<head>\n" +
                    "    <title>"+title+"</title>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                    "</head>\n<body>\n<a href=\"/menu\">Menu</a>\n");
            writer.append(newContent);
            writer.append("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void addToFile(String fileName, String newContent){
        ArrayList<String> listOfFile = new ArrayList<String>();
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != "</body></html>") {
                listOfFile.add(line);
            }
        } catch (IOException e) {
            IBIO.output("Exception!");
        }

        try {
            listOfFile.add(newContent);
            try (FileWriter writer = new FileWriter(fileName)) {
                for (String l : listOfFile) {
                    writer.append(l);
                    writer.append("\n");
                }
                writer.append("</body></html>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String fileToString(String fileName){
        String line = "";
        String ret = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                ret += line +"\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void printFile(String fileName){
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            IBIO.output("Exception!");
        }
    }

    static String pageControllerPath = "/Users/anniezhuang/Documents/csg11s2/src/main/java/com/example/csg11s2/PageController.java";

    public static void writeToController(String title, String requestParam, String attributeName){

        String contentsAsClass = "    @GetMapping(\"/"+ title +"\")\n" +
                "    public String "+ title +"(@RequestParam("+requestParam+") String "+attributeName+", Model model){\n" +
//                "        model.addAttribute(\"show\", show);\n" +
                "        return \""+ title +"\";\n" +
                "    }";

        ArrayList<String> listOfFile = new ArrayList<String>();
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(pageControllerPath));
            while ((line = br.readLine()) != null) {
                listOfFile.add(line);
            }
        } catch (IOException e) {
            IBIO.output("Exception!");
        }

        try {
            listOfFile.remove(listOfFile.size()-1);
            listOfFile.add(contentsAsClass);
            try (FileWriter writer = new FileWriter(pageControllerPath)) {
                for (String l : listOfFile) {
                    writer.append(l);
                    writer.append("\n");
                }
                writer.append("}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeToMenu(String title){
        ArrayList<String> listOfFile = new ArrayList<String>();
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/menu.html"));
            while ((line = br.readLine()) != null) {
                listOfFile.add(line);
            }
        } catch (IOException e) {
            IBIO.output("Exception!");
        }

        try {
            listOfFile.remove(listOfFile.size()-1);
            listOfFile.remove(listOfFile.size()-1);
            listOfFile.add("<p> <a href=/"+title+"> "+title+"</a> </p>");
            try (FileWriter writer = new FileWriter("/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/menu.html")) {
                for (String l : listOfFile) {
                    writer.append(l);
                    writer.append("\n");
                }
                writer.append("</body>\n</html>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
