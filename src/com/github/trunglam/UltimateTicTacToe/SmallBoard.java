package com.github.trunglam.UltimateTicTacToe;



import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class SmallBoard {
	Image image, overlay;
	int x, y, placement, player;
	boolean selected;
	
	ArrayList<Square> squares = new ArrayList<Square>();
	
	public SmallBoard(int posx, int posy, int boardWidth, int boardHeight, int num) throws SlickException {
		x = posx;
		y = posy;
		selected = false;
		overlay = null;
		image  = (selected) ? new Image("data/greensmallboard.png") : new Image("data/smallboard.png");
		placement = num;
		
		int i = 0;
		for (int xsquare = x, q = 0; q < 3; xsquare += 100, q++) {
			for (int ysquare = y, w = 0; w < 3; ysquare += 75, w++) {
				squares.add(new Square(xsquare, ysquare, i));
				i++;
			}
		}
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setImage(Image i) {
		image = i;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, 300, 225);
	}
	
	public void drawSquares() {
		for (Square square : squares) {
			if (square.isMarked())
				square.drawSquare();
		}
	}
	
	public void setSelect(boolean e) throws SlickException {
		selected = e;
		image  = (selected) ? new Image("data/greensmallboard.png") : new Image("data/smallboard.png");	
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}
	
	public int getPlacement() {
		return placement;
	}
	
	public void drawBoard() {
		image.draw(x, y);
		if (overlay != null)
			overlay.draw(x, y);
	}
	
	public void setOverlay(boolean e, int p) throws SlickException {
		if (e) {
			overlay = (p == 0) ? new Image("data/bigx.png") : new Image("data/bigo.png");
			player = p;
		}
		else
			overlay = null;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public boolean isOverlay() {
		return (overlay == null) ? false : true;
	}
	
	public void reinitialize() throws SlickException {
		selected = false;
		overlay = null;
		image  = (selected) ? new Image("data/greensmallboard.png") : new Image("data/smallboard.png");
		
		for (Square square : squares) {
			square.reinitialize();
		}
	}
	
	public int getPlayer() {
		return player;
	}
	
	public boolean isFilled() {
		for (Square square : squares) {
			if (!square.isMarked())
				return false;
		}
		return true;
	}
}
