package inf101.v18.sem2.gui;

import javax.swing.AbstractButton;

public interface IImage {
	/**
	 * Draw should only be called on a {@link #copy()} of the original image.
	 * 
	 * @param button
	 *            The button to draw into
	 * @param frameNo
	 *            Current frame number, for doing animations
	 */
	void draw(AbstractButton button, int frameNo);

	/**
	 * If this image contains internal state, which cannot be shared across
	 * multiple instances of the same image on the screen, this method will
	 * return a new identical image object. Otherwise it'll just return the same
	 * image.
	 * 
	 * @return A copy of this image
	 */
	IImage copy();
	
	/**
	 * @return True if the image is an animation, in which case {@link #draw(AbstractButton, int)} should be called every 16 ms.
	 */
	boolean isAnimation();
}
