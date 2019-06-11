package edu.wofford.wordoff;
//import com.sun.xml.internal.fastinfoset.util.StringArray;
import java.util.Scanner;

import java.util.*;
import java.io.*;

public class Feature01Main {
    public  static void main(String[] args) {
        //File file = new File("C:\\Users\\Marc Western\\Desktop\\Software engineering semester project\\project-okay-tier\\client\\src\\main\\java\\edu\\wofford\\wordoff");
        //for(String fileNames : file.list()) System.out.println(fileNames);

        AnagramSearcher a = new AnagramSearcher("/allwords.txt");
                /*Scanner scan = new Scanner(System.in);
                    if(scan.hasNextInt()){
                        int i = scan.nextInt();
                        System.out.println(a.getRandagram(i));
                    }
                    if(scan.hasNext()){
                        String s = scan.nextLine();
                        System.out.println(a.getAnagram(s));
                    }*/
                try {
                    int i = Integer.parseInt(args[0]);
                    String x = String.valueOf(a.getRandagram(i));
                    x = x.replaceAll("\\[","");
                    x = x.replaceAll("\\]", "");
                    x = x.replaceAll("\\,", "");
                    System.out.println(x);
                }
                catch(NumberFormatException e){
                    String s = args[0];
                    String x = String.valueOf(a.getAnagram(s));
                    x = x.replaceAll("\\[","");
                    x = x.replaceAll("\\]", "");
                    x = x.replaceAll("\\,", "");
                    System.out.println(x);
        }





        /*a.readIn("/allwords.txt");
        if(a.testPrint() == true){
            System.out.println("IT LIVES!!!!!");
        }
        else{
            System.out.println("You dun Goofed");
        }*/
        //System.out.println(a.getAnagram("SlEEp"));
        //System.out.println(a.getRandagram(15));





    }




}

