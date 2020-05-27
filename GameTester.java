
package mmn11;

/**
 * @author dvir0
 *
 */
public class GameTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Game game = new Game();
		game.addWord("Abstract Class");
		game.addWord("environment variables");
		game.addWord("Virtual Machine");
		game.addWord("Web by thread");
		game.addWord("Hello World");
		game.addWord("World Wide Web");
		game.addWord("What A Wonderfull World");
		game.addWord("Red Black Tree");
		game.addWord("ubuntu");

		game.runGame();

	}

}
