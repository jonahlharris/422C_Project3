package assignment3;
import java.util.*;
import java.io.*;

/*
 * Class that creates our word ladders from 
 */
public class WordLadder {
	
	Set<String> full_list;
	
	public WordLadder(){
		full_list = new HashSet<String>();
	}
	
	public void addWord(String word){
		full_list.add(word);
	}
	
	public void removeWord(String word){
		full_list.remove(word);
	}
	
	public boolean findWord(String word){
		return full_list.contains(word);
	}
	
	public void printLadder(){
		// Need to implement
	}
	
	

}
