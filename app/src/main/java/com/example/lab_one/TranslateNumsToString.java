package com.example.lab_one;

import android.content.Intent;

public class TranslateNumsToString {
    private static String hundreds(char num){ //вариации сотен 1xx ... 9xx
        switch (num){
            case '0': return "";
            case '1': return "сто";
            case '2': return "двести";
            case '3': return "тристо";
            case '4': return "четыресто";
            case '5': return "пятьсот";
            case '6': return "шестьсот";
            case '7': return "семьсот";
            case '8': return "восемьсот";
            case '9': return "девятьсот";
        }
        return null;
    }
    private static String tens(char num){ //вариации десятков 1x, 2x ... 9x
        switch (num){
            case '0': return "";
            case '1': return "десять";
            case '2': return "двадцать";
            case '3': return "тридцать";
            case '4': return "сорок";
            case '5': return "пятьдесят";
            case '6': return "шестьдесят";
            case '7': return "семьдесят";
            case '8': return "восемьдесят";
            case '9': return "девяносто";
        }
        return null;
    }

    private static String tensWithOneX(char num){ //вариации десятков вида 11 ... 19 | в параметры передаётся цифра из единичного разряда
        switch (num){
            case '0': return "десять";
            case '1': return "одиннадцать";
            case '2': return "двенадцать";
            case '3': return "тринадцать";
            case '4': return "четырнадцать";
            case '5': return "пятнадцать";
            case '6': return "шестнадцать";
            case '7': return "семнадцать";
            case '8': return "восемнадцать";
            case '9': return "девятнадцать";
        }
        return null;
    }

    private static String oneNum(char num){ //вариации единичных цифр 0 ... 9
        switch (num){
            case '0': return "нуль";
            case '1': return "один";
            case '2': return "два";
            case '3': return "три";
            case '4': return "четыре";
            case '5': return "пять";
            case '6': return "шесть";
            case '7': return "семь";
            case '8': return "восемь";
            case '9': return "девять";
        }
        return null;
    }

    private static String oneNumThousands(char num){ //вариации единичных цифр 0 ... 9
        switch (num){
            case '0': return "нуль";
            case '1': return "одна";
            case '2': return "две";
            case '3': return "три";
            case '4': return "четыре";
            case '5': return "пять";
            case '6': return "шесть";
            case '7': return "семь";
            case '8': return "восемь";
            case '9': return "девять";
        }
        return null;
    }

    private static String thousands (char numMid, char numLit, int amountOfNumbers){ //Вариации склонения тысячи, зависит от единичного разряда 1 - тысяча | 2, 3, 4 - тысячи | 5 ... xxx - тысяч
        if (amountOfNumbers == 2 && numMid == '1')
            return "тысяч";
        switch (numLit){
            case '0': return amountOfNumbers > 1 ? "тысяч" : "";
            case '1': return "тысяча";
            case '2': case '3': case '4': return "тысячи";
            case '5': case '6': case '7': case '8': case '9': return "тысяч";
        }
        return null;
    }

    private static String millions (char num, int amountOfNumbers){
        if (amountOfNumbers > 1)
            return "миллионов";
        switch (num){
            case '0': return "";
            case '1': return "миллион";
            case '2': case '3': case '4': return "миллиона";
            case '5': case '6': case '7': case '8': case '9': return "миллионов";
        }
        return null;
    }

    private static String translateLittleNum(String num){
        String translated = "";
        if (num.length() == 3){
            translated += TranslateNumsToString.hundreds(num.charAt(0));
            if (num.charAt(1) == '1')
                translated += " " + TranslateNumsToString.tensWithOneX(num.charAt(2));
            else
            if (num.charAt(1) > '1') {
                translated += " " + TranslateNumsToString.tens(num.charAt(1));
                if (num.charAt(2) != '0')
                    translated += " " + TranslateNumsToString.oneNum(num.charAt(2));
            }else
            if (num.charAt(2) != '0')
                translated += " " +  TranslateNumsToString.oneNum(num.charAt(2));
        }else {
            if (num.length() == 2) {
                if (num.charAt(0) == '1')
                    translated += TranslateNumsToString.tensWithOneX(num.charAt(1));
                else if (num.charAt(0) > '1') {
                    translated += TranslateNumsToString.tens(num.charAt(0));
                    if (num.charAt(1) != '0')
                        translated += " " + TranslateNumsToString.oneNum(num.charAt(1));
                } else
                if (num.charAt(1) != '0')
                    translated += " " + TranslateNumsToString.oneNum(num.charAt(1));
            }
            else
                translated += TranslateNumsToString.oneNum(num.charAt(0));
        }
        return translated;
    }

    private static String[] translateMillions(String num, String nameOfNum){
        String millions = "", t = "";
        for (int i = 0; i < num.length()-6; i++)
            millions += num.charAt(i);
        for (int i = millions.length(); i < num.length(); i++)
            t += num.charAt(i);
        num = t;
        String tThous = TranslateNumsToString.millions(millions.charAt(millions.length()-1), millions.length());
        if (millions.length() > 1 )
            nameOfNum += translateLittleNum(millions)+ " ";
        nameOfNum += tThous;
        String array[]  = {num, nameOfNum};
        return array;
    }

    private static String[] translateThousands(String num, String nameOfNum){
        String thousands = "", t = "";
        for (int i = 0; i < num.length()-3; i++)
            thousands += num.charAt(i);
        for (int i = thousands.length(); i < num.length(); i++)
            t += num.charAt(i);
        num = t;
        char numMid = ( thousands.length() > 1 ? thousands.charAt(thousands.length()-2) : '*'), numLit = thousands.charAt(thousands.length()-1);
        String tThous = TranslateNumsToString.thousands(numMid, numLit, thousands.length());
        if (thousands.length() > 1 || thousands.charAt(0) > '2')
            nameOfNum += translateLittleNumThousands(thousands)+ " ";
        else
        if (thousands.charAt(0) == '2')
            nameOfNum += "две ";
        nameOfNum += tThous;
        String array[]  = {num, nameOfNum};
        return array;
    }

    public static String translateLittleNumThousands(String num){
        String translated = "";
        if (num.length() == 3){
            translated += TranslateNumsToString.hundreds(num.charAt(0));
            if (num.charAt(1) == '1')
                translated += " " + TranslateNumsToString.tensWithOneX(num.charAt(2));
            else
            if (num.charAt(1) > '1') {
                translated += " " + TranslateNumsToString.tens(num.charAt(1));
                if (num.charAt(2) != '0')
                    translated += " " + TranslateNumsToString.oneNumThousands(num.charAt(2));
            }else
            if (num.charAt(2) != '0')
                translated += " " +  TranslateNumsToString.oneNumThousands(num.charAt(2));
        }else {
            if (num.length() == 2) {
                if (num.charAt(0) == '1')
                    translated += TranslateNumsToString.tensWithOneX(num.charAt(1));
                else if (num.charAt(0) > '1') {
                    translated += TranslateNumsToString.tens(num.charAt(0));
                    if (num.charAt(1) != '0')
                        translated += " " + TranslateNumsToString.oneNumThousands(num.charAt(1));
                } else
                if (num.charAt(1) != '0')
                    translated += " " + TranslateNumsToString.oneNumThousands(num.charAt(1));
            }
            else
                translated += TranslateNumsToString.oneNumThousands(num.charAt(0));
        }
        return translated;
    }

    public static String translate(String num){
        String nameOfNum = "", t = "";
        if (num.length() > 6){
            String ar[] = translateMillions(num, nameOfNum);
            num = ar[0]; nameOfNum = ar[1];
            ar = translateThousands(num, nameOfNum);
            num = ar[0]; nameOfNum = ar[1];
            t = translateLittleNum(num);
            nameOfNum += (t == "нуль" ? "" : " " + t);
        }
        else
            if (num.length() > 3){
                String ar[] = translateThousands(num, nameOfNum);
                num = ar[0]; nameOfNum = ar[1];
                t = translateLittleNum(num);
                nameOfNum += (t == "нуль" ? "" : " " + t);
            }else
                nameOfNum += translateLittleNum(num);
        return nameOfNum;
    }

    public static String translate(int num){
        return translate(Integer.toString(num));
    }
}
