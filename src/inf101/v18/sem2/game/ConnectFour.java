package inf101.v18.sem2.game;

import java.util.Arrays;
import java.util.List;

import inf101.v18.sem2.gui.listeners.IClickListener;
import inf101.v18.sem2.gui.listeners.ITimeStepListener;
import inf101.v18.sem2.grid.IPosition;
import inf101.v18.sem2.grid.MyGrid2D;
import inf101.v18.sem2.grid.Rectangle;
import inf101.v18.sem2.gui.IImage;
import inf101.v18.sem2.gui.IUserInterface;

public class ConnectFour implements IGame, IClickListener, ITimeStepListener {

	private int width = 7;
	private int height = 6;
	private MyGrid2D<SlotState> board;
	private IUserInterface ui;
	private boolean solo = true;

	public ConnectFour() {
		
	}

	@Override
	public boolean canChangeSize() {
		return false;
	}

	@Override
	public List<String> getBoardSizes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCellHeight() {
		// TODO Auto-generated method stub
		return 70;
	}

	@Override
	public int getCellWidth() {
		// TODO Auto-generated method stub
		return 70;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public IImage getImageAt(IPosition pos) {
		// TODO Auto-generated method stub
		return board.get(pos).getImage();
	}

	@Override
	public List<String> getMenuChoices() {
		return Arrays.asList("AI", "PvP");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Connect Four";
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void newGame() {
		board = new MyGrid2D<SlotState>(new Rectangle(width, height));
		for (IPosition pos : board) {
			board.set(pos, SlotState.EMPTY);
		}
	}

	@Override
	public void setMenuChoice(String s) {
		switch (s) {
		case "AI":
			solo = true;
		case "PvP":
			solo = false;
		}
		ui.newGame();
	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setup(IUserInterface ui) {
		ui.addClickListener(this);
		ui.addTimeStepListener(this);

		ui.setPlayPauseButton(false);
		ui.setSpeedButton(false);
		ui.setStepButton(false);

		// we use the time step facility to get notified every second, to update
		// our timer
		ui.setSpeeds(1000, 1000, 1000);
		this.ui = ui;
	}

	@Override
	public void timeStep(int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clicked(IPosition pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rightClicked(IPosition pos) {
		// TODO Auto-generated method stub
		
	}

}
