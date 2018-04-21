package inf101.v18.sem2.game;

import inf101.v18.sem2.grid.IPosition;
import inf101.v18.sem2.grid.MyGrid2D;

public class ConnectFourRules {

	private final int SLOTS_IN_COLUMN = 6;
	private final int SLOTS_IN_ROW = 7;
	
	public ConnectFourRules() {
		
	}

	/*
	 * Check whether the last move created a winning state on the board
	 * 
	 * Any winning-situation created by the placement of a disc, 
	 * must involve the disc itself in this game, so this method 
	 * will only check possible winning lines horizontally, vertically 
	 * or diagonally +/- 3 spaces from the recently placed disc.
	 * 
	 * @param board
	 * 		the current grid of slotstates (the game board)
	 * @param X
	 * 		X-position of last placed disc in grid
	 * @param Y
	 * 		Y-position of last placed disc in grid
	 * 
	 * @return
	 * 		true if player who placed the disc at X,Y has won
	 */
	public boolean hasWon(MyGrid2D<SlotState> board, int X, int Y) {
		int count = 0;
		SlotState current = board.get(X, Y);
		
		//horizontal
		for (int x = Math.max(X-3, 0); x<Math.min(SLOTS_IN_ROW, X+4); x++) {
			count = (board.get(x, Y) == current) ? count+1 : 0;
			if (count >= 4)
				return true;
		}
		
		//vertical
		count = 0;
		for (int y = Math.max(Y-3, 0); y<Math.min(SLOTS_IN_COLUMN, Y+4); y++) {
			count = (board.get(X, y) == current) ? count+1 : 0;
			if (count >= 4)
				return true;
		}
		
		//diagonal ascending
		count = 0;
		for (int x = Math.max(X-3, 0), y = Y-(X-x); x<Math.min(SLOTS_IN_ROW, X+4); x++, y++) {
			 
			if (y >= 0 && y < Math.min(SLOTS_IN_COLUMN, Y+4)) {
				count = (board.get(x, y) == current) ? count+1 : 0;
				if (count >= 4)
					return true;
			} 
		}
		
		//diagonal descending
		count = 0;
		for (int x = Math.max(X-3, 0), y = Y+(X-x); x<Math.min(SLOTS_IN_ROW, X+4); x++, y--) {
			 
			if (y >= 0 && y < Math.min(SLOTS_IN_COLUMN, Y+4)) {
				count = (board.get(x, y) == current) ? count+1 : 0;
				if (count >= 4)
					return true;
			} 
		}
		
		return false;
	}

	
	/*
	 * A method to check whether a disc can be inserted in the clicked column
	 * @param board
	 * 		the current grid of slotstates (the game board)
	 * @param pos
	 * 		the position clicked
	 * @return
	 * 		Y-value of position in column pos.getX where disc can be inserted, 
	 * 		or -1 if column is full
	 * 
	 */
	public int validMove(MyGrid2D<SlotState> board, IPosition pos) {
		for (int i=0; i<SLOTS_IN_COLUMN; i++) {
			if (board.get(pos.getX(), i) == SlotState.EMPTY)
				return i;
		}
		
		return -1;
	}

}
