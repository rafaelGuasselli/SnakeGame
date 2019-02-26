package controler;

import javax.swing.JFrame;

import view.View;

public class Main {
	static JFrame window;

	public static void main(String[] args) {
		View view = new View();
		window = new JFrame("Jogo da cobrinha");
		window.setSize(600, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addKeyListener(view);
		window.setResizable(false);
		window.add(view);

		window.setVisible(true);
	}
}
