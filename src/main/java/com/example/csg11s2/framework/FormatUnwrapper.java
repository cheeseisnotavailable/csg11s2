package com.example.csg11s2.framework;

import java.util.ArrayList;

import com.example.csg11s2.util.MyQueue;

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
//                    ret += p.substring(lastTagIndex, i);
                    //TODO: MAKE THIS A LOOKUP
                    lastTag = replaceHtmlTag(p.charAt(i+1));
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

    public static String replaceHtmlTag(char azTag){
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
