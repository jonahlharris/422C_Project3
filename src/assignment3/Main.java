/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * 
 * <Sagar Krishnaraj>
 * <sk37433>
 * <Student1 5-digit Unique No.>
 * 
 * Jonah Harris
 * jlh6487
 * 16455
 * 
 * Slip days used: <0>
 * Git URL: https://github.com/Hackerman64/422C_Project3
 * Fall 2016
 */

/*
 * BEFORE COMMITTING:  ////////////////////////////////////////////////////////////////////////////////////////
 * 
 * 		UNCOMMENT line getWordLadderBFS in main
 * 		UNCOMMENT method expandSearchRadius in Main
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
		Word startW = new Word(startWord, null);
		String endWord = userInputList.get(1);
		Word endW = new Word(endWord, null);
		
		// BFS
		getWordLadderBFS(startW, endW);

		
		// DFS
		// getWordLadderDFS(startWord, endWord);
		
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
	
	/*
	 * Returns the word ladder for the two specified words using Depth First Search (DFS)
	 * 
	 * Jonah
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		Set<String> dict = makeDictionary();
		Object[] array = dict.toArray();
		
		int n = 0;
		String[] the_D = new String[array.length];
		//System.out.println(array.length);
		while (n < array.length){
			
			the_D[n] = (String)array[n]; 
			//System.out.println(the_D[i]);
			n += 1;
		}
		
		n = 0;
		DFS startDFS = new DFS(the_D, start, end);
		//startDFS. word ladder clear
		startDFS.runDFS(start, n);
		
		
		return null; // replace this line later with real return
	}
	
	/*
	 * Returns the word ladder for the two specified words using Breadth First Search (BFS)
	 * 
	 * Sagar
	 */
	
    public static ArrayList<String> getWordLadderBFS(Word start, Word end) {
		
		// TODO some code
    	ArrayList<Word> currWords = new ArrayList<Word>();
    	int currIdx = 0;
    	currWords.add(start);
    	int currStartIdx = currIdx;
    	int currEndIdx = currWords.size();
		Set<String> dict = makeDictionary();
    	while (!currWords.contains(end)) {
    		currWords = expandSearchRadius(currWords, dict, start, end, currStartIdx, currEndIdx);
    		currStartIdx = currEndIdx;
    		currEndIdx = currWords.size();
    	}
    	
    	// create word ladder
    	ArrayList<Word> printLadder = new ArrayList<Word>();
    	printLadder.add(end);
    	Word printWord = end;
    	while (printWord.parent != start) {
    		printWord = printWord.parent;
    		printLadder.add(printWord);
    	}
    	printLadder.add(start);
    	
    	// print word ladder
    	System.out.println("a " + String.valueOf(printLadder.size()-2) + "-rung word ladder exists between " + start.value + " and " + end.value);
    	for (int k=0; k<printLadder.size(); k++) {
    		System.out.println(printLadder.get(printLadder.size()-k-1).value);
    	}
		
		return null; // replace this line later with real return
	}
    
    public static ArrayList<Word> expandSearchRadius(ArrayList<Word> currWords, Set<String> dict, Word start, Word end, int currStartIdx, int currEndIdx) {
    	// find all words one letter change away
    	for (int i=currStartIdx; i<currEndIdx; i++) {
    		Word currWord = currWords.get(i);
    		currWords = changeOneLetter(currWords, currWord, dict);
    	}
    	return currWords;
    }
    
    public static ArrayList<Word> changeOneLetter(ArrayList<Word> currWords, Word currWord, Set<String> dict) {
    	char[] currWordArray = currWord.value.toCharArray();
    	char[] tempCurrWordArray = currWordArray;
    	String tempCurrWordStr = String.valueOf(tempCurrWordArray);
    	for (int j=0; j<5; j++) {
    		for (char k='a'; k<'z'; k++) {
    			tempCurrWordArray = currWordArray;
    			tempCurrWordArray[j] = k;
    			tempCurrWordStr = String.valueOf(tempCurrWordArray);
    			Word tempCurrWord = new Word(tempCurrWordStr, currWord);
    			// checks if word is valid/hasn't been repeated before
    			boolean isValid = checkIfValid(tempCurrWord, currWords, dict);
    			if (isValid) {
    				currWords.add(tempCurrWord);
    			}
    		}
    	}
    	return currWords;
    }
    
    // check if currWord is legal (in the dict) and hasn't been added to ArrayList currWords before
    public static boolean checkIfValid(Word checkWord, ArrayList<Word> currWords, Set<String> dict) {
    	boolean test1 = dict.contains(checkWord.value);
    	boolean test2 = (currWords.indexOf(checkWord) == -1);
		if (test1 && test2) {
			return true;
		}
    	return false;
	}
    
//    public static ArrayList<String> removeExtraWords(ArrayList<String> currWords, String currWord);
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("short_dict.txt"));  //Short dictionary for testing
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
