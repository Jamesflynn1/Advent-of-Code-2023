package com.james.flynn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;



public class Day2 {
    public static ArrayList<Integer> parseRound(String round){
        String[] colours = round.split(",");
        int red = 0;
        int green = 0;
        int blue = 0;
        for (int i = 0; i<colours.length;i++){
            String[] item = colours[i].split(" ");
            switch (item[2]) {
                case "blue":{
                    blue = Integer.parseInt(item[1]);
                    break;
                }
                case "green":{
                    green = Integer.parseInt(item[1]);
                    break;
                }
                case "red":{
                    red = Integer.parseInt(item[1]);
                    break;
                }

                default:
                    break;
            }
        }
        return new ArrayList<Integer>(Arrays.asList(red,green,blue));
    }
    public static int powerOfMinimum (ArrayList<String> game){
        int rMax = 0;
        int gMax = 0;
        int bMax = 0;
        for (int i = 0; i<game.size();i++){
            String round;
            if (i == 0){
                round = game.get(i).split(":")[1];
            }
            else{
                round = game.get(i);
            }
            ArrayList<Integer> roundValues =  parseRound(round);

            rMax = Math.max(rMax, roundValues.get(0));
            gMax = Math.max(gMax, roundValues.get(1));
            bMax = Math.max(bMax, roundValues.get(2));
        }
        return rMax*gMax*bMax;
    }

    public static boolean validGame (ArrayList<String> game, Constraint c){
        //System.out.println(game);
        for (int i = 0; i<game.size();i++){
            String round;
            if (i == 0){
                round = game.get(i).split(":")[1];
            }
            else{
                round = game.get(i);
            }
            ArrayList<Integer> roundValues =  parseRound(round);
            if (c.checkAgainst(roundValues.get(0), roundValues.get(1), roundValues.get(2)) == false){
                return false;
            }
        }

        return true;
    }
    public static int getGameId(ArrayList<String> game){
        String firstPart = game.get(0).split(":")[0];
        System.out.println(firstPart);
        String gameId = firstPart.split(" ")[1];
        System.out.println(gameId);

        return Integer.parseInt(gameId);

    }
    public static void main(String[] args) {
        //Part 1
        ArrayList<ArrayList<String>> inArr = new ArrayList<ArrayList<String>>();
        Constraint c = new Constraint(12, 13, 14);
        try{
            inArr = Utils.readFileDelimitedInput("advent/data/Day2.txt", ";");
        }
        catch (IOException e){
            System.out.println(e);
        }
        int idTotal = 0;
        for (int i = 0; i < inArr.size();i++){
            if (validGame(inArr.get(i), c)){
                idTotal += getGameId(inArr.get(i));
            }
        }
        System.out.println(idTotal);

        //Part 2
        int powerTotal = 0;
        for (int i = 0; i < inArr.size();i++){
            powerTotal += powerOfMinimum(inArr.get(i));
        }
        System.out.println(powerTotal);
    }

    public static class Constraint {
        public int cRed;
        public int cGreen;
        public int cBlue;

        public Constraint(int red, int green, int blue){
            this.cRed = red;
            this.cGreen = green;
            this.cBlue = blue;
        }
        public boolean checkAgainst(int red, int green, int blue){
            if (this.cRed >= red && this.cGreen >= green && this.cBlue >= blue){
                return true;
            }
            else{
                return false;
            }
        }
    }
}
