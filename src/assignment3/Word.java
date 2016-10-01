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

public class Word {
	
	String value;
	Word parent;
//	boolean isVisited = false;
	
	public Word(String value, Word parent) {
		this.value = value;
		this.parent = parent;
//		this.isVisited = false;
	}
	
	@Override
	public boolean equals(Object obj) {
	    return value.equals(( (Word) obj).value);
	}

	@Override
	public int hashCode() {
		int hash = 0;
	    return hash;
	}
	
}
