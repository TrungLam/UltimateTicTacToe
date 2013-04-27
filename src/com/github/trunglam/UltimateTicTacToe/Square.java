package com.github.trunglam.UltimateTicTacToe;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Square {

	int x, y, placement;
	Image marker;
	boolean marked;
	
	public Square(int posx, int posy, int num) throws SlickException {
		x = posx;
		y = posy;
		marker = new Image("data/x.png");
		marked = false;
		placement = num;
	}
	
	public Image getImage() {
		return marker;
	}
	
	public void drawSquare() {
		marker.draw(x, y);
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public void setMark(boolean e) {
		marked = e;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, 100, 75);
	}
	
	public int getPlacement() {
		return placement;
	}
}
