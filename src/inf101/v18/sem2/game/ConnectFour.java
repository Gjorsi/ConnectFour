package inf101.v18.sem2.game;

import java.util.List;

import inf101.v18.sem2.grid.IPosition;
import inf101.v18.sem2.gui.IImage;
import inf101.v18.sem2.gui.IUserInterface;

public class ConnectFour implements IGame {

	public ConnectFour() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canChangeSize() {
		// TODO Auto-generated method stub
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
		return 0;
	}

	@Override
	public int getCellWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IImage getImageAt(IPosition pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMenuChoices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void newGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMenuChoice(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setup(IUserInterface ui) {
		// TODO Auto-generated method stub

	}

}
