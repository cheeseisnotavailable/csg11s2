package com.example.csg11s2.util;

public class FormatUnwrapper {

    public static String unwrapFormat(String contents, String format){
        MyQueue newLineIndices = new MyQueue();
        MyQueue tagIndices = new MyQueue();
        String[] paragraphs = contents.split("\n");

        String ret = "";
        String lastTag;
        int startingRetLength;

        for(String p:paragraphs){
            lastTag = "";
            startingRetLength = ret.length();
            p = "<p>"+p+"</p> ";


            int j = 0;
            for(int i = 0; i<p.length()-1; i++){
                if(p.charAt(i) == '+'){
                    lastTag = replaceWithHtmlTag(p.charAt(i+1));
                    ret += "<" + lastTag + ">";
                    j = i+2;
                    while(p.charAt(j) != '+' && j<p.length()){
                        ret += p.charAt(j);
                        j++;
                    }
                    ret += "</" + lastTag + ">";
                    i = j;
                }else{
                    ret += p.charAt(i);
                    j=i;
                }
            }

            if(startingRetLength == ret.length()){
                ret += p+"\n";
            }else{
                ret += "\n";
            }
        }

        return ret;
    }

    public static String wrapFormat(String htmlContents, String format) {
        htmlContents.substring(0,htmlContents.indexOf("<form"));
        StringBuilder ret = new StringBuilder();
        String[] paragraphs = htmlContents.split("<p>");
        String paragraph;

        for (int p = 1; p<paragraphs.length; p++) {
            if (paragraphs[p].trim().isEmpty()) {
                continue; // Skip empty paragraphs
            }
            paragraph = paragraphs[p];

            // Remove opening <p> tag and trim whitespace
            paragraph = paragraph.replace("<p>", "").trim();

            StringBuilder formattedParagraph = new StringBuilder();

            int i = 0;
            while (i < paragraph.length()) {
                if (paragraph.charAt(i) == '<') {
                    int closingTagStart = paragraph.indexOf('>', i);
                    int closingTagEnd = paragraph.indexOf('<', closingTagStart);

                    if (closingTagStart == -1 || closingTagEnd == -1) {
                        // Malformed HTML, skip processing
                        break;
                    }

                    String tag = paragraph.substring(i + 1, closingTagStart).trim();
                    String content = paragraph.substring(closingTagStart + 1, closingTagEnd).trim();

                    char azTag = replaceWithAzTag(tag);
                    formattedParagraph.append("+").append(azTag).append(content).append("+");

                    i = closingTagEnd + tag.length() + 3; // +3 accounts for "</" and ">"
                } else {
                    formattedParagraph.append(paragraph.charAt(i));
                    i++;
                }
            }
            ret.append(formattedParagraph).append("\n");
        }

        return ret.toString().trim();
    }

    public static char replaceWithAzTag(String htmlTag) {
        switch (htmlTag) {
            case "b":
                return 'b';
            case "h1":
                return 't';
            case "i":
                return 'i';
            case "del":
                return 's';
            case "sup":
                return 'u';
            case "sub":
                return 'd';
        }
        return htmlTag.charAt(0);
    }

    public static String replaceWithHtmlTag(char azTag){
        switch(azTag){
            case 'b':
                return "b";
            case 't':
                return "h1";
            case 'i':
                return "i";
            case 's':
                return "del";
            case 'u':
                return "sup";
            case 'd':
                return "sub";
        }
        return azTag+"";
    }
}
