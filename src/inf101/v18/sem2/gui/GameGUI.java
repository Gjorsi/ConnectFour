package inf101.v18.sem2.gui;

import inf101.v18.sem2.gui.listeners.IClickListener;
import inf101.v18.sem2.gui.listeners.IDragListener;
import inf101.v18.sem2.gui.listeners.ITimeStepListener;
import inf101.v18.sem2.game.IGame;
import inf101.v18.sem2.grid.IArea;
import inf101.v18.sem2.grid.IGrid2D;
import inf101.v18.sem2.grid.IPosition;
import inf101.v18.sem2.grid.MyGrid2D;
import inf101.v18.sem2.grid.Position;
import inf101.v18.sem2.grid.Rectangle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractButton;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author Anna Maria Eilertsen
 * @author Alexandre Vivmond
 * @author Anya Helene Bagge
 *
 */
public class GameGUI extends JPanel implements IUserInterface, ActionListener,
		MouseListener, MouseMotionListener {
	private static final long serialVersionUID = -2030937455049555857L;

	/**
	 * Her ligger cellene
	 */
	private final ImagePanel mainPanel;

	/**
	 * Viser tid nederst
	 */
	private final JPanel statusPanel;

	/**
	 * A label to display status information
	 */
	private final JLabel statusLabel;
	/**
	 * 'Nytt games' knapp
	 */
	private final JPanel controlPanel;
	/**
	 * Knapper for å starte btnNew games
	 */
	private final JButton btnNew, btnPlayPause, btnStep, btnSpeed;
	/**
	 * Used to find the position corresponding to a button
	 */
	private final Map<JButton, IPosition> buttonMap = new HashMap<JButton, IPosition>();

	/**
	 * Used to find the button corresponding to a position
	 */
	private final Map<IPosition, JButton> positionMap = new HashMap<IPosition, JButton>();

	private IGrid2D<IImage> animations;
	/**
	 * Referanse til spillet
	 */
	private final List<IGame> games;

	private IGame selectedGame;

	private javax.swing.Timer animationTimer;

	private int animationFrame;

	private JComboBox<String> sizes;
	private final JComboBox<String> gameSelection;
	private JComboBox<String> gameMenu;
	private boolean paused = true;
	private boolean started = true;
	private static final String[] boardSizes = new String[] { "10x10", "12x12",
			"15x15", "20x15", "30x20" };
	private int currentSpeed = 0;
	public static final int DELAY_NORMAL = 400, DELAY_FAST = 150,
			DELAY_FASTER = 50;
	private final int[] speeds = new int[] { 3, 2, 1 };
	private final List<IClickListener> clickListeners = new ArrayList<>();
	private final List<ITimeStepListener> timeStepListeners = new ArrayList<>();
	private final List<IDragListener> dragListeners = new ArrayList<>();

	private int stepCount;

	private boolean hasPlayPauseButton, hasStepButton, hasSpeedButton;
	private IPosition mousePosition;
	private IPosition draggedFrom;

	private static IArea boardSize(String size) {
		String[] split = size.split("x");
		if (split.length != 2) {
			throw new IllegalArgumentException("Should be on WxH: " + size);
		}
		return new Rectangle(Integer.parseInt(split[0]),
				Integer.parseInt(split[1]));
	}

	public GameGUI(IGame spill) {
		this(Arrays.asList(spill));
	}

	/**
	 * Oppretter en ny games-GameGUI
	 *
	 * @param games
	 *            Spillet som skal kontrolleres
	 */
	public GameGUI(List<IGame> spill) {
		super();
		setLayout(new BorderLayout());

		this.games = new ArrayList<IGame>(spill);
		Collections.sort(games, new GameComparator());
		this.selectedGame = spill.get(0);

		String[] gameNames = new String[games.size()];
		int i = 0;
		for (IGame g : games) {
			gameNames[i++] = g.getName();
		}
		gameSelection = new JComboBox<String>(gameNames);
		gameSelection.setSelectedItem(selectedGame.getName());
		gameSelection.addActionListener(this);
		gameSelection.setToolTipText("Select game");

		JPanel dummyControlPanel = new JPanel();
		dummyControlPanel.setLayout(new BorderLayout());
		dummyControlPanel.setForeground(Style.FOREGROUND);
		dummyControlPanel.setBackground(Style.BACKGROUND);
		controlPanel = new JPanel();
		controlPanel.setForeground(Style.FOREGROUND);
		controlPanel.setBackground(Style.BACKGROUND);
		dummyControlPanel.add(controlPanel, BorderLayout.WEST);

		btnPlayPause = new JButton();
		btnPlayPause.setToolTipText("Play");
		btnPlayPause.addActionListener(this);
		btnSpeed = new JButton();
		btnSpeed.addActionListener(this);
		btnStep = new JButton(ImageLoader.getImage("gui/images/step"));
		btnStep.setToolTipText("Step");
		btnStep.addActionListener(this);
		btnNew = new JButton(ImageLoader.getImage("gui/images/new"));
		btnNew.setToolTipText("Start new game");
		btnNew.addActionListener(this);

		updateSpeed();
		animationTimer = new javax.swing.Timer(
				AnimatedImage.ANIMATION_BASE_DELAY, null);
		animationTimer.stop();
		animationTimer.addActionListener(this);

		mainPanel = new ImagePanel();
		mainPanel.setForeground(Style.BACKGROUND);
		mainPanel.setBackground(Style.FOREGROUND);
		statusPanel = new JPanel();
		statusPanel.setForeground(Style.FOREGROUND);
		statusPanel.setBackground(Style.BACKGROUND);

		statusLabel = new JLabel();
		statusPanel.add(statusLabel);
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// ekstra panel for å få riktig bakgrunn på resten
		JPanel dummyPanel = new JPanel();
		dummyPanel.setForeground(Style.FOREGROUND);
		dummyPanel.setBackground(Style.BACKGROUND);
		
//		JButton testButton = new JButton();
//		testButton.setIcon(ImageLoader.getImage("gui/images/red"));
//		testButton.setPreferredSize(new Dimension(70,70));
//		dummyPanel.add(testButton);

		add(dummyControlPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.WEST);
		add(statusPanel, BorderLayout.SOUTH);
		add(dummyPanel, BorderLayout.CENTER);
	}

	/**
	 * Denne blir kalt av Java hver gang brukeren trykker på en knapp, eller
	 * hver gang timer-signalet avfyres.
	 *
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNew) {
			newGame();
			updateAll();
		} else if (e.getSource() == btnPlayPause) {
			if (paused) {
				setPlaying();
				// timeStep();
				// updateAll();
			} else {
				setPaused();
			}
		} else if (e.getSource() == btnStep) {
			setPaused();
			timeStep();
			updateAll();
		} else if (e.getSource() == btnSpeed) {
			currentSpeed = (currentSpeed + 1) % speeds.length;

			updateSpeed();

		} else if (e.getSource() == sizes) {
			String size = (String) sizes.getSelectedItem();
			IArea p = boardSize(size);
			selectedGame.setSize(p.getWidth(), p.getHeight());
			if (!selectedGame.canChangeSize()) {
				newGame();
			}
			initializeBoard();
			updateAll();
		} else if (e.getSource() == gameMenu) {
			selectedGame.setMenuChoice((String) gameMenu.getSelectedItem());
			initializeBoard();
			updateAll();
		} else if (e.getSource() == gameSelection) {
			String selection = (String) gameSelection.getSelectedItem();
			if (!selection.equals(selectedGame.getName())) {
				for (IGame game : games) {
					if (selection.equals(game.getName())) {
						selectedGame = game;
						initialize();
					}
				}
			}
		} else if (e.getSource() == animationTimer) {
			// animationTimer.restart();

			// System.out.println("Tick");
			try {
				if (started && !paused) {
//					System.out.println("Frame: " + animationFrame);
					if (animationFrame % speeds[currentSpeed] == 0) {
						timeStep();
						updateAll();
					}

					if (animations != null) {
						drawAnimated();
					}
					animationFrame++;

				}
			} catch (RuntimeException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Add a listener which will be called whenever the user clicks a mouse
	 * button.
	 *
	 * @param listener
	 *            The listener
	 */
	@Override
	public synchronized void addClickListener(IClickListener listener) {
		clickListeners.add(listener);
	}

	@Override
	public synchronized void addDragListener(IDragListener l) {
		dragListeners.add(l);
	}

	/**
	 * Add a listener which will be called for each time step.
	 *
	 * Time steps happen at regular intervals (typically in the ranger of 50–500
	 * ms, depending on what the game's speed is set to.
	 *
	 * @param listener
	 * @see IGame#hasSpeedButton()
	 */
	@Override
	public synchronized void addTimeStepListener(ITimeStepListener listener) {
		timeStepListeners.add(listener);
	}

	@Override
	public synchronized void endGame() {
		started = false;
		animationTimer.stop();
	}

	public synchronized void initialize() {
		setupGame();
		initializeControl();
		initializeBoard();
		setVisible(true);

		newGame();

		if (hasPlayPauseButton) {
			setPaused();
		} else {
			setPlaying();
		}
	}

	private void initializeBoard() {
		int width = selectedGame.getWidth();
		int height = selectedGame.getHeight();

		mainPanel.removeAll();
		mainPanel.setLayout(new GridLayout(height + 1, width + 1));
		buttonMap.clear();
		positionMap.clear();

		animations = new MyGrid2D<>(width, height, null);

		for (int y = 0; y < height; y++) {
			mainPanel.add(new CoordLabel(height - y - 1));
			for (int x = 0; x < width; x++) {
				JPanel panel = new JPanel(new BorderLayout());
				JButton button = new JButton();
				button.addMouseListener(this);
				button.addMouseMotionListener(this);
				button.setMargin(new Insets(0, 0, 0, 0));
				// button.setBorder(new LineBorder(Color.DARK_GRAY, 1));
				button.setContentAreaFilled(false);
				int borderWidth = 3;
				button.setBounds(0, 0, selectedGame.getCellWidth()
						+ (2 * borderWidth), selectedGame.getCellHeight()
						+ (2 * borderWidth));
				button.setPreferredSize(new Dimension(selectedGame
						.getCellWidth() + (2 * borderWidth), selectedGame
						.getCellHeight() + (2 * borderWidth)));
				button.setForeground(Color.WHITE);
				button.setBackground(Color.BLACK);
				button.setFocusPainted(false);
				panel.add(button);
				panel.setOpaque(false);
				mainPanel.add(panel);
				Position pos = new Position(x, height - y - 1);
				buttonMap.put(button, pos);
				positionMap.put(pos, button);
			}
		}

		mainPanel.add(new CoordLabel(""));
		for (int x = 0; x < width; x++) {
			mainPanel.add(new CoordLabel(x));
		}
		updateFrame();
	}

	private void initializeControl() {
		controlPanel.removeAll();

		controlPanel.add(gameSelection);

		List<String> bSizes = selectedGame.getBoardSizes();
		if (bSizes == null) {
			bSizes = Arrays.asList(boardSizes);
		}
		// sørg for at vi har vår egen lokale kopi som vi kan endre på
		bSizes = new ArrayList<String>(bSizes);
		String size = selectedGame.getWidth() + "x" + selectedGame.getHeight();
		if (!bSizes.contains(size)) {
			bSizes.add(size);
		}
		Collections.sort(bSizes);
		if (sizes != null) {
			sizes.removeActionListener(this);
		}
		sizes = new JComboBox<String>(bSizes.toArray(new String[bSizes.size()]));
		sizes.setSelectedItem(size);
		sizes.addActionListener(this);
		sizes.setToolTipText("Select board size");

		controlPanel.add(sizes);

		controlPanel.add(btnNew);

		if (hasPlayPauseButton) {
			controlPanel.add(btnPlayPause);
		}

		if (hasStepButton) {
			controlPanel.add(btnStep);
		}

		if (hasSpeedButton) {
			controlPanel.add(btnSpeed);
		}

		if (gameMenu != null) {
			gameMenu.removeActionListener(this);
			gameMenu = null;
		}
		List<String> choices = selectedGame.getMenuChoices();
		if (choices != null) {
			gameMenu = new JComboBox<String>(choices.toArray(new String[choices
					.size()]));
			gameMenu.addActionListener(this);
			gameMenu.setToolTipText("Game options");
			controlPanel.add(gameMenu);
		}

	}

	@Override
	public synchronized void mouseClicked(MouseEvent e) {
		if (!started) {
			return;
		}

		if (e.getSource() instanceof JButton) {
			IPosition pos = buttonMap.get(e.getSource());

			if (pos != null) {
				for (IClickListener l : clickListeners) {
					for (int i = 0; i < e.getClickCount(); i++) {
						if (SwingUtilities.isLeftMouseButton(e)) {
							l.clicked(pos);
						} else if (SwingUtilities.isRightMouseButton(e)) {
							l.rightClicked(pos);
						}
					}
				}
				updateAll();
			}
		}
	}

	@Override
	public synchronized void mouseDragged(MouseEvent e) {
		if (!started) {
			return;
		}

		if (!dragListeners.isEmpty()) {
			setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));

			draggedFrom = buttonMap.get(e.getSource());
		}

	}

	@Override
	public synchronized void mouseEntered(MouseEvent e) {
		if (!started) {
			return;
		}

		mousePosition = buttonMap.get(e.getSource());
		if (draggedFrom != null) {
			selectedGame.getImageAt(draggedFrom).draw(
					(AbstractButton) e.getSource(), animationFrame);
		}

	}

	@Override
	public synchronized void mouseExited(MouseEvent e) {
		if (!started) {
			return;
		}

		if (draggedFrom != null) {
			update(mousePosition);
		}

		mousePosition = null;
	}

	@Override
	public synchronized void mouseMoved(MouseEvent e) {
		// do nothing

	}

	@Override
	public synchronized void mousePressed(MouseEvent e) {
	}

	@Override
	public synchronized void mouseReleased(MouseEvent e) {
		if (!started) {
			return;
		}

		if (draggedFrom != null) {
			for (IDragListener l : dragListeners) {
				l.dragged(draggedFrom, mousePosition);
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			update(mousePosition);
			draggedFrom = null;
		}
	}

	@Override
	public synchronized void newGame() {
		stepCount = 0;
		started = true;
		selectedGame.newGame();
		animationFrame = 0;
		animationTimer.start();
		updateAll();
	}

	private void setPaused() {
		btnPlayPause.setToolTipText("Play");
		btnPlayPause.setIcon(ImageLoader.getImage("gui/images/play"));
		paused = true;
	}

	private void setPlaying() {
		btnPlayPause.setToolTipText("Pause");
		btnPlayPause.setIcon(ImageLoader.getImage("gui/images/pause"));
		paused = false;
		animationFrame = 0;

	}

	@Override
	public synchronized void setPlayPauseButton(boolean enabled) {
		hasPlayPauseButton = enabled;
	}

	@Override
	public synchronized void setSpeedButton(boolean enabled) {
		hasSpeedButton = enabled;
	}

	@Override
	public synchronized void setSpeeds(int normalDelay, int fastDelay,
			int fasterDelay) {
		if (normalDelay <= 0 || fastDelay <= 0 || fasterDelay <= 0) {
			throw new IllegalArgumentException();
		}

		speeds[0] = Math.max(1, normalDelay
				/ AnimatedImage.ANIMATION_BASE_DELAY);
		speeds[1] = Math.max(1, fastDelay / AnimatedImage.ANIMATION_BASE_DELAY);
		speeds[2] = Math.max(1, fasterDelay
				/ AnimatedImage.ANIMATION_BASE_DELAY);

		updateSpeed();
	}

	@Override
	public synchronized void setStatus(String status) {
		statusLabel.setText(status);
	}

	@Override
	public synchronized void setStepButton(boolean enabled) {
		hasStepButton = enabled;
	}

	public synchronized void setupGame() {
		clickListeners.clear();
		timeStepListeners.clear();
		dragListeners.clear();
		hasSpeedButton = true;
		hasPlayPauseButton = true;
		hasStepButton = true;
		setStatus(selectedGame.getName());

		setSpeeds(DELAY_NORMAL, DELAY_FAST, DELAY_FASTER);
		selectedGame.setup(this);
		updateSpeed();
	}

	public synchronized void timeStep() {
		if (started) {
//			System.out.println("Step!");

			for (ITimeStepListener l : timeStepListeners) {
				l.timeStep(stepCount);
			}

			stepCount++;
		}
	}

	@Override
	public synchronized void update(IPosition pos) {
		JButton button = positionMap.get(pos);
		IImage img = selectedGame.getImageAt(pos);
		
		img.draw(button, animationFrame);

		animations.set(pos, img.isAnimation() ? img : null);
	}

	private void drawAnimated() {
		for (IPosition pos : animations) {
			IImage img = animations.get(pos);
			if (img != null) {
				img.draw(positionMap.get(pos), animationFrame);
			}
		}
	}

	@Override
	public synchronized void updateAll() {
		for (IPosition pos : positionMap.keySet()) {
			update(pos);
		}
	}

	private void updateFrame() {
		Container parent = getParent();
		while (parent != null) {
			if (parent instanceof JFrame) {
				JFrame frame = (JFrame) parent;
				frame.setTitle(selectedGame.getName());
				frame.pack();
				return;
			} else if (parent instanceof JApplet) {
				JApplet applet = (JApplet) parent;
				applet.setName(selectedGame.getName());
				applet.resize(getPreferredSize());
				applet.validate();
				return;
			}
			parent = parent.getParent();
		}

	}

	private void updateSpeed() {
		if (currentSpeed == 0) {
			btnSpeed.setIcon(ImageLoader.getImage("gui/images/normal"));
			btnSpeed.setToolTipText("Current speed: Normal");
		} else if (currentSpeed == 1) {
			btnSpeed.setIcon(ImageLoader.getImage("gui/images/fast"));
			btnSpeed.setToolTipText("Current speed: Fast");

		} else if (currentSpeed == 2) {
			btnSpeed.setIcon(ImageLoader.getImage("gui/images/faster"));
			btnSpeed.setToolTipText("Current speed: Faster");

		}
	}

	private static final class GameComparator implements Comparator<IGame>,
			Serializable {
		private static final long serialVersionUID = 6647481037039732094L;

		@Override
		public int compare(IGame arg0, IGame arg1) {
			return arg0.getName().compareTo(arg1.getName());
		}
	}
}
