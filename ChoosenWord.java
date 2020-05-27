package mmn11;

import java.util.*;

public class ChoosenWord{
	private String _choosenWord;
	private Hashtable<Character, ArrayList<Integer>> _reapedWord;
	
	public ChoosenWord(String word) {
		this._choosenWord = word.toLowerCase();
		this._reapedWord = new Hashtable<Character, ArrayList<Integer>>();
		for(int i = 0;(i+'a')<='z';i++) {
			this._reapedWord.put((char)(i+'a'), this.getIndexOf((char)(i+'a')));
		}
	}

	/** this function get the indexes of all the characters ch in the chosen word to
	 * an ArrayList<Integer> and return them.
	 * 
	 * @param ch - character to get it's indexes in the chosen word
	 * @return an ArrayList with those indexes
	 */
	private ArrayList<Integer> getIndexOf(char ch) {
		ArrayList<Integer> indexOfList = new ArrayList<Integer>();
		int currInd = 0;
		while ((currInd = this._choosenWord.indexOf(ch, currInd)) != -1) {
			indexOfList.add(2* currInd); 
			currInd++;
		}
		return indexOfList;
	}
	
	/**
	 * @return the _choosenWord
	 */
	public String get_choosenWord() {
		return _choosenWord;
	}

	

	/**
	 * @return the _reapedWord
	 */
	protected Hashtable<Character, ArrayList<Integer>> get_reapedWord() {
		return _reapedWord;
	}
}
