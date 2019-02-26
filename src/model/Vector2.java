package model;

public class Vector2 {
	protected int x;
	protected int y;

	public Vector2() {
	}

	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean equals(Vector2 b) {
		return this.x == b.x && this.y == b.y;
	}
}
