package inf101.v18.sem2.gui.listeners;

import inf101.v18.sem2.grid.IPosition;

public interface IClickListener {

	/**
	 * Called when the user clicks (mouse) / taps (touch screen) a grid cell.
	 *
	 * @param pos
	 *            The position of the cell the user clicked.
	 */
	void clicked(IPosition pos);

	/**
	 * Called when the user right clicks (mouse) / taps and holds (touch screen)
	 * a grid cell.
	 *
	 * @param pos
	 *            The position of the cell the user clicked.
	 */
	void rightClicked(IPosition pos);

}
