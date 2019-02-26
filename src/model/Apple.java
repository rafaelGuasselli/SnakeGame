package model;

import java.awt.Color;
import java.awt.Graphics;

import model.util.Random;

public class Apple {
	private final Square square;
	private final Vector2 pos;
	private final Color COLOR;
	private final int SIZE;

	public Apple() {
		COLOR = Color.RED;
		SIZE = 10;

		this.pos = new Vector2(Random.range(0, 55) * 10, Random.range(0, 55) * 10);
		square = new Square(SIZE, COLOR, pos.x, pos.y);
	}

	public void paint(Graphics g) {
		square.paint(g);
	}

	public Vector2 getPos() {
		return pos;
	}
}
