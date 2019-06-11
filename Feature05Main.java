package edu.wofford.wordoff;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.border.Border;


public class Feature05Main extends JFrame implements ActionListener {
	//java.util.List<String> anagrams_without_word;
	String[] anagrams_without_word;
	JButton button;
	JLabel[] label;
	String guess_word;
	JTextField guess;
	Border border;
	int count;
	JLabel target;
	
     public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();
		guess_word = guess.getText();
		if (src == button){
			for (int i = 0; i< anagrams_without_word.length;i++){
				if (guess_word.equals(anagrams_without_word[i])){
					label[i].setText(guess_word);
					count ++;
				}
			}
			guess.setText("");
			if (count == anagrams_without_word.length){
				border = BorderFactory.createLineBorder(Color.GREEN, 5);
				target.setBorder(border);
				guess.setEnabled(false);
				button.setEnabled(false);
				}
		}
	 }
    public Feature05Main(String chosen_word, int number_of_anagrams) {
		AnagramSearcher b = new AnagramSearcher("/commonwords.txt");
		// String y = String.valueOf(b.getAnagram(chosen_word));
		// y = y.replaceAll("\\[","");
        // y = y.replaceAll("\\]", "");
        // y = y.replaceAll("\\,", "");
		// y = y.replaceAll(chosen_word,"");
		// anagrams_without_word = new ArrayList<String>(Arrays.asList(y.split(" ")));
		String y = String.valueOf(b.getAnagram(chosen_word));
		y = y.replaceAll("\\[","");
        y = y.replaceAll("\\]", "");
        y = y.replaceAll("\\,", "");
		y = y.replaceAll(chosen_word,"");
		y = y.replaceAll("\\s+"," ");
		java.util.List<String> lista = new ArrayList<String>(Arrays.asList(y.split(" ")));
		
		String[] x = lista.toArray(new String[0]);
		anagrams_without_word = Arrays.copyOfRange(x, 1, x.length);
		
		JFrame frame = new JFrame();
		
		//this.setLayout(new BorderLayout());
        // Create JButton and JPanel
        button = new JButton("Click");
		button.setName("button");
		button.addActionListener(this);
		count = 0;
			
        JPanel bottom_panel = new JPanel(new FlowLayout());
		//bottom_panel.setLayout(experimentLayout);
		//bottom_panel.setLayout(new BoxLayout(bottom_panel, BoxLayout.LINE_AXIS));
		
		
		target = new JLabel(chosen_word,JLabel.CENTER);
		target.setName("target");
		border = BorderFactory.createLineBorder(Color.RED, 5);
		target.setBorder(border);

		JPanel main_panel = new JPanel(new BorderLayout());
		JPanel word_panel = new JPanel(new GridLayout(number_of_anagrams+1,1));
		word_panel.add(target);
		
		//JPanel bottomleft_panel = new JPanel(new BorderLayout());
		guess = new JTextField(10);
		guess.setName("guess");
		guess_word = guess.getText();
		bottom_panel.add(guess);
		bottom_panel.add(button);
		//bottomleft_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		//bottomleft_panel.add(guess, BorderLayout.LINE_END);

		label = new JLabel[number_of_anagrams];
		for (int i = 0; i < number_of_anagrams; i++) {
			label[i] = new JLabel("anagram" + i,JLabel.CENTER);
			//label[i].setText("anagram" + i,);
			//label[i].setText(anagrams_without_word.get(i));
			word_panel.add(label[i]);
		}

		
		main_panel.add(word_panel);
		//main_panel.add(word_panel, BorderLayout.CENTER);
		main_panel.add(bottom_panel, BorderLayout.PAGE_END);
		//main_panel.add(bottomleft_panel, BorderLayout.PAGE_END);
		//main_panel.setPreferredSize(new Dimension(300, 600));
		
		frame.setTitle("WordOff");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(main_panel);
		frame.pack();
		//frame.setLocationRelativeTo(null);
		frame.setVisible(true);
    }

    public static void main(String[] args) {
		AnagramSearcher a = new AnagramSearcher("/commonwords.txt");
		try{int num = Integer.parseInt(args[0]);
            if(num < 1){
                throw new ArgsLessThanTwo("You dun goofed");
            }
			String x = String.valueOf(a.getRandagram(num+1));
			x = x.replaceAll("\\[","");
			x = x.replaceAll("\\]", "");
			x = x.replaceAll("\\,", "");
			java.util.List<String> words = new ArrayList<String>(Arrays.asList(x.split(" ")));
			Feature05Main c = new Feature05Main(words.get(0),num);
		}
		catch(IllegalArgumentException b){}
		catch (ArgsLessThanTwo d){}
    }
}