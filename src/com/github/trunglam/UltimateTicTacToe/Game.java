package com.github.trunglam.UltimateTicTacToe;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame{
	
	private static final int screenHeight = 675;
	private static final int screenWidth = 900;
	
	Image bigboard;
	SmallBoard smallboards[] = new SmallBoard[9];
	
	MouseBlock mb;
	public Game() {
		super("Ultimate TicTacToe");
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		
		for (SmallBoard board : smallboards) {
			board.drawBoard();
			board.drawSquares();
		}
		
		bigboard.draw();
		
		arg1.drawString("x: " + mb.getX(), 0, 0);
		arg1.drawString("y: " + mb.getY(), 0, 10);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		bigboard = new Image("data/bigboard.png");
		
		int i = 0;
		for (int x = 0; x < screenWidth; x += screenWidth/3) {
			for (int y = 0; y < screenHeight; y += screenHeight/3) {
				smallboards[i] = new SmallBoard(x, y, screenWidth/3, screenHeight/3, i);
				i++;
			}
		}
		mb = new MouseBlock();
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		Input input = arg0.getInput();
		
		mb.setXY(Mouse.getX(), Mouse.getY(), screenHeight);
		if (input.isMousePressed(0) && input.isKeyDown(Input.KEY_A)) {
			
			SmallBoard currBoard = null;
			
			int multipleCollisions = 0;
			for (SmallBoard board : smallboards) {
				if (board.getRect().intersects(mb.getRect())) {
					multipleCollisions++;
					currBoard = board;
				}
				
				if (multipleCollisions > 1)
					break;
			}
			
			if (multipleCollisions == 1) {
				if (!currBoard.isOverlay()) {
					for (SmallBoard board : smallboards) {
						board.setSelect(false);
					}
					boolean value = currBoard.isSelected() ? false : true;
					currBoard.setSelect(value);
				}
			}
		}
		
		else if (input.isMousePressed(1)) {
			SmallBoard newBoard = null;
			
			for (SmallBoard board : smallboards) {
				if (board.getRect().intersects(mb.getRect()) && board.isSelected()) {
					Square currSquare = null;
					int multipleCollisions = 0;
					for (Square square : board.getSquares()) {
						if (square.getRect().intersects(mb.getRect())) {
							multipleCollisions++;
							currSquare = square;
						}
						
						if (multipleCollisions > 1)
							break;
					}
					
					if (multipleCollisions == 1) {
						boolean value = currSquare.isMarked() ? false : true;
						currSquare.setMark(value);
						newBoard = smallboards[currSquare.getPlacement()];
						
						if (checkSquares(board.getSquares())) {
							board.setOverlay(true);
							if (checkBoards(smallboards)) {
								System.out.println("lol");
							}
								
						}
						break;
					}
				}
			}
			
			if (newBoard != null) {
				for (SmallBoard board : smallboards) {
					board.setSelect(false);
				}
				if (!newBoard.isOverlay())
					newBoard.setSelect(true);
			}
		}
		
		
	}
	boolean checkSquares(ArrayList<Square> arraylist) {
		int counter = 0, position = 0;
		Square squares[][] = new Square[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				squares[x][y] = arraylist.get(position);
				position++;
			}
		}
		
		//check vertically
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (squares[x][y].isMarked()) {
					counter++;
				}
			}
			if (counter == 3)
				return true;
			counter = 0;
		}
		//checks horizontally
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (squares[y][x].isMarked()) {
					counter++;
				}
			}
			if (counter == 3)
				return true;
			counter = 0;
		}
		//checks diagonally
		for (int x = 0; x < 3; x++) {
			if (squares[x][x].isMarked()) {
				counter++;
			}
		}
		if (counter == 3)
			return true;
		counter = 0;
		
		//checks the other diagonal
		for (int x = 0, y = 2; x < 3; x++, y--) {
			if (squares[x][y].isMarked()) {
				counter++;
			}
		}
		if (counter == 3)
			return true;
		return false;
	}
	
	public boolean checkBoards(SmallBoard sm[]) {
		int counter = 0, position = 0;
		SmallBoard smallboards[][] = new SmallBoard[3][3];
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				smallboards[x][y] = sm[position];
				position++;
			}
		}
		
		//checks vertically
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (smallboards[x][y].isOverlay()) {
					counter++;
				}
			}
			if (counter == 3) 
				return true;
			counter = 0;
		}
		return false;
	}
	

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game());
		
		app.setShowFPS(false);
		app.setDisplayMode(screenWidth, screenHeight, false);
		app.start();
	}
}
