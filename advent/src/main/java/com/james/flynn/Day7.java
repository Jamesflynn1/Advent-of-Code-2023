package com.james.flynn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.james.flynn.Day5.Rule;

import java.util.HashMap;

public class Day7 {
    
    public static class Hand implements Comparable<Hand> {
        private String hand;
        private int major;
        public Hand(String hand){
            this.hand = hand;
            this.major = this.majorRank();

        }
        public int majorRank(){
            HashMap<String, Integer> count = new HashMap<>();

            for (String i : count.keySet()) {
                System.out.println(i);
              }

            
        }
        public int compareTo(Hand o){
            if (o.major > this.major){
                return -1;
            }
            else if(o.major < this.major){
                return 1;
            }
            else{
                for (int i=0;i<this.hand.length();i++){
                    int cardRank = mapCard(hand.substring(i, i));
                    int oCardRank = mapCard(o.hand.substring(i, i));
                    if (cardRank!= oCardRank){
                        return cardRank - oCardRank;
                    }
                }
            }
            return 0;
        }
        public int mapCard(String card){
            try {
                int r = Integer.parseInt(card);
                return r;
            }
            finally{
                switch (card) {
                    case "A":
                        return 14;
                    case "K":
                        return 13;
                    case "Q":
                        return 12;
                    case "J":
                        return 11;
                    case "T":
                        return 10;
                    default:
                        System.err.println("INVAILD CARD");
                        break;
                }
            }
        }
        
    }
}
