package com.example.csg11s2.framework;

import com.example.csg11s2.util.FormatUnwrapper;

import java.util.ArrayList;

import static com.example.csg11s2.util.FileOperations.*;

public class Page {
    protected String title;
    protected String filePath;
    protected String link;
    protected String format;

    public Page(String title, String contentsTemporary) {
        if(!allTitles.contains(title)){
            allTitles.add(title);
            this.title = title;
            this.filePath = "/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/"+title+".html";
            writeOverHtml(filePath, FormatUnwrapper.unwrapFormat(contentsTemporary, format), title);
            writeToController(title, "name = \"show\", required=false, defaultValue=\"\"", "attName");
            writeToMenu(title);
            allPages.add(this);
        }else{
//            throw new IllegalArgumentException(title+" ALREADY TAKEN!");
            System.out.println(title+" ALREADY TAKEN!");
        }
    }

    public Page(String title){
        if(!allTitles.contains(title)){
            allTitles.add(title);
            this.title = title;
            this.filePath = "/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/"+title+".html";
        }else{
            throw new IllegalArgumentException("TITLE ALREADY TAKEN!");
        }
    }

    public String toString(){
        return readFile(filePath);
    }

    public void display(){
        printFile(filePath);
    }

    public void addToPage(String newContent){
        addToFile(filePath,newContent);
    }

    public void rewritePage(String newContent){
        writeOver(filePath,newContent);
    }

    public String getFormat(){
        return format;
    }

    public String getTitle() {
        return title;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getLink() {
        return link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setLink(String link) {
        this.link = link;
    }

    static ArrayList<String> allTitles = new ArrayList<>();
    static ArrayList<Page> allPages = new ArrayList<>();

//    public static void addTitle(String title){
//        //TODO???
//        allTitles.add(title);
//    }
    public static void addPage(String title){
        allTitles.add(title);
        allPages.add(new Page(title, returnHtmlContents(title)));
    }

    public static void clearAllTitles(){
        for(Page p:allPages){
            deleteReferencesToPage(p);
        }
        allTitles.clear();
        allPages.clear();
    }

}
