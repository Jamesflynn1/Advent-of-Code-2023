package com.james.flynn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.james.flynn.Day5.Rule;

public class Day6 {
    //d = st
    // s = t_1
    //d = t_1(t-t_1)

    //Find a critical t_1, we know the other critical t_1
    //tt_1 - t_1^2 - d = 0
    public static int minCharge(int time, int distance){
        int target = time +1;

        double setOff = (-target -Math.sqrt((Math.pow(target, 2)) + 4* (-distance)))/(-2);
        return (int)Math.ceil(setOff);


    }
    public static void main(String[] args) {
        List<String>inArr = new ArrayList<String>();
        List<Integer> times = new ArrayList<Integer>();
        List<Integer> distances = new ArrayList<Integer>();
        try{
            inArr = Utils.readFileLineInput("advent/data/Day6.txt");
        }
        catch (IOException e){
            System.out.println(e);
        }
        //Process times
        String [] distStr = inArr.get(0).split(" ");
        for (int i = 1; i < distStr.length; i++) {
            if (distStr[i] != ""){
                distances.add(Integer.parseInt(distStr[i]));
            }
        }

        //Process distances
        String [] timesStr = inArr.get(0).split(" ");
        for (int i = 1; i < timesStr.length; i++) {
            if (timesStr[i] != ""){
                times.add(Integer.parseInt(timesStr[i]));
            }
        }
        System.out.println(minCharge(times.get(0), distances.get(0)));
    }
}
