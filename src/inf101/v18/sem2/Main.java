package inf101.v18.sem2;

import java.util.Arrays;

import inf101.v18.sem2.game.ConnectFour;
import inf101.v18.sem2.game.IGame;
import inf101.v18.sem2.gui.GUIFrame;
import inf101.v18.sem2.gui.GameGUI;
import inf101.v18.sem2.gui.IScreen;
import inf101.v18.sem2.gui.SwingScreen;

public class Main {
	
	public static void main(String[] args) {
		new GUIFrame(startGame());
	}

	public static GameGUI startGame() {
//		IGame life = new Life(15, 17);
//		IGame sweeper = new Minesweeper(15, 17);
//		IGame zombie = new Zombiesweeper(15, 17);
//		IGame listener = new ListenerDemo(15, 17);
		
		IGame connectFour = new ConnectFour();

		return new GameGUI(Arrays.asList(connectFour));
	}

}
