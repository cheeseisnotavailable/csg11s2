package com.example.csg11s2.framework;

public class Page {
    protected String title;
    protected String filePath;
    protected String link;
    protected String contentsTemporary;
    protected String format;

    public Page(String title, String filePath, String link, String contentsTemporary) {
        this.title = title;
        this.filePath = filePath;
        this.link = link;
        this.contentsTemporary = contentsTemporary += " ";
    }

    public String display(){
        return FormatUnwrapper.unwrapFormat(contentsTemporary, format);
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

    public String getContentsTemporary() {
        return contentsTemporary;
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

    public void setContentsTemporary(String contentsTemporary) {
        this.contentsTemporary = contentsTemporary;
    }
}
