package mmn11;

import javax.swing.JOptionPane;
import java.util.*;

/**
 * @author Dvir A.
 * 
 */
public class Game extends Word_data {
	private int _geuss;
	private String _choosenWord;
	private String _currOutString;
	private ArrayList<Character> _notYetUsed;
	

	final int ABC_LEN = 26;
	final int ASCII_a = 97;
	final int ASCII_A = 65;

	/**
	 * Initialize a new game
	 */
	public Game() {
		super();
		this._notYetUsed = new ArrayList<Character>();
		for (int i = 0; i < ABC_LEN; i++) {
			this._notYetUsed.add((char) (ASCII_a + i));
		}
		_currOutString = "";
		_geuss = 0;
	}

	/**
	 * run the game will show the user a dialog box with needed string(the character
	 * that not yet found from the answer will show as '_') and a bank of words that
	 * not used yet.
	 */
	public void runGame() {
		String ans;
		this._choosenWord = this.getRandWord().get_choosenWord().toLowerCase();
		_currOutString = "";
		for (int i = 0; i < this._choosenWord.length(); i++) {
			if (this._choosenWord.charAt(i) != ' ') {
				_currOutString += "_ ";
			} else {
				_currOutString += "- ";
			}
		}
		int res_indexOf;
		while (this._currOutString.contains("_") && !this._notYetUsed.isEmpty()) {  //change this
			if ((ans = layOut()) != null)  { //change this
				if ((res_indexOf = this._notYetUsed.indexOf(ans.charAt(0))) != -1) {
					this._notYetUsed.remove(res_indexOf);
					this._notYetUsed.trimToSize();
					this._geuss++;
				}
				replace(ans.charAt(0));
			}
		}
		String restartNeeded = JOptionPane
				.showInputDialog("\nYou finish after " + this._geuss + " geuss to get to the word :\n"
						+ this._choosenWord + "\nEnter 1 if you whant to restart the game , else enter another key\n");
		if (restartNeeded == null) {
			System.exit(0);
		}
		restartNeeded = this.checkInput(restartNeeded.replaceAll(" ", ""));
		if (restartNeeded!=null && restartNeeded.charAt(0) == '1') {
			restart();
		}
	}

	
	/**
	 * initialize an old game for another run
	 */
	private void restart() {
		this._notYetUsed = new ArrayList<Character>();
		for (int i = 0; i < ABC_LEN; i++) {
			this._notYetUsed.add((char) (ASCII_a + i));
		}
		_currOutString = "";
		_geuss = 0;
		runGame();
	}

	/**
	 * replace all the occurrence of ch in the output string from '_' to ch
	 * 
	 * @param ch - character to fill in the output string
	 */
	private void replace(char ch) {
		ArrayList<Integer> indexList = getIndexOf(ch);
		if ((indexList == null) || indexList.isEmpty()) {
			return;
		}
		char[] chArr = this._currOutString.toCharArray();
		for (int i = 0; i < indexList.size(); i++) {
			chArr[indexList.get(i)] = ch;
		}
		this._currOutString = String.copyValueOf(chArr);
	}

	/**
	 * this function get the indexes of all the characters ch in the chosen word to
	 * an ArrayList<Integer> and return them.
	 * 
	 * @param ch - character to get it's indexes in the chosen word
	 * @return an ArrayList with those indexes
	 */
	private ArrayList<Integer> getIndexOf(char ch) {
		ArrayList<Integer> indexOfList = new ArrayList<Integer>();
		int currInd = 0;
		while ((currInd = this._choosenWord.indexOf(ch, currInd)) != -1) {
			indexOfList.add(2 * currInd);
			currInd++;
		}
		return indexOfList;
	}

	/**
	 * Checking if ch is letter in ASCII
	 * 
	 * @param ch - a character
	 * @return true if ch is a letter in ASCII , else return false
	 */
	public Boolean isalpha(char ch) {
		return ((ch >= ASCII_A && ch <= (ASCII_A + ABC_LEN)) || (ch >= ASCII_a && ch <= (ASCII_a + ABC_LEN))) ? true
				: false;
	}

	/**
	 * make the dialog for this chosen word, get the input and check the spelling.
	 * exit when user cancel.
	 * 
	 * @return the input if the spelling is correct , else return null
	 */
	private String layOut() {
		String ans = JOptionPane
				.showInputDialog("This is your " + this._geuss + " geuss , you can enter your next gess now.. \nBank : "
						+ this._notYetUsed.toString() + "\n\n" + this._currOutString);
		if (ans == null) {
			System.exit(0);
		}
		ans = this.checkInput(ans.replaceAll(" ", ""));
		return (ans != null) ? ans.toLowerCase() : null;
	}

	private String checkInput(String strToCheck) {
		if (strToCheck.length() != 1 || !isalpha(strToCheck.charAt(0))) {
			return null;
		}
		return strToCheck.toLowerCase();
	}
}
