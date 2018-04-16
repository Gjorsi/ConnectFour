package inf101.v18.sem2.grid;

/**
 * Interface for a 2-dimensional grid data structure.
 *
 * @param <T>
 *            The element type
 */

public interface IGrid2D<T> extends IArea {
	/**
	 * Set all cells in the grid to the given default value (may be null).
	 *
	 * @param val
	 *            The default value
	 */
	void clear(T val);

	/**
	 * Make a copy
	 *
	 * @return A fresh copy of the grid, with the same elements
	 */
	IGrid2D<T> copy();

	/**
	 * Get the element at position (x, y)
	 *
	 * @param x
	 *            0 => x < getWidth()
	 * @param y
	 *            0 => y < getHeight()
	 * @return Element at x, y
	 */
	T get(int x, int y);

	/**
	 * Get the element at position (x, y)
	 *
	 * @param x
	 *            0 => x < getWidth()
	 * @param y
	 *            0 => y < getHeight()
	 * @param defaultValue
	 *            A default value to be return on access outside the grid
	 * @return Element at x, y, or defaultValue if (x, y) is outside the grid
	 */
	T get(int x, int y, T defaultValue);

	/**
	 * Get the element at the given position
	 *
	 * @param pos
	 *            contains(pos) must be true
	 * @return Element at pos
	 */
	T get(IPosition pos);

	/**
	 * Get the element at the given position
	 *
	 * @param pos
	 *            contains(pos) must be true
	 * @param defaultValue
	 *            A default value to be return on access outside the grid
	 * @return Element at pos, or defaultValue if (x, y) is outside the grid
	 */
	T get(IPosition pos, T defaultValue);

	/**
	 * @return The height of the grid
	 */
	@Override
	int getHeight();

	/**
	 * @return The width of the grid
	 */
	@Override
	int getWidth();

	/**
	 * Set the element at position (x, y)
	 *
	 * @param x
	 *            0 => x < getWidth()
	 * @param y
	 *            0 => y < getHeight()
	 * @param val
	 *            New value
	 * @return Old value
	 */
	T set(int x, int y, T val);

	/**
	 * Set the element at a position
	 *
	 * @param pos
	 *            contains(pos) must be true
	 * @param val
	 *            New value
	 * @return Old value
	 */
	T set(IPosition pos, T val);

}
