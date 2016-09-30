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
