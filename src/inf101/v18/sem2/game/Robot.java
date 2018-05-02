package inf101.v18.sem2.game;

import java.util.Random;

import inf101.v18.sem2.grid.IPosition;
import inf101.v18.sem2.grid.Position;

public class Robot implements IRobot {

	private Random random = new Random();
	private SlotState colour;
	private String name;
	
	public Robot(SlotState givenColour, String name) {
		this.colour = givenColour;
		this.name = name;
	}

	@Override
	public SlotState getColour() {
		return colour;
	}

	@Override
	public IPosition doTurn(IPosition pos, IConnectFour game) {
		
		return randomTurn(game);
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IPosition randomTurn(IConnectFour game) {
		Position newPos;
		int Y;
		
		do {
			newPos = new Position(random.nextInt(game.getWidth()), random.nextInt(game.getHeight()));
			Y = game.validMove(newPos);
		} while (Y<0);
		 
		return new Position(newPos.getX(), Y);
	}
}
