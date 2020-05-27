package mmn11;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class HangMan {
	private File _file;
	private GameFrame myFrame;
	
	public HangMan() throws IOException {
		Word_data wordsDB = new Word_data();  // init a new data base
		JOptionPane.showMessageDialog(null, "Choose file to init the words storage or click cancel on file chooser window");
		// let the user choose file
		JFileChooser fileChooser = new JFileChooser(); 
		fileChooser.setVisible(true);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int res = fileChooser.showOpenDialog(null);
		if(res == JFileChooser.CANCEL_OPTION) {
			//System.exit(1);
			init(wordsDB);
		}else {
			this._file = fileChooser.getSelectedFile();
			//scan data for the game data base
			Scanner scanFile = new Scanner(this._file);
			while(scanFile.hasNext()) {
				wordsDB.addWord(scanFile.nextLine());
			}
			scanFile.close();
		}
		this.myFrame = new GameFrame(wordsDB);
	}
	
	
	public void runGame() {
		myFrame.setVisible(true);		
	}
	
	
	private void init(Word_data wordsDB) {
		wordsDB.addWord("Abstract Class");
		wordsDB.addWord("environment variables");
		wordsDB.addWord("Virtual Machine");
		wordsDB.addWord("Web by thread");
		wordsDB.addWord("Hello World");
		wordsDB.addWord("World Wide Web");
		wordsDB.addWord("What A Wonderfull World");
		wordsDB.addWord("Red Black Tree");
		wordsDB.addWord("ubuntu");
	} 
	public static void main(String args[]) throws IOException {
		HangMan hm = new HangMan();
		hm.runGame();
	}
}
