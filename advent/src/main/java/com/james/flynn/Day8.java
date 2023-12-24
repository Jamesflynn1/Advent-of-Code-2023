package com.james.flynn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Day8 {
    public static class BinaryNode {
        public BinaryNode left;
        public BinaryNode right;
        public String label;
        public BinaryNode nextStep;
        public BinaryNode(BinaryNode left, BinaryNode right, String label){
            this.left = left;
            this.right = right;
            this.label = label;
            this.nextStep = null;
        }
        public int traverse(String path){
            if (this.label.equals("ZZZ")){
                return 0;
            }
            else if (path.length() == 0){
                this.nextStep = this;
                return -1;
            }
            else{
                if (path.charAt(0) == 'L'){
                    int result = left.traverse(path.substring(1));
                    if (result == -1){
                        this.nextStep = left.nextStep;
                        return -1;
                    }
                    else{
                        return result+1;
                    }
                }
                else{
                    int result = right.traverse(path.substring(1));
                    if (result == -1){
                        this.nextStep = right.nextStep;
                        return -1;
                    }
                    else{
                        return result+1;
                    }
                }
            }

        }
      /* public List<Character> search(List<Character> path){
            if (this.label == "ZZZ"){
                return path;
            }
            else{
                if (left != null){
                    List<Character> l = new ArrayList<Character>(path);
                    l.add('l');
                    return left.search(l);
                }
                if (right != null){
                    List<Character> r = new ArrayList<Character>(path);
                    r.add('r');
                    return right.search(r );
                }
                return null;
            }
        }
    }*/
    public static BinaryNode returnOrCreateNode(HashMap<String, BinaryNode> graphStructure, String key){
        if (graphStructure.get(key) != null){
            return graphStructure.get(key);
        }
        else{
            BinaryNode toAdd = new BinaryNode(null, null, key);
            graphStructure.put(key, toAdd);
            return toAdd;
        }
    }
    public static void main(String[] args) {
        List<String>inArr = new ArrayList<String>();
        HashMap<String, BinaryNode> graphStructure = new HashMap<String, BinaryNode>();
        try{
            inArr = Utils.readFileLineInput("data/Day8.txt");
        }
        catch (IOException e){
            System.out.println(e);
        }
        for (int i = 2; i<inArr.size(); i++){
            String[] headBody = inArr.get(i).split(" = ");
            String[] lr = headBody[1].replaceAll("\\(|\\)| ", "").split(",");
            BinaryNode left = returnOrCreateNode(graphStructure ,lr[0]);
            BinaryNode right = returnOrCreateNode(graphStructure, lr[1]);
            try{
                BinaryNode toUpdate = graphStructure.get(headBody[0]);
                toUpdate.left = left;
                toUpdate.right = right;
                graphStructure.put(headBody[0], toUpdate);
            }
            catch (Exception e){
                BinaryNode created = new BinaryNode(left, right, headBody[0]);
                graphStructure.put(headBody[0], created);
            }
        }
        BinaryNode start = graphStructure.get("AAA");
        String path = inArr.get(0);
        int remainder = -1;
        int pathLength = 0; 
        while(remainder == -1){
            remainder = start.traverse(path);
            start = start.nextStep;
            if (remainder == -1){
            pathLength+= path.length();
            }
        }
        pathLength+= remainder;
        System.out.println(pathLength);
        //System.out.println(graphStructure.get("AAA").traverse(path));
    }
}
