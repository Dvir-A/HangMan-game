package mmn11;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

public class GameFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ChoosenWord _word;
	private Word_data _wordsDB;
	//for the drawing of the hanged man 
	private MyPanel _hangManPanel;
	//for the characters buttons
	private ArrayList<JButton> _buttonsArr;
	private JPanel _buttonsPanel;
	private Color _buttonColor;
	public final int ABC_LEN = 26;
	//for the text
	private JPanel _labelPanel;
	private JLabel _currentLabel;
	
	public static final int PREFRRED_SIZE=600;
	
	public GameFrame(Word_data wordDB) {
		super("Hanged Man Game");
		this.setPreferredSize(new Dimension(PREFRRED_SIZE, PREFRRED_SIZE));
		this._wordsDB = wordDB;
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,600);
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		this._word=this._wordsDB.getRandWord();
		this._wordsDB.removeWord(this._word.get_choosenWord());
		this._hangManPanel = new MyPanel();
		this.add(_hangManPanel, BorderLayout.CENTER);
		this._hangManPanel.setVisible(true);
		
		//string to display the missing letters
		String hiddenStr = this.hideWord(_word.get_choosenWord());
		this._currentLabel = new JLabel(hiddenStr);
		this._currentLabel.setFont(new Font("f", Font.CENTER_BASELINE, this._currentLabel.getFont().getSize()*2));
		this._labelPanel = new JPanel(new BorderLayout(50,50));
		this._labelPanel.add(new JLabel(), BorderLayout.WEST);
		this._labelPanel.add(this._currentLabel, BorderLayout.CENTER);
		this.add(_labelPanel, BorderLayout.NORTH);
		
		//add buttons a to z
		this._buttonsPanel = new JPanel(new GridLayout(5, 6,10,10));
		this._buttonsArr = new ArrayList<JButton>(ABC_LEN);
		String currLetter = "a";
		for(int i=0;i<ABC_LEN;i++) {
			this._buttonsArr.add(i, new JButton(currLetter));
			JButton button = this._buttonsArr.get(i);
			char ch =currLetter.charAt(0);
			button.addActionListener(new ButtonsListener(ch));
			currLetter = currLetter.replace(ch, (char)(ch+1));
			this._buttonsPanel.add(button);
			
		}
		this._buttonColor = this._buttonsArr.get(0).getBackground();
		this.add(_buttonsPanel, BorderLayout.EAST);
	}

	public String hideWord(String word) {
		int len = word.length();
		String hiddenStr = "";
		for(int i = 0; i<len;i++) {
			if(((char)word.charAt(i))==' ') {
				hiddenStr += "- ";
			}else {
				hiddenStr += "_ ";
			}
		}
		return hiddenStr;
	}
	/**
	 * replace all the occurrence of ch in the output string from '_' to ch
	 * 
	 * @param ch - character to fill in the output string
	 */
	private void replace(char ch) {
		ArrayList<Integer> indexList = this._word.get_reapedWord().get(ch);
		if ((indexList == null) || indexList.isEmpty()) {
			return;
		}
		char[] chArr = this._currentLabel.getText().toCharArray();
		for (int i = 0; i < indexList.size(); i++) {
			chArr[indexList.get(i)] = ch;
		}
		this._currentLabel.setText(String.copyValueOf(chArr));
	}
	
	
	private void restart() {
		for(int i=0;i<this._buttonsArr.size();i++) {
			this._buttonsArr.get(i).setBackground(this._buttonColor);
		}
		this._hangManPanel.setDraw(0);
		this._hangManPanel.repaint();
		this._currentLabel.setText(this.hideWord((_word=_wordsDB.getRandWord()).get_choosenWord()));
		
	}
	
	private class ButtonsListener implements ActionListener{
		private char _buttonChar;
		
		public ButtonsListener(char ch) {
			this._buttonChar = ch;
		}
		@Override
		public void actionPerformed(ActionEvent event) {
			int buttonInd=(int)(this._buttonChar-'a');
			Color c = _buttonsArr.get(buttonInd).getBackground();
			if(c==Color.RED || c==Color.GREEN) {
				return;
			}
			char ch;
		    if(_word.get_reapedWord().get(ch=event.getActionCommand().charAt(0)).isEmpty()) {
		    	_buttonsArr.get(ch-'a').setBackground(Color.RED);
		    	_hangManPanel.setDraw(_hangManPanel.getDraw()+1);
		    	_hangManPanel.repaint();
		    }else {
		    	_buttonsArr.get(ch-'a').setBackground(Color.GREEN);
		    	replace(event.getActionCommand().charAt(0));
		    	if(!_currentLabel.getText().contains("_")) {
		    		doTheRestar("Congratulations ,You won!");
		    	}
		    }
		    if(_hangManPanel.getDraw()==MyPanel.DRAW_LIM) {
		    	
		    	doTheRestar("Game Over");
		    }
		}
		
		private void doTheRestar(String title) {
			int choise=JOptionPane.showConfirmDialog(GameFrame.this, "Do you whant to restart the game?",title,JOptionPane.YES_NO_OPTION);
	    	if(choise==JOptionPane.YES_OPTION) {
	    		restart();
	    	}else if(choise== JOptionPane.CLOSED_OPTION || choise == JOptionPane.NO_OPTION) {
	    		System.exit(0);
	    	}
		}
		
	}
}
