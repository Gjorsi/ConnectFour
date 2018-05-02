package inf101.v18.sem2.tests;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

import inf101.v18.sem2.game.ConnectFour;
import inf101.v18.sem2.game.IConnectFour;
import inf101.v18.sem2.game.IPlayer;
import inf101.v18.sem2.game.Player;
import inf101.v18.sem2.game.SlotState;
import inf101.v18.sem2.grid.Position;
import inf101.v18.sem2.gui.GUIFrame;
import inf101.v18.sem2.gui.GameGUI;
import inf101.v18.sem2.gui.listeners.IClickListener;

public class GameTest {

	@Test
	void testGrid() {
		IConnectFour connectFour = new ConnectFour();
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
	
	void testWinCondition() {
		IConnectFour connectFour = new ConnectFour();
		new GUIFrame(new GameGUI(Arrays.asList(connectFour)));
		IPlayer playerR = new Player(SlotState.RED, "TestPlayerRED");
		IPlayer playerY = new Player(SlotState.YELLOW, "TestPlayerYELLOW");
		
		//horizontal win
		for (int i=0; i<4; i++) {
			connectFour.placeDisc(new Position(i,0), playerY);
		}
		assertTrue(connectFour.hasWon(new Position(0,0)));
		
		//vertical win
		for (int i=1; i<4; i++) {
			connectFour.placeDisc(new Position(0,i), playerY);
		}
		assertTrue(connectFour.hasWon(new Position(0,3)));
		
		//diagonal ascending win
		for (int i=0; i<4; i++) {
			connectFour.placeDisc(new Position(i,i), playerY);
		}
		assertTrue(connectFour.hasWon(new Position(3,3)));
		
		//change the central disc to the 3 win conditions to playerR colour and check that it is no longer considered a win
		connectFour.placeDisc(new Position(0,0), playerR);
		assertFalse(connectFour.hasWon(new Position(0,3)));
		assertFalse(connectFour.hasWon(new Position(3,0)));
		assertFalse(connectFour.hasWon(new Position(3,3)));
		
	}

}
