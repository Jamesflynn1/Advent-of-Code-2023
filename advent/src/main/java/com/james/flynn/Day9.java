package com.james.flynn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Day9 {
    //Exponential complexity in memory and time, however each line is quite short, this should be sufficent
    public static int[] extrapolate(ArrayList<Integer> series) {
        boolean sameDiff = false;
        int level = 0;
        ArrayList<ArrayList<Integer>> diff = new ArrayList<ArrayList<Integer>>();
        diff.add(series);
        while (sameDiff == false){
            sameDiff = true;
            ArrayList<Integer>levelDiff = new ArrayList<Integer>();
            for (int i = 0; i<series.size()-level-1; i++){
                levelDiff.add(diff.get(level).get(i+1)-diff.get(level).get(i));
                if (i>0 && levelDiff.get(i)!= levelDiff.get(i-1)){
                    sameDiff = false;
                }
            }
            diff.add(levelDiff);
            level++;
        }
        int forwardPred = 0;
        int backwardPred = 0;
        //We could return bot
        for (ArrayList<Integer> diffLevel : diff){
            forwardPred += diffLevel.getLast();
            backwardPred += diffLevel.getFirst();
        }
        int[] preds = {forwardPred, backwardPred};
        return preds;
        
    }
    public static ArrayList<Integer> parseLine(String line){
        String[] entries = line.split(" ");
        ArrayList<Integer> numericalEntries = new ArrayList<Integer>();
        for (String entry : entries){
            numericalEntries.add(Integer.parseInt(entry));
        }
        return numericalEntries;
    }
    public static void main(String[] args) {
        List<String>inArr = new ArrayList<String>();
        try{
            inArr = Utils.readFileLineInput("data/Day9.txt");
        }
        catch (IOException e){
            System.out.println(e);
        }
        int[] sum = {0,0};
        for (int i = 0; i<inArr.size(); i++){
            ArrayList<Integer> series = parseLine(inArr.get(i));
            int[] lineResult = extrapolate(series);
            sum[0] += lineResult[0];
            sum[1] += lineResult[1];
        }
        System.out.println(sum[0]);
        System.out.println(sum[1]);
    }
}
