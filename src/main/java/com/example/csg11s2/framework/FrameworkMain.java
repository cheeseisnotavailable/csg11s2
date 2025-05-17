package com.example.csg11s2.framework;

import static com.example.csg11s2.util.FileOperations.writeToController;

public class FrameworkMain {
    public static void main(String[] args) {
        writeToController("testpage", "name = \"show\", required=false, defaultValue=\"\"", "attName");

        String contents = "I +blike+ cheese and bagels \nI +ilike+ +dcheese+ \nI +ulike+ cheese";
        Page testPage = new Page("bagels", "testpage.html", "lll", contents);
        testPage.display();
        System.out.println("\n"+testPage.toString());
    }
}
