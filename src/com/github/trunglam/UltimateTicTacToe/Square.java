package com.github.trunglam.UltimateTicTacToe;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Square {

	int x, y, placement, player;
	Image marker;
	boolean marked;
	
	public Square(int posx, int posy, int num) throws SlickException {
		x = posx;
		y = posy;
		marker = new Image("data/x.png");
		marked = false;
		placement = num;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public void drawSquare() {
		marker.draw(x, y);
	}

	public void setMark(boolean e, int p) throws SlickException {
		marked = e;
		player = p;
		marker = (p == 0) ? new Image("data/x.png") : new Image("data/o.png");
		
	}
	
	public Image getImage() {
		return marker;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, 100, 75);
	}
	
	public int getPlacement() {
		return placement;
	}
	
	public void reinitialize() {
		marked = false;
	}
	
	public int getPlayer() {
		return player;
	}
}
