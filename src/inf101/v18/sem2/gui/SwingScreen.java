package inf101.v18.sem2.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.*;

import javafx.scene.shape.Rectangle;

public class SwingScreen extends Canvas implements IScreen {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4285886391134067072L;
	private final int WIDTH = 1500;
	private final int HEIGHT = 1000;
	
	private JFrame mainFrame;
	private JPanel buttonPanel;
	private JPanel board;
	
	public SwingScreen() {
		mainFrame = new JFrame();
		mainFrame.setSize(WIDTH, HEIGHT);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 0, WIDTH, (HEIGHT/5));
		
		board = new JPanel();
		board.setBounds(0, (HEIGHT/5), WIDTH, (HEIGHT/5)*4);
		board.setBackground(Color.GRAY.brighter());
		
		mainFrame.add(buttonPanel);
		mainFrame.add(board);
		
		mainFrame.setVisible(true);
	}

	@Override
	public void clear() {
		
	}

	@Override
	public void placeDisc(int x, int y) {
		
	}

}
