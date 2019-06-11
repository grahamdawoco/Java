package edu.wofford.wordoff;

import java.util.*;
import java.io.*;

public class Feature04Main {
    public static void main(String[] args) {
		ArrayList<String> all_words = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		AnagramSearcher a = new AnagramSearcher("/commonwords.txt");
		try{
			if (args[0] == ""||args[0]==null){
				//System.out.println("");
				throw new IllegalArgumentException("null");
			}
			int num = Integer.parseInt(args[0]);
            if(num < 3){
                throw new ArgsLessThanTwo("You dun goofed");
            }
			String chosen_word = a.play_random(num);
			String all = a.getAllAnagrams(chosen_word);
			all = all.substring(1);
			all.replaceAll(chosen_word,"");
			all = all.replaceAll("\\s+", "\n");
			List<String> alist = new ArrayList<String>(Arrays.asList(all.split("\n")));
			//System.out.println(alist.get(0).length());
			Map<String, List<String>> words = new HashMap();
			String pre = "[len";
			String pos = "]";
			alist.remove(0);
			
			// for (int i = 0; i < alist.size(); i++) {
			// System.out.println(alist.get(i));
			// }
			
			for(int i = 2; i <= num; i++){
				for (int j = 0; j < alist.size(); j++){
					String k = Integer.toString(i);
					if (!words.containsKey(k)) {
						words.put(k,new ArrayList<String>());
					}
					if(alist.get(j).length()==i){
						words.get(k).add(alist.get(j));
					}
				}
			}
			System.out.println("The word is \"" + chosen_word + "\". ");
			
			System.out.print("There are ");
			// for (int i = words.size(); i > 1; i--){
				// String k = Integer.toString(i);
				// System.out.print(pre + words.get(k).size()+" " + pos);
			// }
			
			for (int i = words.size()+1; i > 1; i--){
				String k = Integer.toString(i);
				System.out.print(words.get(k).size()+" "+pre +" "+ k + pos + ", " );
			}
			System.out.print("and 0 "+pre+" 1"+ pos +"anagrams remaining: ");
			
			// for(Object key: words.keySet())
			// System.out.println(key + " - " + words.get(key));
			// System.out.println("");
			
			while(alist.size()>0){
				//System.out.println(alist.size());
				if(alist.size()==0){
					System.out.println("There are 0 anagrams remaining.");
				}
				String guess = input.nextLine();
				guess = guess.toLowerCase();
				if(alist.contains(guess)){
					alist.remove(guess);
					if (alist.size()==0){
						System.out.println("There are 0 anagrams remaining.");
						//break;
					}
					else{
						String s = Integer.toString(guess.length());
						words.get(s).remove(guess);
						System.out.println();
						System.out.print("There are ");
						for (int i = words.size()+1; i > 1; i--){
						String k = Integer.toString(i);
						System.out.print(words.get(k).size()+" "+pre +" "+ k + pos + ", " );
						}
						System.out.print("and 0 "+pre+" 1"+ pos +"anagrams remaining: ");
					}
				}
				else{
					System.out.println();
					System.out.print("There are ");
					for (int i = words.size()+1; i > 1; i--){
					String k = Integer.toString(i);
					System.out.print(words.get(k).size()+" "+pre +" "+ k + pos + ", " );
					}
					System.out.print("and 0 "+pre+" 1"+ pos +"anagrams remaining: ");
				}
			}
			// for(Object key: words.keySet())
				// System.out.println(key + " - " + words.get(key));
				// System.out.println("");
		}
		catch(NumberFormatException e){
				String chosen_word = args[0];
				chosen_word = chosen_word.toLowerCase();
				String all = a.getAllAnagrams(chosen_word);
				all = all.substring(1);
				all = all.replaceAll(chosen_word,"");
				all = all.replaceAll("\\s+", "\n");
				List<String> alist = new ArrayList<String>(Arrays.asList(all.split("\n")));
				
				Map<String, List<String>> words = new HashMap();
				String pre = "[len";
				String pos = "]";
			//alist.remove(0);
			//System.out.println(alist.size());
			
			// for (int i = 0; i < alist.size(); i++) {
			// System.out.println(alist.get(i));
			// }
			
				for(int i = 2; i <= chosen_word.length(); i++){
					for (int j = 0; j < alist.size(); j++){
						String k = Integer.toString(i);
						if (!words.containsKey(k)) {
							words.put(k,new ArrayList<String>());
						}
						if(alist.get(j).length()==i){
							words.get(k).add(alist.get(j));
						}
					}
				}
				System.out.println("The word is \"" + chosen_word + "\". ");
				
				System.out.print("There are ");
				// for (int i = words.size(); i > 1; i--){
					// String k = Integer.toString(i);
					// System.out.print(pre + words.get(k).size()+" " + pos);
				// }
				
				for (int i = words.size()+1; i > 1; i--){
					String k = Integer.toString(i);
					System.out.print(words.get(k).size()+" "+pre +" "+ k + pos + ", " );
				}
				System.out.print("and 0 "+pre+" 1"+ pos +"anagrams remaining: ");
				
				// for(Object key: words.keySet())
				// System.out.println(key + " - " + words.get(key));
				// System.out.println("");
				
				while(alist.size()>0){
					//System.out.println(alist.size());
					if(alist.size()==0){
						System.out.println("There are 0 anagrams remaining.");
					}
					String guess = input.nextLine();
					guess = guess.toLowerCase();
					if(alist.contains(guess)){
						alist.remove(guess);
						if (alist.size()==0){
							System.out.println("There are 0 anagrams remaining.");
							//break;
						}
						else{
							String s = Integer.toString(guess.length());
							words.get(s).remove(guess);
							System.out.println();
							System.out.print("There are ");
							for (int i = words.size()+1; i > 1; i--){
							String k = Integer.toString(i);
							System.out.print(words.get(k).size()+" "+pre +" "+ k + pos + ", " );
							}
							System.out.print("and 0 "+pre+" 1"+ pos +"anagrams remaining: ");
						}
					}
					else{
						System.out.println();
						System.out.print("There are ");
						for (int i = words.size()+1; i > 1; i--){
						String k = Integer.toString(i);
						System.out.print(words.get(k).size()+" "+pre +" "+ k + pos + ", " );
						}
						System.out.print("and 0 "+pre+" 1"+ pos +"anagrams remaining: ");
					}
				}
		}
		//catch(StringIndexOutOfBoundsException c){}
		catch(NullPointerException f){}
		catch(IllegalArgumentException b){}
		catch(ArgsLessThanTwo d){}
	}
}