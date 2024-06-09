package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {

		JFrame window = new JFrame(); // frame formation
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // possibility to exit the program by closing
		window.setResizable(false); // game window is not resizable
		window.setTitle("Empire"); // game window Title

		GamePanel gamePanel = new GamePanel(); // starting the GamePanel class
		window.add(gamePanel); // adding the constructor of gamePanel

		window.pack(); // the window sizes to fit the preferred size (GamePanel)

		window.setLocationRelativeTo(null); // window display at the center of the screen
		window.setVisible(true); // the opportunity to see the window

		gamePanel.startGameThread(); // start of the game (game life)
		gamePanel.setupGame();
	}

}
