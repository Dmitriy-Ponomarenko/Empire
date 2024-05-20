package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SATTINGS
	final int originalTileSize = 32; // 32x32: default size of a tile
	final int scale = 10; // tile scale for the screen

	public final int tileSize = originalTileSize * scale; // 32 * 3 = 96px: screen size of a tile
	public final int maxScreenCol = 16; // the number of columns - horizontally
	public final int maxScreenRow = 9; // the number of rows - vertically
	public final int screenWidth = tileSize * maxScreenCol; // 1536px - screen width
	public final int screenHight = tileSize * maxScreenRow; // 864px - screen height

	// WORLD SETTING

	// FPS
	int FPS = 60;

	// SYSTEM
	KeyHandler keyH = new KeyHandler();
	Thread gameThread; // Thread to make the game go by itself

	// MAP PLACEMENT

	// GAME STATE
	public int gameState;
	public final int downloadState = 0; // game opening screen
	public final int menuState = 1; // starting screen (menu)
	public final int playState = 2; // in-game screen
	public final int pauseState = 3; // pause screen

	// DEFOLT PLAYER POSITION
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHight)); // new Dimension 50x50 world squares (not px)
		this.setBackground(Color.black); // default background color

		this.setDoubleBuffered(true); // drawing the graphic components into the screen image
										// buffer and then copying the contents of the buffer to the screen all at once
		this.addKeyListener(keyH);
		this.setFocusable(true); // GamePanel can be focused to receive the key input
	}

	// GAME SETUP

	// THREAD ACTION
	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	// Creation of the Runnable interface
	public void run() {

		// Game FPS in nanoseconds
		double drawInterval = 1000000000 / FPS; // 0.016667 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;

		// FPS checker
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {

			long currentTime = System.nanoTime();
			// System.out.println("Current time is: " + currentTime);

			// UPDATE: updates the information of the game
			update();
			// DRAW: draws the updated information on the screen
			repaint(); // The call of the paintComponent method

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);

				nextDrawTime += drawInterval;

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {

		if (keyH.upPressed == true) {
			playerY -= playerSpeed;

		} else if (keyH.downPressed == true) {
			playerY += playerSpeed;

		} else if (keyH.rightPressed == true) {
			playerX += playerSpeed;

		} else if (keyH.leftPressed == true) {
			playerX -= playerSpeed;

		} else {

		}

		if (keyH.upRightPressed == true) {
			playerX += playerSpeed / 2;
			playerY -= playerSpeed / 2;
		}

		if (keyH.downRightPressed == true) {
			playerX += playerSpeed / 2;
			playerY += playerSpeed / 2;
		}

		if (keyH.downLeftPressed == true) {
			playerX -= playerSpeed / 2;
			playerY += playerSpeed / 2;
		}

		if (keyH.upLeftPressed == true) {
			playerX -= playerSpeed / 2;
			playerY -= playerSpeed / 2;
		}
		// if(gameState == playState) {
		// player.update();
		// }
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		g2.dispose();
	}

}
