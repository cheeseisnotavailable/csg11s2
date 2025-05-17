package com.example.csg11s2.util;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IBIO {
    public IBIO() {
    }

    static void output(String info) {
        System.out.println(info);
    }

    static void output(char info) {
        System.out.println(info);
    }

    static void output(byte info) {
        System.out.println(info);
    }

    static void output(int info) {
        System.out.println(info);
    }

    static void output(long info) {
        System.out.println(info);
    }

    static void output(double info) {
        System.out.println(info);
    }

    static void output(boolean info) {
        System.out.println(info);
    }

    static String input(String prompt) {
        String inputLine = "";
        System.out.print(prompt);

        try {
            inputLine = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        } catch (Exception e) {
            String err = e.toString();
            System.out.println(err);
            inputLine = "";
        }

        return inputLine;
    }

    static String inputString(String prompt) {
        return input(prompt);
    }

    static String input() {
        return input("");
    }

    static int inputInt() {
        return inputInt("");
    }

    static double inputDouble() {
        return inputDouble("");
    }

    static char inputChar(String prompt) {
        char result = '\u0000';

        try {
            result = input(prompt).charAt(0);
        } catch (Exception var3) {
            result = '\u0000';
        }

        return result;
    }

    static byte inputByte(String prompt) {
        byte result = 0;

        try {
            result = Byte.valueOf(input(prompt).trim());
        } catch (Exception var3) {
            result = 0;
        }

        return result;
    }

    static int inputInt(String prompt) {
        int result = 0;

        try {
            result = Integer.valueOf(input(prompt).trim());
        } catch (Exception var3) {
            result = 0;
        }

        return result;
    }

    static long inputLong(String prompt) {
        long result = 0L;

        try {
            result = Long.valueOf(input(prompt).trim());
        } catch (Exception var4) {
            result = 0L;
        }

        return result;
    }

    static double inputDouble(String prompt) {
        double result = (double)0.0F;

        try {
            result = Double.valueOf(input(prompt).trim());
        } catch (Exception var4) {
            result = (double)0.0F;
        }

        return result;
    }

    static boolean inputBoolean(String prompt) {
        boolean result = false;

        try {
            result = Boolean.valueOf(input(prompt).trim());
        } catch (Exception var3) {
            result = false;
        }

        return result;
    }
}
