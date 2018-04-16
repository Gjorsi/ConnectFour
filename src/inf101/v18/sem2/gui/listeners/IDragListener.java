package inf101.v18.sem2.gui.listeners;

import inf101.v18.sem2.grid.IPosition;

public interface IDragListener {

	/**
	 * Called when the drags-and-drops from one cell to another
	 *
	 * @param from
	 *            The position from which the user dragged
	 * @param to
	 *            The position where the user dropped
	 */
	void dragged(IPosition from, IPosition to);
}
