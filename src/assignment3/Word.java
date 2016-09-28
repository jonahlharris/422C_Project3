package assignment3;

public class Word {
	
	String value;
	Word parent;
	boolean isVisited = false;
	
	public Word(String value, Word parent) {
		this.value = value;
		this.parent = parent;
		this.isVisited = false;
	}
	
}
