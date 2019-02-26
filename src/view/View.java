package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import model.Apple;
import model.Snake;


public class View extends JPanel implements Runnable, KeyListener {
	public enum Direction {
		UP, DOWN, RIGHT, LEFT
	}

	private static final long serialVersionUID = 1;

	private Direction direction;
	private Snake snake;
	private Apple apple;
	private boolean canMove;
	private int score;
	private boolean gameOver;
	private int fps;
	private long second;

	public View() {
		fps = 30;
		second = 1000000000;
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		this.start();

		Thread thread = new Thread(this);
		thread.start();
	}

	public void start() {
		snake = new Snake(20, 20);
		apple = new Apple();
		direction = Direction.RIGHT;
		score = 0;
		gameOver = false;
	}

	@Override
	public void run() {
		long firstTime = 0;
		long lastTime = 0;
		long sleep = 0;
		while (true) {
			firstTime = System.nanoTime();
			if (!gameOver) {
				update();
				repaint();
			}
			lastTime = System.nanoTime();
			sleep = (second - ((lastTime - firstTime) * fps)) / (fps * (second / 1000));
			sleep(sleep);
			canMove = true;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		snake.paint(g2);
		apple.paint(g2);
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Comic Sans", java.awt.Font.BOLD, 25));

		if (!gameOver) {
			g2.drawString("Score: " + score, 20, 20);
		} else {
			g2.drawString("Game  over!", 150, 250);
			g2.drawString("Score: " + score, 150, 270);
			g2.drawString("Press return to try again", 150, 290);
		}

	}

	public void update() {
		snake.update(direction);
		gameOver = snake.gameOver();

		if (snake.collision(apple.getPos())) {
			snake.eat();
			score++;
			apple = new Apple();
		}
	}

	public void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			if (direction != Direction.DOWN && direction != Direction.UP && canMove) {
				this.direction = Direction.UP;
				canMove = false;
			}
			break;
		case KeyEvent.VK_S:
			if (direction != Direction.UP && direction != Direction.DOWN && canMove) {
				this.direction = Direction.DOWN;
				canMove = false;
			}
			break;
		case KeyEvent.VK_D:
			if (direction != Direction.LEFT && direction != Direction.RIGHT && canMove) {
				this.direction = Direction.RIGHT;
				canMove = false;
			}
			break;
		case KeyEvent.VK_A:
			if (direction != Direction.RIGHT && direction != Direction.LEFT && canMove) {
				this.direction = Direction.LEFT;
				canMove = false;
			}
			break;
		case KeyEvent.VK_ENTER:
			if (gameOver) {
				this.start();
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// nothing
	}
}