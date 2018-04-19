package inf101.v18.sem2.gui;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Image implements IImage {

	private ImageIcon icon;

	public Image(String filename) {
		icon = ImageLoader.getImage(filename);
	}

	@Override
	public void draw(AbstractButton button, int frameNo) {
		button.setIcon(icon);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setVerticalAlignment(SwingConstants.BOTTOM);
//		button.setBackground(Color.BLACK);

	}

	@Override
	public IImage copy() {
		return this;
	}

	@Override
	public boolean isAnimation() {
		return false;
	}

}
