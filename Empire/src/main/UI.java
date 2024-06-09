package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.SuperObject;
import object.OBJ_healthBar;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40;
	BufferedImage healthBar10, healthBar20, healthBar30, healthBar40, healthBar50,
			healthBar60, healthBar70, healthBar80, healthBar90, healthBar100;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;

	public UI(GamePanel gp) {
		this.gp = gp;

		arial_40 = new Font("Arial", Font.PLAIN, 40);

		// CREATE HUD OBJECT
		SuperObject heart = new OBJ_healthBar(gp);
		healthBar10 = heart.image;
		healthBar20 = heart.image1;
		healthBar30 = heart.image2;
		healthBar40 = heart.image3;
		healthBar50 = heart.image4;
		healthBar60 = heart.image5;
		healthBar70 = heart.image6;
		healthBar80 = heart.image7;
		healthBar90 = heart.image8;
		healthBar100 = heart.image9;
	}

	public void showMessage(String text) {

		// gp.ui.showMessege("");
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		this.g2 = g2;

		// FONT
		g2.setFont(arial_40);
		g2.setColor(Color.white);

		// if (gp.gameState == gp.playState) {
		// TOP MENU
		drawPlayerLife();

		g2.drawString("HP = " + gp.player.life, 120, 180);

		// MESSAGE
		if (messageOn == true) {
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message, gp.tileSize / 2, gp.tileSize * 2);

			messageCounter++;

			if (messageCounter > 120) {
				messageCounter = 0;
				messageOn = false;
			}
		}

		// } else if (gp.gameState == gp.pauseState) {
		// drawPauseScreen();
		// }
	}

	public void drawPlayerLife() {

		int x = 0;
		int y = 0;
		int i = 0;

		g2.drawImage(healthBar100, x, y, null);
	}

	public void drawPauseScreen() {

		String text = "PAUSED";

		int x = getXforCenterText(text);
		int y = gp.screenHeight / 2;

		g2.drawString(text, x, y);
	}

	public int getXforCenterText(String text) {

		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}
}