package com.example.csg11s2.framework;

import com.example.csg11s2.util.FormatUnwrapper;

import static com.example.csg11s2.framework.Page.*;
import static com.example.csg11s2.util.FileOperations.*;
import static com.example.csg11s2.util.IBIO.*;

public class FrameworkMain {
    public static void main(String[] args) {
        resetProject();
//        System.out.println(FormatUnwrapper.wrapFormat(readFile("/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/bagels.html"), ""));

//        addPreexistingPagesFromMenu();
        String contents = "I +blike+ cheese and bagels \nI +ilike+ +dcheese+ \nI +ulike+ cheese";
        String contents2 = "I +blike+ cheese, +bnot+ bagels\nI +ihate+ +bbagels+ \nI +uhate+ bagels";
        Page bagelsPage = new Page("bagels", contents);
//        Page cheesePage = new Page("cheese", contents2);
//        Page goosePage = new Page("goose", "honk goes the goose \nhonk goes the goose \nhonk goes the goose");
//        Page dollsHouse = new Page("dollshouse", readFile("/Users/anniezhuang/Documents/csg11s2/src/main/resources/templates/sourcetext/dollshouse.txt"));

    }
}
