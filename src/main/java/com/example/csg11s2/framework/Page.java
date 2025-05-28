package com.example.csg11s2.framework;

import com.example.csg11s2.util.FormatUnwrapper;

import java.sql.SQLException;

import static com.example.csg11s2.util.FileOperations.*;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;

@DatabaseTable(tableName = "page")
public class Page {

    @DatabaseField(generatedId = true, columnName = "id")
    protected int id;

    @DatabaseField(canBeNull = false)
    protected String title;

    @DatabaseField(canBeNull = false)
    protected String filePath;

    protected String link;
    protected String format;

    static int currIds = 0;

    public Page(){

    }

    public Page(String title, String contentsTemporary) {
        this.id = currIds;
        currIds ++;
        try {
            initiateDBifNot();

            if(!pageDao.idExists(currIds)){
                this.title = title;
                this.filePath = "/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/"+title+".html";
                writeOverHtml(filePath, FormatUnwrapper.unwrapFormat(contentsTemporary, format), title);
                writeToController(title, "name = \"show\", required=false, defaultValue=\"\"", "attName");
                writeToMenu(title);
                System.out.println(title+" added as page");
            }else{
//            throw new IllegalArgumentException(title+" ALREADY TAKEN!");
                System.out.println(title+" ALREADY TAKEN!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Page(String title){
        this.id = currIds;
        currIds ++;
        try {
            if(!pageDao.idExists(currIds)){
                this.title = title;
                this.filePath = "/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/"+title+".html";
                writeOverHtml(filePath, FormatUnwrapper.unwrapFormat("", format), title);
                writeToController(title, "name = \"show\", required=false, defaultValue=\"\"", "attName");
                writeToMenu(title);
                System.out.println(title+" added as page");
            }else{
//            throw new IllegalArgumentException(title+" ALREADY TAKEN!");
                System.out.println(title+" ALREADY TAKEN!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    public int getId(){
        return this.id;
    }


    public static boolean hasInitiated = false;
    static ConnectionSource connectionSource;
    static String databaseUrl = "jdbc:h2:file:./database/page.db";
    static Dao<Page, Integer> pageDao;



    public static void addPageToDatabase(String title, String contentsTemporary, int currId) throws SQLException {

        initiateDBifNot();
        TableUtils.createTableIfNotExists(connectionSource, Page.class);

        // Search for the user in the db with id 1
        Page p1 = pageDao.queryForId(currId);
        if (p1 == null) {
            // create our one and only user
            p1 = new Page(title, contentsTemporary);

            // persist the account object to the database
            pageDao.create(p1);
            pageDao.update(p1);
        }
    }

    public static void deletePageFromDatabase(int id) throws SQLException {
        Page p1 = pageDao.queryForId(id);
        if(p1 != null){
            pageDao.delete(p1);
            pageDao.update(p1);
            System.out.println(id+" page deleted.");
        }
    }

    public static void initiateDBifNot(){
        if(!hasInitiated){

            try {
                connectionSource = new JdbcConnectionSource(databaseUrl);
                pageDao = DaoManager.createDao(connectionSource, Page.class);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            hasInitiated = true;
        }
    }



    public static Page newPage(String title, String contents){
        Page p = new Page(title, contents);
        return p;
    }



}
