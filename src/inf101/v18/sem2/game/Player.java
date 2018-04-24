package inf101.v18.sem2.game;

import inf101.v18.sem2.grid.IPosition;
import inf101.v18.sem2.grid.Position;

public class Player implements IPlayer {
	
	private SlotState colour;
	private String name;
	
	public Player(SlotState givenColour, String name) {
		this.colour = givenColour;
		this.name = name;
	}

	@Override
	public SlotState getColour() {
		return colour;
	}

	@Override
	public IPosition doTurn(IPosition pos, IGame game) {
		int Y = game.validMove(pos);
		if (Y >= 0) {
			return new Position(pos.getX(), Y);
		}
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

}
