package edu.wofford.wordoff;

//import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.util.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class AnagramSearcher {

    //    private boolean working;
    public Map<String, List<String>> wordMap;
	public int jump = 1;

    public AnagramSearcher() {
        this.wordMap = new HashMap<>();
    }

    public AnagramSearcher(String filename) {
        this();
        InputStream is = getClass().getResourceAsStream(filename);
        Scanner s = new Scanner(is);
        // loops through dictionary and enters contents into map
        while (s.hasNext()) {
            String str = s.next();
            //convert the word to lowercase, sort it, check the sort against the map, and if it ain't there, enter it.
            //If it is there, then tack the unsorted word on to the array ( with the appropriate key)
            str = str.toLowerCase();
            char[] strChar = str.toCharArray();
            Arrays.sort(strChar);
            String sortStr = new String(strChar);
            if (!wordMap.containsKey(sortStr)) {
                wordMap.put(sortStr, new ArrayList<String>());
            }
            wordMap.get(sortStr).add(str);
            /*
                // If it doesn't have the key there already, make a new entry. If it does, add str to the proper entry
                ArrayList<String> wordHold = new ArrayList<>();
                wordHold.add(str);
                wordMap.put(sortStr, wordHold);
            }
            // Create a new entry in the map if the word isn't already there
            else {
                ArrayList<String> wordHold = new ArrayList<>();
                wordHold.add(str);
                wordHold.addAll((Collection<? extends String>) wordMap.get(sortStr));
                wordMap.put(sortStr, wordHold);
            }
            */
        }
    }
    //When fed a string, this outputs the associated anagrams, if any.
    //If the word isn't there, it just outputs an empty string
    public List<String> getAnagram(String originalSin) {
        originalSin = originalSin.toLowerCase();
        char[] strChar = originalSin.toCharArray();
        Arrays.sort(strChar);
        String sortStr = new String(strChar);
        if (wordMap.containsKey(sortStr)) {
            //System.out.print(wordMap.get(sortStr));
            ArrayList goof = new ArrayList();
            goof.add("");
            String s = String.valueOf(wordMap.get(sortStr));
            if (s.contains(originalSin)|| jump == 0) {
                return wordMap.get(sortStr);
            }
            return goof;
        } else {
            //System.out.print("You dun Goofed");
            ArrayList goof = new ArrayList();
            goof.add("");
            return goof;

        }
    }

    //When fed a string, this outputs the associated anagrams, if any.
    //If the word isn't there, it just outputs an empty string


    //When fed an integer, this picks a random key from all the keys of that length and returns the associated anagrams
    public Object getRandagram(Integer num) {
        Set keyset = wordMap.keySet();
        ArrayList<String> randCandidates = new ArrayList<>();
        if (num == 0) {

            return randCandidates;
        }
        List<String> stringList = new ArrayList<>(keyset);

        for (int i = 0; i < keyset.size(); i++) {
            ArrayList<String> blank = (ArrayList<String>) wordMap.get(stringList.get(i));
            if (blank.size() == num) {
                randCandidates.add(stringList.get(i));
            }
        }
        int rNum;
        if (randCandidates.size() == 1) {
            rNum = ThreadLocalRandom.current().nextInt(0, randCandidates.size());
            int a = rNum;

        } else {
            rNum = ThreadLocalRandom.current().nextInt(0, randCandidates.size() - 1);
        }
        String rString = randCandidates.get(rNum);
        return wordMap.get(rString);
    }

	public String getAllAnagrams(String text){
		ArrayList<String> combination = new ArrayList<String>();
		for (int i = 0; i < text.length(); i++) {
        // Record size as the list will change
        int combinationLength = combination.size();
			for (int j = 0; j < combinationLength; j++) {
				combination.add(text.charAt(i) + combination.get(j));
			}
				combination.add(Character.toString(text.charAt(i)));
		}
		
		for (int i = 0; i < combination.size(); i++){
			char[] chars = combination.get(i).toCharArray();
			Arrays.sort(chars);
			String sorted = new String(chars);
			combination.set(i,sorted);
		}
		
		jump = 0;
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < combination.size(); i++){
			String y = String.valueOf(getAnagram(combination.get(i)));
			y = y.replaceAll("\\[","");
			y = y.replaceAll("\\]", "");
			y = y.replaceAll("\\,", "");
			result.add(y);
		}
		
		String finalResult = String.valueOf(result);
		finalResult = finalResult.replaceAll("\\[","");
		finalResult = finalResult.replaceAll("\\]", "");
		finalResult = finalResult.replaceAll(",", "");
		finalResult = finalResult.replaceAll(" ,","");
		finalResult = finalResult.replaceAll(", ","");
		List<String> words = new ArrayList<String>(Arrays.asList(finalResult.split(" ")));
		Set<String> eliminateDuplicates = new HashSet<>();
		eliminateDuplicates.addAll(words);
		words.clear();
		words.addAll(eliminateDuplicates);
		Collections.sort(words);
		String allAnagram = String.valueOf(words);
		allAnagram = allAnagram.replaceAll(",","");
		allAnagram = String.valueOf(allAnagram);
		allAnagram = allAnagram.replaceAll("\\[","");
		allAnagram = allAnagram.replaceAll("\\]","");
		allAnagram = allAnagram.replaceAll(" ","\n");
		
		return allAnagram;
	}
    /*public boolean testPrint() {
        return wordMap.containsKey("asw");
    }*/
//The old anagram finder function. Here in case we need it for some reason. It's a good function, but not for our needs
    public static boolean isAnagram(String s1, String s2){
        if (s1.length() != s2.length()) {
            return false;
        }
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        String sc1 = new String(c1);
        String sc2 = new String(c2);
        return sc1.equals(sc2);
	}
	public String play_random(int len){
		ArrayList<String> all_words_with_length = new ArrayList<>();
		// while (this.s.hasNext()) {
            // String str = s.next();
			// if (str.length == len){
				// all_words_with_length.add(str);
			// }
		// }
		for (String key : wordMap.keySet()){
			if (key.length() == len){
				all_words_with_length.add(key);
			}
		}
		Random rand = new Random();
		String random_word = all_words_with_length.get(rand.nextInt(all_words_with_length.size()));
		wordMap.get(random_word).get(rand.nextInt(wordMap.get(random_word).size()));
		// String y = String.valueOf(getAnagram(random_word));
		// y = y.replaceAll("\\[","");
        // y = y.replaceAll("\\]", "");
        // y = y.replaceAll("\\,", "");
		// List<String> lista = new ArrayList<String>(Arrays.asList(y.split(" ")));
		// random_word = lista.get(rand.nextInt(lista.size()));
		
		if (len <= 1){random_word = " ";}
		return random_word;
	}
}
