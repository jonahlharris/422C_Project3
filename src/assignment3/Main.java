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
 * Slip days used: <1>
 * Git URL: https://github.com/Hackerman64/422C_Project3
 * Fall 2016
 */

package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants.

	static int word_count;
	static String start;
	static String end;
	static String userInput;
	
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
		userInput = prompt(kb);
		
		// read in and parse the words
		ArrayList<String> userInputList = parse(kb);
		String startWord = userInputList.get(0);
		Word startW = new Word(startWord, null);
		String endWord = userInputList.get(1);
		Word endW = new Word(endWord, null);
		
		start = startWord;
		end = endWord;
		
		// BFS
		getWordLadderBFS(startW, endW);
		
		// DFS
		ArrayList<String> output = getWordLadderDFS(startWord, endWord);
		printLadder(output);
	}
	
	public static void initialize() {
		//Not used 
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
	public static ArrayList<String> parse(Scanner keyboard) {
		String[] userInputSplit = userInput.split("\\s+");
		ArrayList<String> userInputList = new ArrayList<String>(Arrays.asList(userInputSplit));
		// make uppercase
		if (userInputList.size() != 2) {
			System.out.println("\nThanks for playing. Goodbye!\n");
			System.exit(0);
		}
		String temp1 = userInputList.get(0).toUpperCase();
		String temp2 = userInputList.get(1).toUpperCase();
		userInputList.remove(1);
		userInputList.remove(0);
		userInputList.add(temp1);
		userInputList.add(temp2);
		// DEBUG OUTPUT
//		System.out.println("The first word is:" + userInputList.get(0) + "/end\n");
//		System.out.println("The second word is:" + userInputList.get(1) + "/end\n");
//		System.out.println(Arrays.toString(userInputList.toArray()));
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
		while (n < array.length){
			the_D[n] = (String)array[n]; 
			n += 1;
		}
		
		n = 0;
		DFS startDFS = new DFS(dict, the_D, start, end);
		startDFS.runDFS(start, n);

		if (!(startDFS.myLadder.findWord(end))){ 
			startDFS.myLadder.removeWord(start);
			word_count = 0;
		}
		else { word_count = startDFS.word_count - 2; }
		return startDFS.myLadder.convertToArrayList();
	}
	
	/*
	 * Returns the word ladder for the two specified words using Breadth First Search (BFS)
	 * 
	 * Sagar
	 */
	
    public static ArrayList<String> getWordLadderBFS(Word start, Word end) {
		
		// list of current words - queue
    	ArrayList<Word> currWords = new ArrayList<Word>();
    	int currIdx = 0;
    	currWords.add(start);
    	// what range of words have been unchecked in the queue
    	int currStartIdx = currIdx;
    	int currEndIdx = currWords.size();
		Set<String> dict = makeDictionary();
		
		// not done yet
		boolean goAgain = true;
		int endWordIdx = 0;
    	while (goAgain) {
    		// expand the radius as end word is not found yet
    		currWords = expandSearchRadius(currWords, dict, start, end, currStartIdx, currEndIdx);
    		currStartIdx = currEndIdx;
    		currEndIdx = currWords.size();
    		
    		// copy the words into a string array for easier manipulation
    		ArrayList<String> tempClone = new ArrayList<String>(currWords.size());
        	for (Word item : currWords) tempClone.add(item.value);
        	goAgain = !tempClone.contains(end.value);
        	endWordIdx = tempClone.indexOf(end.value);
    	}
    	
    	Word endWordFinal = currWords.get(endWordIdx);
    	// create word ladder
    	ArrayList<String> printLadder = new ArrayList<String>();
//    	printLadder.add(end);
    	Word printWord = endWordFinal;
    	while (printWord.parent != start) {
    		printWord = printWord.parent;
    		printLadder.add(printWord.value);
    	}
//    	printLadder.add(start);
//    	ArrayList<String> printLadder2 = new ArrayList<String>();
//    	for (a : printLadder) {
//    		
//    	}
    	Collections.reverse(printLadder);
    	
    	System.out.println("a " + String.valueOf(printLadder.size()) + "-rung word ladder exists between " + start.value + " and " + end.value);
    	printLadder(printLadder);
    	
    	// print word ladder
		
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
    		tempCurrWordArray = currWordArray;
    		char currChar = currWordArray[j];
    		for (char k='A'; k<='Z'; k++) {
    			tempCurrWordArray = currWordArray;
    			tempCurrWordArray[j] = k;
    			tempCurrWordStr = String.valueOf(tempCurrWordArray);
    			Word tempCurrWord = new Word(tempCurrWordStr, currWord);
    			// checks if word is valid/hasn't been repeated before
    			boolean isValid = checkIfValid(tempCurrWord, currWords, dict);
    			if (isValid) {
    				currWords.add(tempCurrWord);
    			}
    			tempCurrWordArray[j] = currChar;
    		}
    	}
    	return currWords;
    }
    
    // check if currWord is legal (in the dict) and hasn't been added to ArrayList currWords before
    public static boolean checkIfValid(Word checkWord, ArrayList<Word> currWords, Set<String> dict) {
//    	System.out.println("The word is:" + checkWord.value + "/end\n");
    	boolean test1 = dict.contains(checkWord.value);
    	
    	ArrayList<String> tempClone = new ArrayList<String>(currWords.size());
    	for (Word item : currWords) tempClone.add(item.value);
    	
    	boolean test2 = (!tempClone.contains(checkWord.value));
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
			infile = new Scanner (new File("five_letter_words.txt"));  //Short dictionary for testing   Long Dictionary: five_letter_words.txt   Short Dictionary: short_dict.txt
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
		if (ladder.contains(start)) { System.out.print("a " + word_count + "-rung word ladder exists between " + start.toLowerCase() + " and " + end.toLowerCase() + ".\n"); }
		else { System.out.print("no word ladder can be found between " + start + " and " + end + ".\n"); }
		Iterator<String> myIt = ladder.iterator();
		while(myIt.hasNext()){
			String out = myIt.next();
			System.out.println(out.toLowerCase());		//If no ladder then nothing will be printed
		}
	}
	// TODO
	// Other private static methods here
}
