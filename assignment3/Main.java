/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Sagar Krishnaraj>
 * <sk37433>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2016
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		
		// prompt user for input
		String userInput = prompt(kb);
		
		// read in and parse the words
		ArrayList<String> userInputList = parse(userInput);
		String startWord = userInputList.get(0);
		String endWord = userInputList.get(1);
		
		// BFS
		getWordLadderBFS(startWord, endWord);
		// TODO methods to read in words, output ladder
		
		// print ladder
		// printLadder();
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
	}
	
	// prompt user for input
	public static String prompt(Scanner keyboard) {
		System.out.println("\nWelcome to Word Ladder. Please enter two five-letter words, or type /quit to exit: ");
		String userInput = keyboard.nextLine();
		return userInput;
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(String userInput) {
		String[] userInputSplit = userInput.split("\\s+");
		ArrayList<String> userInputList = new ArrayList<String>(Arrays.asList(userInputSplit));
		// DEBUG OUTPUT
		System.out.println(Arrays.toString(userInputList.toArray()));
		if (userInputList.size() != 2) {
			System.out.println("\nThanks for playing. Goodbye!\n");
			System.exit(0);
		}
		return userInputList;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		// TODO some code
    	ArrayList<String> currWords = new ArrayList<String>();
    	int currIdx = 0;
    	currWords.add(start);
		Set<String> dict = makeDictionary();
    	while (!currWords.contains(end)) {
    		expandSearchRadius(currWords, dict, start, end, currIdx);
//    		removeExtraWords();
    	}
		// TODO more code
		
		return null; // replace this line later with real return
	}
    
    public static ArrayList<String> expandSearchRadius(ArrayList<String> currWords, Set<String> dict, String start, String end, int currIdx) {
    	int tempIdx, currEndIdx = currIdx;
    	// find all words one letter change away
    	for (int i=tempIdx; i<currWords.size(); i++) {
    		String currWord = currWords.get(i);
    		changeOneLetter(currWords, currWord);
    		currEndIdx += 1;
    	}
    	// remove all illegal/repeated words from currWords
    	for (int i=tempIdx; i<currWords.size(); i++) {
    		if (!dict.contains(currWords.get(i)) || currWords.containsBeforeIt()) {
    			currWords.remove(i);
    		}
    	}
    	return null;
    }
    
    public static ArrayList<String> changeOneLetter(ArrayList<String> currWords, String currWord) {
    	char[] currWordArray = currWord.toCharArray();
    	char[] tempCurrWordArray = currWordArray;
    	String tempCurrWord = String.valueOf(tempCurrWordArray);
    	for (int j=0; j<5; j++) {
    		for (char k='a'; k<'z'; k++) {
    			tempCurrWordArray = currWordArray;
    			tempCurrWordArray[j] = k;
    			tempCurrWord = String.valueOf(tempCurrWordArray);
    			currWords.add(tempCurrWord);
    		}
    	}
    	return null;
    }
    
//    public static ArrayList<String> removeExtraWords(ArrayList<String> currWords, String currWord);
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		// System.out.println("A " + ladder.size() + "-rung word ladder exists between" + startWord + " and " + endWord + " <finish>. ");
		for (int i=0; i<ladder.size(); i++) {
			System.out.println(ladder.get(i));
		}
	}
	// TODO
	// Other private static methods here
}
