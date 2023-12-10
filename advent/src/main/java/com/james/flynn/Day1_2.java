package com.james.flynn;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import com.james.flynn.Utils;

public class Day1_2{
    //Prefix and postfix to maintain words for possible subsequent matchings
    public static String digitiseLine(String line){
    String [][]digitsEncoding = {{"one","one1one"},{"two", "two2two"},
                       {"three", "three3three"}, {"four", "four4four"},
                       {"five", "five5five"}, {"six", "six6six"}, {"seven", "seven7seven"},
                       {"eight","eight8eight"} , {"nine", "nine9nine"}};

        for (int i = 0; i<digitsEncoding.length;i++){
            line = line.replaceAll(digitsEncoding[i][0], digitsEncoding[i][1]);
        }
        System.out.println(line);
        return line;
    }

    public static void main(String[] args) {
        List<String>inArr = new ArrayList<String>();

        int total = 0;
        try{
        inArr = Utils.readFileLineInput("advent/data/Day1.txt");
        }
        catch (IOException e){
            System.out.println(e);
        }
        for (int i = 0; i<inArr.size(); i++){
            String line = inArr.get(i);
            line = digitiseLine(line);
            int j = 0;
            //Obtain first digit and add 10x that to total
            while (j < line.length()){
                if (Character.isDigit(line.charAt(j))){
                    total += 10* Character.getNumericValue(line.charAt(j));
                    break;
                }
                j++;
            }
            //Obtain last digit and add to total
            int h = line.length()-1;
            while (h >=0){
                if (Character.isDigit(line.charAt(h))){
                    total += Character.getNumericValue(line.charAt(h));
                    break;
                }
                h--;
        }
    }
    System.out.println(total);
}
}
