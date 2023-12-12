package com.james.flynn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.james.flynn.Day2.Constraint;

public class Day4 {
        public static List<Integer> partOne(List<String> inArr){
        int totalWin = 0;
        ArrayList<Integer> matched = new ArrayList<Integer>();
        for (int i = 0; i < inArr.size();i++){
            String[] game = inArr.get(i).split("\\|");
            int power = -1;
            HashSet<Integer> winningNumbers = new HashSet<>();

            String[] numbers = game[0].split(" ");
            //Skip Game and game number
            for (int h = 2; h < numbers.length;h++){
                if (numbers[h] != "" && numbers[h] != ":" && !numbers[h].contains(":")){
                    winningNumbers.add(Integer.parseInt(numbers[h]));
                }
            }
            String[] gameNumbers = game[1].split(" ");
            //Skip Game and game number
            for (int h = 0; h < gameNumbers.length;h++){
                if (gameNumbers[h] != "" && gameNumbers[h] != ":"){
                    int gameNumber = Integer.parseInt(gameNumbers[h]);
                    if (winningNumbers.contains(gameNumber)){
                        power+=1;
                    }
                }
            }
            if (power >= 0){
                totalWin+= Math.pow(2, power);
            }
            matched.add(power+1);
        }
        System.out.println(totalWin);
        return matched;
        }
        public static void main(String[] args) {
        //Part 1
        List<String>inArr = new ArrayList<String>();
        try{
            inArr = Utils.readFileLineInput("advent/data/Day4.txt");
        }
        catch (IOException e){
            System.out.println(e);
        }
        List<Integer> matched = partOne(inArr);

        List<Integer> causingAdditional = new ArrayList<Integer>();
        System.out.println(matched);
        //Work backwards so we also have the additional scratch card number for the cards that we will copy
        for (int i = matched.size()-1; i>= 0; i--){
            int add = 0;
            //Min statement to ensure that we don't include scratch cards out of bounds (puzzle said to assume that
            // we don't need to though - added anyway).
            for (int h = i+1; h< Math.min(matched.size(), i + matched.get(i)+1); h++){
                add+= causingAdditional.get(matched.size()-1 - h);
            }
            causingAdditional.add(add+1);
        }
        int total = 0;
        for (int i = 0; i< causingAdditional.size(); i++){
            total += causingAdditional.get(i);
        }


        System.out.println(total);

    }
}
