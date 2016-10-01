package assignment3;
import java.util.*;
import java.io.*;

/*
 * Class that creates our word ladders from 
 */
public class WordLadder {
	
	Set<String> full_list;
	
	public WordLadder(){
		full_list = new LinkedHashSet<String>();
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
		
		Iterator<String> myIt = full_list.iterator();
		while(myIt.hasNext()){
			System.out.println(myIt.next());
		}
	}
	
	public ArrayList<String> convertToArrayList(){
		
		ArrayList<String> output = new ArrayList<String>();
		Iterator<String> myIt = full_list.iterator();
		while(myIt.hasNext()){
			output.add(myIt.next());
		}
		
		return output;
		
	}
	

}
