package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

	GamePanel gp;
	Font arial_40;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;

	public UI(GamePanel gp) {
		this.gp = gp;

		arial_40 = new Font("Arial", Font.PLAIN, 40);
	}

	public void showMessage(String text) {

		// gp.ui.showMessege("");
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		// FONT
		g2.setFont(arial_40);
		g2.setColor(Color.white);

		// TOP MENU
		g2.drawString("HP = " + gp.player.playerHP, 50, 50);

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

	}
}
