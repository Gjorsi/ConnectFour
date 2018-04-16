package inf101.v18.sem2.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class AnimatedImage implements IImage {
	public static final int ANIMATION_BASE_DELAY = 16;
	private List<ImageIcon> icons;
	private int numFrames;
	private int repeat;
	private int delay;
	private int frame;
	private int lastFrame;
	private int tick;

	/**
	 * Create an animated image. NumFrames indicates how many frames are in the
	 * animation, which will be constructed from image files names
	 * "&lt;basename&gt;-0", "&lt;basename&gt;-1", ... up to
	 * &lt;numFrames-1&gt;.
	 *
	 * For example: jump-0.jpg, jump-1.jpg, jump-2.jpg, for basename="jump" and
	 * numFrames=3.
	 *
	 * @param baseName
	 *            Base name of image files
	 * @param numFrames
	 *            Number of frames (must be >= 1)
	 * @param delay
	 *            Delay between each image, in milliseconds (will be rounded to
	 *            nearest non-zero increment of {@value #ANIMATION_BASE_DELAY}
	 *            ms)
	 * @param repeat
	 *            Number of times to repeat the animation, 0 for infinite
	 */
	public AnimatedImage(String baseName, int numFrames, int delay, int repeat) {
		if (numFrames < 1) {
			throw new IllegalArgumentException(
					"Number of frames must be one or greater");
		}
		if (delay < 0)
			throw new IllegalArgumentException("Delay must be 0 or greater");
		if (repeat < 0)
			throw new IllegalArgumentException("Repeat must be 0 or greater");

		this.numFrames = numFrames;
		this.delay = Math.max(1, delay / ANIMATION_BASE_DELAY);
		if (repeat == 0)
			repeat = -1;

		this.repeat = repeat*this.delay*this.numFrames;
		this.frame = 0;
		this.lastFrame = -1;

		this.icons = new ArrayList<ImageIcon>();
		for (int i = 0; i < numFrames; i++) {
			icons.add(ImageLoader.getImage(baseName + "-" + i));
		}
		this.tick = -1;
	}

	private AnimatedImage(List<ImageIcon> icons, int numFrames, int delay,
			int repeat) {
		this.numFrames = numFrames;
		this.delay = delay;
		this.repeat = repeat;
		this.icons = icons;
		this.frame = 0;
		this.lastFrame = -1;
		this.tick = -1;
	}

	@Override
	public void draw(AbstractButton button, int frameNo) {
		if (frameNo != lastFrame) {
			// we do one clock tick when the incoming frame number changes
			tick++;
		}
		lastFrame = frameNo;

		if (frameNo == 0)
			tick = 0;

		if (repeat == -1 || tick < repeat) { // only update if we're still playing
			frame = (tick / delay) % numFrames; // switch frames every delay
												// ticks
			System.out.println("tick=" + tick + ", repeat=" + repeat + "frame to show: " + frame);
		}

		Icon icon = icons.get(frame);
		if (button.getIcon() != icon) {
			button.setHorizontalAlignment(SwingConstants.LEFT);
			button.setVerticalAlignment(SwingConstants.BOTTOM);
			button.setIcon(icon);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + delay;
		result = prime * result + ((icons == null) ? 0 : icons.hashCode());
		result = prime * result + numFrames;
		result = prime * result + repeat;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimatedImage other = (AnimatedImage) obj;
		if (delay != other.delay)
			return false;
		if (icons == null) {
			if (other.icons != null)
				return false;
		} else if (!icons.equals(other.icons))
			return false;
		if (numFrames != other.numFrames)
			return false;
		if (repeat != other.repeat)
			return false;
		return true;
	}

	@Override
	public IImage copy() {
		return new AnimatedImage(icons, numFrames, delay, repeat);
	}

	@Override
	public boolean isAnimation() {
		return true;
	}

}
