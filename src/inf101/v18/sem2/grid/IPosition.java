package inf101.v18.sem2.grid;

/**
 * A coordinate in normal two-dimensional Cartesian space, with (0,0) being in
 * the south-west/lower-left corner.
 *
 * For relative position, 'north' (or up) means increasing y-values, 'south' (or
 * down) means decreasing y-values, 'west' (or left) means decreasing x-values,
 * and 'east' (or right) means increasing x-values.
 *
 *
 * @author anya
 *
 */
public interface IPosition {
	/**
	 * @return Position to the immediate east of this position (might be outside
	 *         whatever area the positions are used in)
	 */
	IPosition east();

	/**
	 * X-coordinates are increasing from the south west corner.
	 *
	 * @return X-coordinate of this position
	 */
	int getX();

	/**
	 * Y-coordinates are increasing from the south west corner.
	 *
	 * @return Y-coordinate of this position
	 */
	int getY();

	/**
	 * @return Position to the immediate north of this position (might be
	 *         outside whatever area the positions are used in)
	 */
	IPosition north();

	/**
	 * @return Position to the immediate north-east of this position (might be
	 *         outside whatever area the positions are used in)
	 */
	IPosition northEast();

	/**
	 * @return Position to the immediate north-west of this position (might be
	 *         outside whatever area the positions are used in)
	 */
	IPosition northWest();

	/**
	 * @return Position to the immediate south of this position (might be
	 *         outside whatever area the positions are used in)
	 */
	IPosition south();

	/**
	 * @return Position to the immediate south-east of this position (might be
	 *         outside whatever area the positions are used in)
	 */
	IPosition southEast();

	/**
	 * @return Position to the immediate south-west of this position (might be
	 *         outside whatever area the positions are used in)
	 */
	IPosition southWest();

	/**
	 * @return Position to the immediate west of this position (might be outside
	 *         whatever area the positions are used in)
	 */
	IPosition west();

}
