package inf101.v18.sem2.tests;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

import inf101.v18.sem2.game.ConnectFour;
import inf101.v18.sem2.game.IGame;
import inf101.v18.sem2.game.IPlayer;
import inf101.v18.sem2.game.Player;
import inf101.v18.sem2.game.SlotState;
import inf101.v18.sem2.grid.Position;
import inf101.v18.sem2.gui.GUIFrame;
import inf101.v18.sem2.gui.GameGUI;
import inf101.v18.sem2.gui.listeners.IClickListener;

public class GameBoardTest {

	@Test
	void testGrid() {
		IGame connectFour = new ConnectFour();
		new GUIFrame(new GameGUI(Arrays.asList(connectFour)));
		
		int height = connectFour.getHeight();
		int width = connectFour.getWidth();
		IPlayer player = new Player(SlotState.RED, "TestPlayer");
		
		//check that validMove returns Y-value of the bottom row available when choosing top of column
		for (int i=0; i<width; i++) {
			int Y = connectFour.validMove(new Position(i,height-1));
			assertTrue(Y == 0);
			connectFour.placeDisc(new Position(i, Y), player);
		}
		
		//whole bottom row should be occupied, test that validMove now returns Y = 1 (2nd row)
		int Y = connectFour.validMove(new Position(0, 0));
		assertTrue(Y == 1);
		
		//fill column 0 and check that validMove on that column returns -1 (as it should when column is full)
		for (int i=0; i<height; i++) {
			connectFour.placeDisc(new Position(0, i), player);
		}
		Y = connectFour.validMove(new Position(0,0));
		assertTrue(Y == -1);
		
	}
	
	@Test
	void testPvP() {
		IGame connectFour = new ConnectFour();
		IClickListener click = (IClickListener) connectFour;
		new GUIFrame(new GameGUI(Arrays.asList(connectFour)));
		int height = connectFour.getHeight();
		int width = connectFour.getWidth();
		
		connectFour.setMenuChoice("pvp");
		
		//starting player in pvp is always yellow
		click.clicked(new Position(0,0));
		
	}
	
	void testWinCondition() {
		IGame connectFour = new ConnectFour();
		new GUIFrame(new GameGUI(Arrays.asList(connectFour)));
		
		int height = connectFour.getHeight();
		int width = connectFour.getWidth();
		
		
	}

}
