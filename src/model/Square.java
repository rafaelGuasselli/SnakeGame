package model;

import java.awt.Color;
import java.awt.Graphics;

public class Square {
	private Vector2 pos;
	private final int SIZE;
	Color COLOR;

	public Square(int lado, Color color, int x, int y) {
		this.pos = new Vector2(0, 0);
		this.SIZE = lado;
		this.COLOR = color;
		this.pos.x = x;
		this.pos.y = y;
	}

	public Square(int size, Color color) {
		this.pos = new Vector2(0, 0);
		this.SIZE = size;
		this.COLOR = color;
	}

	public void paint(Graphics g) {
		g.setColor(COLOR);
		g.fillRect(pos.x, pos.y, SIZE, SIZE);
	}

	public void move(Vector2 pos) {
		this.pos.x += pos.x;
		this.pos.y += pos.y;
	}

	public boolean collision(Vector2 b) {
		return this.pos.equals(b);
	}

	public int getX() {
		return pos.x;
	}

	public int getY() {
		return pos.y;
	}

	public void setPos(int x, int y) {
		this.pos = new Vector2(x, y);
	}

	public Vector2 getPos() {
		return pos;
	}

	public int getSIZE() {
		return SIZE;
	}

}
