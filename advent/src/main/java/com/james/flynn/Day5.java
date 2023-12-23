package com.james.flynn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedList;;

public class Day5 {
    public static class Range{
        private ArrayList<Long[]> start;
        private ArrayList<Long[]> end;
        public Range(){
            this.start = new ArrayList<>();
            this.end = new ArrayList<>();
        }
        public void addNew(long start, long end){
            for 
        }
    }
    public static class Rule {
        private ArrayList<Long[]> ranges;
        public Rule(){
        this.ranges = new ArrayList<>();
        }
        public void addRule(Long[] ranges){
            this.ranges.add(ranges);
        }
        public Long applyRule(Long x){
            for (int i = 0; i< this.ranges.size(); i++){
                if (x>= ranges.get(i)[1] && x< ranges.get(i)[1]+ ranges.get(i)[2]){
                    //Add 
                    return (x - ranges.get(i)[1]) + ranges.get(i)[0];
                }
            }
            //No rules apply
            return x;
        }
        public Range applyRule(Range x){
            if 
            return x;
        }

    }
    public static long findMinLocation (List<Long> seeds, List<Rule> rules){
        Long minLocation = Long.MAX_VALUE;
        for (int i = 0; i<seeds.size();i++){
            Long intermediateValue = seeds.get(i);
            for (int h = 0; h< rules.size(); h ++){
                intermediateValue = rules.get(h).applyRule(intermediateValue);
            }
            if (intermediateValue < minLocation){
                minLocation = intermediateValue;
            }

        }
        return minLocation;
    }

    public static void main(String[] args) {
        List<String>inArr = new ArrayList<String>();
        List<Long> seeds = new ArrayList<Long>();
        List<Rule> rules = new ArrayList<Rule>();
        try{
            inArr = Utils.readFileLineInput("advent/data/Day5.txt");
        }
        catch (IOException e){
            System.out.println(e);
        }
        String seedRow = inArr.get(0);
        String[] seedStr =seedRow.split(" ");
        for (int i = 1; i<seedStr.length;i++){
            seeds.add(Long.parseLong(seedStr[i]));
        }

        boolean parsingRule = false;
        int ruleCounter = -1;
        //Parse rules
        for (int i = 2; i<inArr.size();i++){
            if (parsingRule){
                if (inArr.get(i).length()>1 ){
                    String [] sRule = inArr.get(i).split(" ");
                    Long [] iRule = new Long[sRule.length];
                    for (int h = 0; h<sRule.length;h++){
                        iRule[h] = Long.parseLong(sRule[h]);
                    }
                    rules.get(ruleCounter).addRule(iRule);
                }
                else{
                    parsingRule = false;
                }
            }
            else{
                if (inArr.get(i).length()>1){
                    rules.add(new Rule());
                    ruleCounter ++;
                    parsingRule = true;
                }
                else{
                    System.out.println("Skipped");
                }
            }
        }

        //Part 1
        System.out.println(findMinLocation(seeds, rules));
        
        //Part 2
        long min = Long.MAX_VALUE;
        for (int i = 0;i<seeds.size(); i+=2){
            List<Long> partTwoSeeds = new ArrayList<Long>();
            for (long h = seeds.get(i);h<seeds.get(i) + seeds.get(i+1); h++){
                partTwoSeeds.add(h);
            }
            min = Math.min(min,findMinLocation(partTwoSeeds, rules));
        }
        System.out.println(min);
    }
}
