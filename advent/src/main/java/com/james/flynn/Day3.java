package com.james.flynn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Day3 {
    public static void main(String[] args) {
        List<String>inArr = new ArrayList<String>();
        
        try{
        inArr = Utils.readFileLineInput("advent/data/Day3.txt");
        }
        catch (IOException e){
            System.out.println(e);
        }

        ArrayList<ArrayList<String>> grid = new ArrayList<ArrayList<String>>();
        for (int i =0; i<inArr.size(); i++){
            grid.add(processLine(inArr.get(i)));
        }

        int total = 0;
        for (int i =0; i<grid.size(); i++){
            System.out.println(grid.get(i).size());
            for (int h =0; h<grid.get(i).size(); h++){
                //If we have a special character
                char checkChar = (grid.get(i).get(h).charAt(0));
                if (!Character.isDigit(checkChar) && checkChar != '.'){
                    total+= crossOff(grid,i,h);

            }
        }
    }
        System.out.println(total);

        System.out.println(grid);
    }
    public static int crossOff (ArrayList<ArrayList<String>> grid, int i, int h){
        //Check surrounding cells and add numbers to total
        int total = 0;
        for (int y = Math.max(0, i-1); y<=Math.min(grid.size()-1, i+1); y++){
            for (int x = Math.max(0, h-1); x<=Math.min(grid.get(y).size()-1, h+1); x++){
                if (Character.isDigit(grid.get(y).get(x).charAt(0))){
                    total+=Integer.parseInt(grid.get(y).get(x));
                    //Left case will cover central
                    int left = x;
                    int right =x+1;
                    while (left>=0 && Character.isDigit(grid.get(y).get(left).charAt(0))) {
                        grid.get(y).set(left, ".");
                        left--;
                    }

                    while (right<grid.get(i).size() && Character.isDigit(grid.get(y).get(right).charAt(0))) {
                        grid.get(y).set(right, ".");
                        right++;
                    }
                }
            }
        }
        return total;
    }
    public static ArrayList<String> processLine (String line){
        ArrayList<String> lineProcessed = new ArrayList<String>();
        boolean currentDigit = false; 
        int j = 0;
        for (int i =0; i<line.length(); i++){
            if (Character.isDigit(line.charAt(i))){
                if (!currentDigit){
                    currentDigit = true;
                    j = i;
                }
            }
            else{
                if (currentDigit){
                    //Make the whole number occupy each cell of the number
                    for (int h = 0; h<i-j;h++){
                        lineProcessed.add(line.substring(j, i));
                    }
                    currentDigit = false;
                }
                lineProcessed.add(Character.toString(line.charAt(i)));
            }
        }
        return lineProcessed;

    }

}
