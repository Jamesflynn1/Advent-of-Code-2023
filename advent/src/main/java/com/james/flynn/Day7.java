package com.james.flynn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;

public class Day7 {
    
    static class Hand implements Comparable<Hand> {
        public String hand;
        private int major;
        public int bet;
        public boolean joker;

        public Hand(String hand, int bet, boolean joker){
            this.joker = joker;
            this.hand = hand;
            this.major = this.majorRank();
            this.bet = bet;
        }
        public int majorRank(){
            HashMap<Character, Integer> count = new HashMap<>();
            for (Character c : this.hand.toCharArray()){
                try {
                    int cCount = count.get(c);
                    count.replace(c, cCount+1);
                } catch (Exception e) {
                    count.put(c, 1);
                }
            }
            int twoPresent = 0;
            int maxMatched = 0;
            int preMaxMatched = 0;
            int jokerAdditional = 0;
            for (Character c : count.keySet()){
                if (c == 'J' && this.joker){
                    jokerAdditional = count.get(c);
                    continue;
                }
                else if(count.get(c) == 2){
                    twoPresent++;
                }
                maxMatched = Math.max(maxMatched, count.get(c));
            }
            preMaxMatched = maxMatched;
            maxMatched += jokerAdditional;
            if (maxMatched >3){
                return maxMatched+1;
            }
            else if (maxMatched == 3){
                if (twoPresent>0 && (twoPresent>1 || preMaxMatched==3)){
                    return 4;
                }
                else{
                    return 3;
                }
            }
            //2 pairs cannot exist with a joker, one triple is better
            else if (maxMatched == 2){
                if(twoPresent == 2){
                    return 2;
                }
                else{
                    return 1;
                }
            }
            else{
                return 0;
            }
        }
        public int compareTo(Hand o){
            if (o.major > this.major){
                return -1*20;
            }
            else if(o.major < this.major){
                return 1*20;
            }
            else{
                for (int i=0;i<this.hand.length();i++){
                    int cardRank = mapCard(hand.charAt(i));
                    int oCardRank = mapCard(o.hand.charAt(i));
                    if (cardRank!= oCardRank){
                        return cardRank - oCardRank;
                    }
                }
            }
            return 0;
        }
        public int mapCard(Character card){
            try {
                int r = Integer.parseInt(Character.toString(card));
                return r;
            }
            catch(Exception e){
                switch (card) {
                    case 'A':
                        return 14;
                    case 'K':
                        return 13;
                    case 'Q':
                        return 12;
                    case 'J':{
                        if (this.joker){
                            return 0;
                        }
                        else{
                            return 11;
                        }
                    }
                    case 'T':
                        return 10;
                    default:
                        System.err.println("INVAILD CARD");
                        System.out.println(card);
                        return-1;
                }
            }
        }
        
    }
    public static void main(String[] args) {
        List<String>inArr = new ArrayList<String>();
        List<Hand> hands = new ArrayList<Hand>();
        List<Hand> jokerHands = new ArrayList<Hand>();
        try{
            inArr = Utils.readFileLineInput("data/Day7.txt");
        }
        catch (IOException e){
            System.out.println(e);
        }

        for (String s : inArr){
            String[] line = s.split(" ");
            Hand normal = new Hand(line[0], Integer.parseInt(line[1]), false);
            Hand jokerPlus = new Hand(line[0], Integer.parseInt(line[1]), true);
            hands.add(normal);
            jokerHands.add(jokerPlus);
        }
        Collections.sort(hands);
        Collections.sort(jokerHands);

        int total = 0;
        int jokerTotal = 0;

        for (int i = 0; i < jokerHands.size(); i++){
            total += hands.get(i).bet*(i+1);
            jokerTotal += jokerHands.get(i).bet*(i+1);
        }
        System.out.println(total);
        System.out.println(jokerTotal);
    }
}
