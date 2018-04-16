package inf101.v18.sem2.gui.listeners;

public interface ITimeStepListener {

	/**
	 * Called for each time step.
	 *
	 * @param count
	 *            The total number of steps taken so far (might be useful in
	 *            some circumstances)
	 */
	void timeStep(int count);
}
