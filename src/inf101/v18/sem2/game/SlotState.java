package inf101.v18.sem2.game;

import java.awt.Color;

import inf101.v18.sem2.gui.IImage;
import inf101.v18.sem2.gui.Image;

public enum SlotState {
	RED, YELLOW, EMPTY;
	
	private IImage image;
	
	public boolean occupied() {
		return this !=EMPTY;
	}
	
	public IImage getImage() {
		return image;
	}
	
	SlotState() {
		this.image = new Image("gui/images/red");
	}
}