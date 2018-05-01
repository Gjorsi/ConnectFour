package inf101.v18.sem2.game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import inf101.v18.sem2.gui.listeners.IClickListener;
import inf101.v18.sem2.gui.listeners.ITimeStepListener;
import inf101.v18.sem2.grid.IPosition;
import inf101.v18.sem2.grid.MyGrid2D;
import inf101.v18.sem2.grid.Position;
import inf101.v18.sem2.grid.Rectangle;
import inf101.v18.sem2.gui.IImage;
import inf101.v18.sem2.gui.IUserInterface;

public class ConnectFour implements IGame, IClickListener, ITimeStepListener {

	private int width = 7;
	private int height = 6;
	private MyGrid2D<SlotState> board;
	private IUserInterface ui;
	
	// in pvp or aI mode
	private boolean pvp;
	
	// used in AI mode to control turns
	private boolean playersTurn;
	
	private IPlayer currentPlayer;
	private IPlayer player1;
	private IPlayer player2;
	private IRobot robot;
	
	// used to stop the game and wait for restart when someone has won.
	private boolean playing;
	
	private ConnectFourRules rules = new ConnectFourRules();
	private Random random = new Random();

	public ConnectFour() {
		
	}

	@Override
	public boolean canChangeSize() {
		return false;
	}

	@Override
	public List<String> getBoardSizes() {
		return null;
	}

	@Override
	public int getCellHeight() {
		return 70;
	}

	@Override
	public int getCellWidth() {
		return 70;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public IImage getImageAt(IPosition pos) {
		return board.get(pos).getImage();
	}

	@Override
	public List<String> getMenuChoices() {
		return Arrays.asList("AI", "PvP");
	}

	@Override
	public String getName() {
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
		
		if (!pvp) {
			playersTurn = random.nextBoolean();
			if (playersTurn) {
				player1 = new Player(SlotState.YELLOW, "Player 1");
				robot = new Robot(SlotState.RED, "Robot");
				ui.setStatus("Player starts");
			} else {
				player1 = new Player(SlotState.RED, "Player 1");
				robot = new Robot(SlotState.YELLOW, "Robot");
				ui.setStatus("AI starts");
			}
		} else {
			player1 = new Player(SlotState.YELLOW, "Player 1");
			player2 = new Player(SlotState.RED, "Player 2");
			ui.setStatus("Player 1 starts");
		}
		
		currentPlayer = player1;
		playing = true;
		
	}

	@Override
	public void setMenuChoice(String s) {
		switch (s) {
		case "AI":
			pvp = false;
			break;
		case "PvP":
			pvp = true;
			playersTurn = false;
			break;
		}
		ui.newGame();
	}

	@Override
	public void setSize(int width, int height) {
		
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
		
		if (!pvp && !playersTurn && playing) {
			// in AI mode, not players turn: call robot to decide its move
			IPosition resultSlot = robot.doTurn(null, this);
			placeDisc(resultSlot, robot);
			
			if (rules.hasWon(board, resultSlot.getX(), resultSlot.getY())) {
				ui.setStatus(robot.getName() + " won!");
				playing = false;
			}
			
			playersTurn = true;
		}
	}

	@Override
	public void clicked(IPosition pos) {
		
		if ((pvp || playersTurn) && playing) {
			IPosition resultSlot = currentPlayer.doTurn(pos, this);
			if (resultSlot != null) {
				placeDisc(resultSlot, currentPlayer);
				
				if (rules.hasWon(board, resultSlot.getX(), resultSlot.getY())) {
					ui.setStatus(currentPlayer.getName() + " won!");
					playing = false;
				}
				
				if (pvp && playing) {
					currentPlayer = (currentPlayer == player1) ? player2 : player1;
					ui.setStatus(currentPlayer.getName() + "'s turn");
				}
				
				if (!pvp)
					playersTurn = false;
			}
		}
		
	}
	
	@Override
	public int validMove(IPosition pos) {
		return rules.validMove(board, pos);
	}
	
	@Override
	public void placeDisc(IPosition pos, IPlayer player) {
		board.set(pos, player.getColour());
	}

	@Override
	public void rightClicked(IPosition pos) {
		
	}

}
