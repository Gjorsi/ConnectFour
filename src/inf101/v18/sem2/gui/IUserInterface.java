package inf101.v18.sem2.gui;

import inf101.v18.sem2.gui.listeners.IClickListener;
import inf101.v18.sem2.gui.listeners.IDragListener;
import inf101.v18.sem2.gui.listeners.ITimeStepListener;
import inf101.v18.sem2.game.IGame;
import inf101.v18.sem2.grid.IPosition;

public interface IUserInterface {
	/**
	 * Add a listener which will be called whenever the user clicks a mouse
	 * button.
	 *
	 * @param l
	 *            The listener
	 */
	void addClickListener(IClickListener l);

	/**
	 * Add a listener which will be called whenever the user drags the mouse
	 * from one position to another (i.e., presses the mouse button at one
	 * position, moves while holding the button and releases it at another
	 * position (or the corresponding touch event)).
	 *
	 * @param l
	 *            The listener
	 */
	void addDragListener(IDragListener l);

	/**
	 * Add a listener which will be called for each time step.
	 *
	 * Time steps happen at regular intervals (typically in the ranger of 50–500
	 * ms, depending on what the game's speed is set to.
	 *
	 * @param l
	 * @see IGame#hasSpeedButton()
	 */
	void addTimeStepListener(ITimeStepListener l);

	/**
	 * Tell the UI that the game has ended.
	 *
	 * Any listeners / other communication from the UI will be stopped until the
	 * user clicks the New Game button.
	 *
	 */
	void endGame();

	/**
	 * Tell the UI that a new game should be started.
	 *
	 * The game's {@link IGame#newGame()} method will be called, and
	 * notifications from listeners etc. will resume.
	 *
	 */
	void newGame();

	/**
	 * Enable or disable the play/pause button.
	 *
	 * @param enabled
	 *            Whether the button should be there or not
	 */
	void setPlayPauseButton(boolean enabled);

	/**
	 * Enable or disable the speed button.
	 *
	 * @param enabled
	 *            Whether the button should be there or not
	 */
	void setSpeedButton(boolean enabled);

	/**
	 * Set the game's speed choices, in milliseconds of delay between steps.
	 *
	 * If {@link #setSpeedButton(boolean)} is set to true, the game will have a
	 * speed button to toggle between normal, fast and faster speed. Otherwise,
	 * it will always be run at normal speed.
	 *
	 * Any listeners set with {@link #addTimeStepListener(ITimeStepListener)}
	 * will be called whenever a time step happens.
	 *
	 * @param normalDelay
	 *            The delay between time steps for normal speed (default is 400
	 *            ms)
	 * @param fastDelay
	 *            The delay between time steps for fast speed (default is 150
	 *            ms)
	 * @param fasterDelay
	 *            The delay between time steps for faster speed (default is 50
	 *            ms)
	 */
	void setSpeeds(int normalDelay, int fastDelay, int fasterDelay);

	/**
	 * Display a status message on the screen.
	 *
	 * @param status
	 *            A message
	 */
	void setStatus(String status);

	/**
	 * Enable or disable the step button.
	 *
	 * @param enabled
	 *            Whether the button should be there or not
	 */
	void setStepButton(boolean enabled);

	/**
	 * Inform the user interface that the cell at position has changed.
	 *
	 *
	 * @param pos
	 *            A position
	 */
	void update(IPosition pos);

	/**
	 * Inform the user interface that some or all cells have been updated, and
	 * should be queried again.
	 */
	void updateAll();

}
