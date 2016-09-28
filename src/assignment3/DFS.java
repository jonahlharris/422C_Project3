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
	String[] the_D;
	String start;
	String end;
	
	/*
	 * Constructor for DFS objects
	 */
	public DFS(String[] in, String s, String e){
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
		
		if (n > the_D.length){ 
			// ---> need to erase last word in path
			return; 
		}
		if (word.equals(end)){ return; }
		
		// --->   Add word to ladder
		for (int i = 0; i < the_D.length; i += 1){
			
			if(wordCompare(the_D[i])){ runDFS(the_D[i], n + 1); }
			
			
			
		}
		
		
		
		
		
		return; //CHANGE 
	}
	
	
	public boolean wordCompare(String word){
		
		
		
		
		return false;
	}
	

}
