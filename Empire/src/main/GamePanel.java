package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import entity.Entity;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SATTINGS
	final int originalTileSize = 32; // 32x32: default size of a tile
	final int scale = 3; // tile scale for the screen

	public final int tileSize = originalTileSize * scale; // 32 * 3 = 96px: screen size of a tile
	public final int maxScreenCol = 16; // the number of columns - horizontally
	public final int maxScreenRow = 9; // the number of rows - vertically
	public final int screenWidth = tileSize * maxScreenCol; // 1536px - screen width
	public final int screenHeight = tileSize * maxScreenRow; // 864px - screen height

	// WORLD SETTING
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;

	// FPS
	int FPS = 60;

	// SYSTEM
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound soundEffect = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread; // Thread to make the game go by itself

	// ENTITY AND OBJECTS
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];

	// MAP PLACEMENT

	// GAME STATE
	public int gameState;
	public final int downloadState = 0; // game opening screen
	public final int menuState = 1; // starting screen (menu)
	public final int playState = 2; // in-game screen
	public final int pauseState = 3; // pause screen

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // new Dimension 50x50 world squares (not px)
		this.setBackground(new Color(0x93, 0xaf, 0x66)); // default background color

		this.setDoubleBuffered(true); // drawing the graphic components into the screen image
										// buffer and then copying the contents of the buffer to the screen all at once
		this.addKeyListener(keyH);
		this.setFocusable(true); // GamePanel can be focused to receive the key input
	}

	// GAME SETUP
	public void setupGame() {

		aSetter.setObject();
		aSetter.setNPC();
		playMusic(0);
		gameState = playState;
	}

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

		// if (gameState == playState) {
		// PLAYER
		player.update();
		// NPC
		for (int i = 0; i < npc.length; i++) {
			if (npc[i] != null) {
				npc[i].update();
			}
		}
		// } else if (gameState == pauseState) {
		// // Add pause logic here
		// }
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// TILE
		tileM.draw(g2);

		// OBJECT
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}

		// NPC
		for (int i = 0; i < npc.length; i++) {
			if (npc[i] != null) {
				npc[i].draw(g2);
			}
		}

		// PLAYER
		player.draw(g2);

		// UI
		ui.draw(g2);

		g2.dispose();
	}

	public void playMusic(int i) {

		music.setFile(i);
		music.play();
		music.loop();
	}

	public void stopMusic() {

		music.stop();
	}

	public void playSoundEffect(int i) {

		// gp.playSoundEffect(index of the sound effect);
		soundEffect.setFile(i);
		soundEffect.play();
	}
}
