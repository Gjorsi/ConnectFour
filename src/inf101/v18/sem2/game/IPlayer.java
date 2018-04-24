package inf101.v18.sem2.game;

import inf101.v18.sem2.grid.IPosition;

public interface IPlayer {
	
	public SlotState getColour();
	
	public String getName();
	
	public IPosition doTurn(IPosition pos, IGame game);
}
