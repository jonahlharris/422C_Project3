/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Jonah Harris
 * jlh6487
 * 16455
 * 
 * Sagar Krishnaraj
 * 2Kool4Skewl
 * 1-800-YOUR-MOM
 * 
 * Slip days used: <0>
 * Git URL: https://github.com/Hackerman64/422C_Project3
 * Fall 2016
 */

package assignment3;
import java.util.*;
import java.io.*;


public class DFS {
	
	WordLadder myLadder = new WordLadder();
	Set<String> hashWords;
	String[] the_D;
	String start;
	String end;
	int word_count;
	
	/*
	 * Constructor for DFS objects
	 */
	public DFS(Set<String> hash, String[] in, String s, String e){
		hashWords = hash;
		the_D = in;
		start = s;
		end = e;
	}
	
	
	
	/*
	 * Returns a word ladder between the 'start' and 'end' words using DFS.
	 */
	
	/* IDEAS:
	 * - If we reach the end of the dictionary array we need to remove the last word added and return
	 * - If we find that the next word matches (:D !!!) the we just return with the added word still there 
	 */
	public void runDFS(String word, int n){
		
		myLadder.addWord(word);
		word_count += 1;
		if (word.equals(end)){ 
			return; 
		}
		
		while (n < the_D.length){
			if (wordCompare(word, the_D[n])){ // Method to compare word and check for redundancy
				//myLadder.addWord(the_D[n]);
				runDFS(the_D[n], 0);
				if (myLadder.findWord(end)) { return; }
				myLadder.removeWord(the_D[n]);
				word_count -= 1;
			}
			n += 1;
		}
		myLadder.removeWord(word);   //Trying to see if it will remove the first word; may screw up lol
		word_count -= 1;
		return;
	}
	
	
	public boolean wordCompare(String word, String dic_word){
		
		//Check our WordLadder to see if already present
		if(myLadder.findWord(dic_word)){ return false; }
		
		int char_match = 0;
		for(int i = 0; i < 5; i += 1){ //Comparing each letter of both words
			if(word.charAt(i) == dic_word.charAt(i)){ char_match += 1; }
		}
		
		if (char_match == 4){ return true; }
		else { return false; }
	}
	

}
