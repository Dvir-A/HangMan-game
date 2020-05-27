package mmn11;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * @author Dvir
 *
 */
public class Word_data {
	private ArrayList<String> _arrOfWords;

	/**
	 * initialize a new data base of words
	 */
	protected Word_data() {
		_arrOfWords = new ArrayList<String>();
	}

	/**
	 * add a new word to the word data base
	 * 
	 * @param word - a new word to add
	 */
	public void addWord(String word) {
		if (!_arrOfWords.isEmpty() && _arrOfWords.contains(word)) {
			return;
		}
		_arrOfWords.add(word);
	}

	public boolean isEmpty() {
		return this._arrOfWords.isEmpty();
	}
	/**
	 * remove word from the word data base
	 * 
	 * @param word - word to remove
	 */
	public void removeWord(String word) {
		if (_arrOfWords.isEmpty() || !_arrOfWords.contains(word)) {
			return;
		}
		_arrOfWords.remove(word);
		_arrOfWords.trimToSize();
	}

	/**
	 * get random word from the data base
	 * 
	 * @return - random word from the words data base
	 */
	protected ChoosenWord getRandWord() {
		SecureRandom rand = new SecureRandom();
		if (this._arrOfWords.isEmpty()) {
			return null;
		}
		int choosenIndex = rand.nextInt(_arrOfWords.size());
		return new ChoosenWord(_arrOfWords.get(choosenIndex));
	}
	
	public File makeDataFile(String fileName) throws IOException {
		if(this._arrOfWords==null) {
			return null;
		}
		File toMake = new File(fileName);
		toMake.createNewFile();
		FileWriter writher = new FileWriter(toMake);
		for(int i=0;i<this._arrOfWords.size();i++) {
			writher.write(this._arrOfWords.get(i)+"\n");
		}
		writher.close();
		return toMake;
	}
}
