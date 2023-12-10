package src;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.Utils;

public class Day1 {
    public static void main(String[] args) {
        List<String>inArr = new ArrayList<String>();

        int total = 0;
        try{
        inArr = Utils.readFileLineInput("../data/Day1.txt");
        }
        catch (IOException e){
            System.out.println(e);
        }
        for (int i = 0; i<inArr.size(); i++){
            String line = inArr.get(i);
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
