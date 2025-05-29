package com.example.csg11s2.util;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FormatUnwrapper {

    public static String unwrapFormat(String contents, String format){
        MyQueue newLineIndices = new MyQueue();
        MyQueue tagIndices = new MyQueue();
        String[] paragraphs = contents.split("/n");

        String ret = "";
        int lastTagIndex;
        String lastTag;
        int startingRetLength;

        for(String p:paragraphs){
            lastTagIndex = 0;
            lastTag = "";
            startingRetLength = ret.length();
            p = "<p>"+p+"</p> ";


            int j = 0;
            for(int i = 0; i<p.length()-1; i++){
                if(p.charAt(i) == '#'){
//                    ret += p.substring(lastTagIndex, i);
                    lastTag = replaceWithHtmlTag(p.charAt(i+1));
                    ret += "<" + lastTag + ">";
                    j = i+2;
                    while(j<p.length() && p.charAt(j) != '+'){
                        ret += p.charAt(j);
                        j++;
                    }
                    ret += "</" + lastTag + ">";
                    i = j;
                    lastTagIndex = j;
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
        htmlContents.replace("+"," ");
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
                    formattedParagraph.append("~").append(azTag).append(content).append("~");

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

    public static String unwrapWebRepsonse(String webInput){
        String ret = webInput.replace("+"," ");
        return unwrapFormat(ret, "");
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

    public static String decodeURL(String sourceText){

        // Regular expression to match percent-encoded characters (%XX)
        Pattern pattern = Pattern.compile("%[0-9A-Fa-f]{2}");
        Matcher matcher = pattern.matcher(sourceText);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            // Decode the matched percent-encoded substring
            String encoded = matcher.group();
            String decoded;
            try {
                decoded = URLDecoder.decode(encoded, StandardCharsets.UTF_8.name());
            } catch (Exception e) {
                decoded = encoded; // If decoding fails, keep the original
            }
            matcher.appendReplacement(result, decoded);
        }

        // Append the rest of the input string
        matcher.appendTail(result);

        // Output the final result
        return result.toString();
    }
}
