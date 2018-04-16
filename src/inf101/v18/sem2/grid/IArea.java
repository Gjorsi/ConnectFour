package inf101.v18.sem2.grid;

import java.util.Collection;

/**
 *
 * Representation of a two-dimensional area.
 *
 * @author anya
 *
 */
public interface IArea extends Iterable<IPosition> {
	/**
	 * @param x
	 *            X-coordinate
	 * @param y
	 *            Y-coordinate
	 * @return True if the (x,y) position lies within the area
	 */
	boolean contains(int x, int y);

	/**
	 * @param pos
	 *            A position
	 * @return True if the position lies within the area
	 */
	boolean contains(IPosition pos);

	@Override
	boolean equals(Object other);

	/**
	 * @return Height of the area
	 */
	int getHeight();

	/**
	 * Translate a 2D coordinate to a 1D array index.
	 *
	 * @param x
	 *            X-coordinate, 0 <= x < getWidth()
	 * @param y
	 *            Y-coordinate, 0 <= y < getHeight()
	 * @return an index, 0 <= i < getSize()
	 * @throws IndexOutOfBoundsException
	 *             if !contains(x, y)
	 */
	int getIndex(int x, int y);

	/**
	 * Translate a 2D coordinate to a 1D array index.
	 *
	 * @param pos
	 *            A legal position
	 * @return an index, 0 <= i < getSize()
	 * @throws IndexOutOfBoundsException
	 *             if !contains(pos)
	 */
	int getIndex(IPosition pos);

	/**
	 * Returns the number of legal positions in the area
	 *
	 * @return Same as getWidth()*getHeight()
	 */
	int getSize();

	/**
	 * @return Width of the area
	 */
	int getWidth();

	@Override
	int hashCode();

	/**
	 * Check if a position is at the edge of the area, i.e.
	 *
	 * pos.getX() == 0 or pos.getX() == getWidth()-1 (unless
	 * wrapsHortizontally()), and/or pos.getY() == 0 or pos.getY() ==
	 * getHeight()-1 (unless wrapsVertically())
	 *
	 * @param pos
	 *            A position
	 * @return True if this position lies on the edge of the rectangle
	 */
	boolean isOnEdge(IPosition pos);

	/**
	 * Return an object for iterating over all the neighbours of the given
	 * position, suitable for use in a new-style for-loop.
	 *
	 * The iterator will yield up to eight positions (less if the given position
	 * is at the edge of the area, and the coordinates are not wrapped). E.g.,
	 * for a 1x1 area, the iterator will yield nothing (if the area is not
	 * wrapped), or the same position two or eight times (if the area is wrapped
	 * horizontally, vertically or both).
	 *
	 * @param pos
	 *            A position in the area
	 * @return An iterable over positions, with {@link #contains(IPosition)}
	 *         being true for each position.
	 * @see #wrapsHorizontally(), {@link #wrapsVertically()}
	 * @throws IndexOutOfBoundsException
	 *             if !contains(pos)
	 */
	Collection<IPosition> neighboursOf(IPosition pos);

	@Override
	String toString();

	/**
	 * If the area wraps horizontally, then x will be the same as
	 * x+(k*getWidth()) for any k – i.e., it will be as if the 2D area is
	 * projected on a cylinder or torus in 3D-space.
	 *
	 * With no wrapping, accessing positions outside
	 * (0,0)–(getWidth(),getHeight()) is illegal.
	 *
	 * @return True if the area wraps around horizontally
	 */
	boolean wrapsHorizontally();

	/**
	 * If the area wraps vertically, then y will be the same as
	 * y+(k*getHeight()) for any k – i.e., it will be as if the 2D area is
	 * projected on a cylinder or torus in 3D-space.
	 *
	 * With no wrapping, accessing positions outside
	 * (0,0)–(getWidth(),getHeight()) is illegal.
	 *
	 * @return True if the area wraps around vertically
	 */
	boolean wrapsVertically();
}
