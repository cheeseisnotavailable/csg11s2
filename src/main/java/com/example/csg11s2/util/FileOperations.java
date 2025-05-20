package com.example.csg11s2.util;

import com.example.csg11s2.framework.Page;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileOperations {

    // Simulated database for HTML files
    private static Map<String, String> htmlFileDatabase = new HashMap<>();

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
                data+=(line+"\n");
                line = reader.readLine();
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        return data;
    }

    static String htmlHeader = "<!DOCTYPE HTML>\n" + "<html xmlns:th=\"http://www.thymeleaf.org\">\n";

    //TODO: FilePath!!!!
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
    static String menuPath = "/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/menu.html";

    public static void writeToController(String title, String requestParam, String attributeName){

        String contentsAsClass = "    @GetMapping(\"/"+ title +"\")\n" +
                "    public String "+ title +"(@RequestParam("+requestParam+") String "+attributeName+", Model model){\n" +
//                "        model.addAttribute(\"show\", show);\n" +
                "        return \""+ title +"\";}";

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

    // Function to add HTML file to the database
    public static void addHtmlFile(String title, String content) {
        htmlFileDatabase.put(title, content);
        writeOver(title + ".html", content, title); // Save to file
        writeToMenu(title); // Update menu
    }

    // Function to delete HTML file from the database and filesystem
    public static void deleteHtmlFile(String title) {
        htmlFileDatabase.remove(title);
        deleteFile(title + ".html"); // Delete from filesystem
    }

    // Function to delete a file from the filesystem
    //TODO: this doesn't work
    public static void deleteFile(String fileName) {
        try {
            Files.deleteIfExists(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void removeReferencesFromFile(String title, String filePath) {
        List<String> listOfFile = new ArrayList<>();
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                if (!line.contains(title)) {
                    listOfFile.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the updated controller back to the file
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String l : listOfFile) {
                writer.append(l);
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void deleteReferencesToPage(Page page){
        removeReferencesFromFile(page.getTitle(), menuPath);
        removeReferencesFromFile(page.getTitle(), pageControllerPath);
        deleteFile(page.getTitle());
    }

    public static void standardFileWrite(String[] contents, String filepath){
        try (FileWriter writer = new FileWriter(filepath)) {
            for (String l : contents) {
                writer.append(l);
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addPreexistingPagesFromMenu(){
        List<String> listOfFile = new ArrayList<>();
        String line = "";
        String title;

        try {
            BufferedReader br = new BufferedReader(new FileReader(menuPath));
            while ((line = br.readLine()) != null) {
                title = "";
                if (line.contains("<a href=")) {
                    for(int i=13; i<line.length(); i++){
                        if(line.charAt(i) == '>'){
                            i = 10000;
                        }else{
                            title += line.charAt(i);
                        }
                    }
                    Page.addPage(title);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String returnHtmlContents(String title){
        return readFile("/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/"+title+".html");
    }

}
