package edu.wofford.wordoff;

import java.util.*;

public class Feature03Main {
    public static void main(String[] args) {
        AnagramSearcher a = new AnagramSearcher("/allwords.txt");
        String s = args[0];
        a.getAnagram(s);
        String x = String.valueOf(a.getAnagram(s));
        x = x.replaceAll("\\[","");
        x = x.replaceAll("\\]", "");
        x = x.replaceAll("\\,", "");
        if (x.equals("")){
            System.out.println("");
        }
        else{
            System.out.println(a.getAllAnagrams(args[0]));
        }
    }
}