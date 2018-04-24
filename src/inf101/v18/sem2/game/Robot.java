package inf101.v18.sem2.game;

import inf101.v18.sem2.grid.IPosition;

public class Robot implements IRobot {

	private SlotState colour;
	private String name;
	
	public Robot(SlotState givenColour, String name) {
		this.colour = givenColour;
		this.name = name;
	}

	@Override
	public SlotState getColour() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPosition doTurn(IPosition pos, IGame game) {
		
		
		
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

}
