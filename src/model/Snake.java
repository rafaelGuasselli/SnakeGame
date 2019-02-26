package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import view.View.Direction;

public class Snake {
	private final int SIZE;
	private final Color COLOR;
	private List<Square> squares;
	
	public Snake(int x, int y) {
		squares = new ArrayList<Square>();
		COLOR = Color.WHITE;
		SIZE = 10;

		this.add(x, y);
		this.add(x - 10, y);
	}

	public void paint(Graphics g) {
		for (Square quadrado : squares) {
			quadrado.paint(g);
		}
	}

	public void move(int x, int y) {
		Vector2 pos = new Vector2(x, y);
		for (int i = squares.size() - 1; i > 0; i--) {
			squares.get(i).setPos(squares.get(i - 1).getPos().x, squares.get(i - 1).getPos().y);

		}
		squares.get(0).move(pos);
	}

	public void update(Direction direction) {
		switch (direction) {
		case UP:
			this.move(0, -10);
			break;
		case DOWN:
			this.move(0, +10);
			break;
		case RIGHT:
			this.move(+10, 0);
			break;
		case LEFT:
			this.move(-10, 0);
			break;
		}
		
		if (this.pos(0).x > 580) {
			this.pos(0).x = 0;
		} else if (this.pos(0).x < 0) {
			this.pos(0).x = 580;
		}
		if (this.pos(0).y > 560) {
			this.pos(0).y = 0;
		} else if (this.pos(0).y < 0) {
			this.pos(0).y = 560;
		}

	}

	public void eat() {
		if (this.pos(this.size() - 1).getY() - this.pos(this.size() - 2).getY() == 10) {
			this.add(this.pos(this.size() - 1).getX(), this.pos(this.size() - 1).getY() + 10);
		}
		if (this.pos(this.size() - 1).getY() - this.pos(this.size() - 2).getY() == -10) {
			this.add(this.pos(this.size() - 1).getX(), this.pos(this.size() - 1).getY() - 10);
		}
		if (this.pos(this.size() - 1).getX() - this.pos(this.size() - 2).getX() == 10) {
			this.add(this.pos(this.size() - 1).getX() + 10, this.pos(this.size() - 1).getY());
		}
		if (this.pos(this.size() - 1).getX() - this.pos(this.size() - 2).getX() == -10) {
			this.add(this.pos(this.size() - 1).getX() - 10, this.pos(this.size() - 1).getY());
		}
	}
	
	public boolean gameOver() {
		boolean over = false;
		for(int i = 1; i < this.size(); i++) {
			if(this.collision(squares.get(i).getPos())) {
				over = true;
				break;
			}
		}
		return over;
	}

	public void add(int x, int y) {
		squares.add(new Square(SIZE, COLOR, x, y));
	}

	public Vector2 pos(int i) {
		return squares.get(i).getPos();
	}

	public int size() {
		return squares.size();
	}

	public boolean collision(Vector2 b) {
		return squares.get(0).collision(b);
	}
	
}
