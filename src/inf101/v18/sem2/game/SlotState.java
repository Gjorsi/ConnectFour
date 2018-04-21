package inf101.v18.sem2.game;

import java.awt.Color;

import inf101.v18.sem2.gui.IImage;
import inf101.v18.sem2.gui.Image;

public enum SlotState {
	RED("redB"), YELLOW("yellowB"), EMPTY("greyB");
	
	private IImage image;
	
	public boolean occupied() {
		return this !=EMPTY;
	}
	
	public IImage getImage() {
		return image;
	}
	
	SlotState(String s) {
		this.image = new Image("gui/images/" + s);
	}
}