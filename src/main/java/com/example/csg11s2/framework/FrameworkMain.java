package com.example.csg11s2.framework;

public class FrameworkMain {
    public static void main(String[] args) {
    String contents = "I +blike+ cheese and bagels \nI +ilike+ +dcheese+ \nI +ulike+ cheese";
        Page testPage = new Page("bagels", "eee", "lll", contents);
        System.out.println(testPage.display());
    }
}
