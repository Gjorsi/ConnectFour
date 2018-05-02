package inf101.v18.sem2.game;

import inf101.v18.sem2.grid.IPosition;

public interface IConnectFour extends IGame {
	
	/*
	 * Check if chosen pos is valid, i.e. is there an empty slot to put the disc in
	 * 
	 * @param pos
	 * 		clicked IPosition in grid
	 * @return
	 * 		a value from -1 to but not including height of grid, -1 means that the move is not valid
	 * 		0 or more gives the Y-coordinate of where the disc should be placed
	 */
	public int validMove(IPosition pos);
	
	/*
	 * place a disc at given pos
	 * 
	 * @param pos
	 * 		IPosition in grid to place the disc
	 * @param player
	 * 		The player who wants to place a disc (use this param to determine which type of disc to place)
	 */
	public void placeDisc(IPosition pos, IPlayer player);
	
	/*
	 * get SlotState at position - used by tests
	 * 
	 * @param pos
	 * 		IPosition in grid
	 * 
	 * @return
	 * 		SlotState
	 */
	public SlotState get(IPosition pos);
	
	public boolean hasWon(IPosition pos);
}
