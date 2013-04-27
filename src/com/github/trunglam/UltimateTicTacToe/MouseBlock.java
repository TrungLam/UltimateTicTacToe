package com.github.trunglam.UltimateTicTacToe;

import org.newdawn.slick.geom.Rectangle;

public class MouseBlock {
	private int x,y;
	public MouseBlock(int px, int py) {
		x = px;
		y = py;
	}
	
	public MouseBlock () {
		x = 0;
		y = 0;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x,y,5,5);
	}
	
	public void setX(int px) {
		x = px;
	}
	
	public void setY(int py, int screenHeight) {
		y = screenHeight - py;
	}
	
	public void setXY(int px, int py, int screenHeight) {
		x = px;
		y = screenHeight - py;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}