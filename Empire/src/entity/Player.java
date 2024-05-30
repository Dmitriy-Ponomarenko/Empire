package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;

	public Player(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle(0, 0, 48, 48);

		setDefoultValues();
		getPlayerImage();
	}

	public void setDefoultValues() {

		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 23;
		playerSpeed = 3;
		playerHP = 100;
		diagonalSpeed = (int) (playerSpeed / Math.sqrt(2));
		direction = "down";
	}

	public void getPlayerImage() {

		up1 = setup("playerStandingUp");
		up2 = setup("playerStandingUp");
		down1 = setup("playerStandingDown");
		down2 = setup("playerStandingDown");
		right1 = setup("playerStandingRight");
		right2 = setup("playerStandingRight");
		left1 = setup("playerStandingLeft");
		left2 = setup("playerStandingLeft");
	}

	public BufferedImage setup(String imageName) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage scaledImage = null;

		try {
			scaledImage = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return scaledImage;
	}

	public void update() {

		// Sprite-change pause/action
		if (keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true) {

			// WALK
			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			} else if (keyH.leftPressed == true) {
				direction = "left";
			}

			// Diagonal directions
			if (keyH.upPressed && keyH.rightPressed) {
				if (keyH.sprint) {
					worldY -= diagonalSpeed * 2;
					worldX += diagonalSpeed * 2;
				} else {
					worldY -= diagonalSpeed;
					worldX += diagonalSpeed;
				}
			} else if (keyH.upPressed && keyH.leftPressed) {
				if (keyH.sprint) {
					worldY -= diagonalSpeed * 2;
					worldX -= diagonalSpeed * 2;
				} else {
					worldY -= diagonalSpeed;
					worldX -= diagonalSpeed;
				}
			} else if (keyH.downPressed && keyH.rightPressed) {
				if (keyH.sprint) {
					worldY += diagonalSpeed * 2;
					worldX += diagonalSpeed * 2;
				} else {
					worldY += diagonalSpeed;
					worldX += diagonalSpeed;
				}
			} else if (keyH.downPressed && keyH.leftPressed) {
				if (keyH.sprint) {
					worldY += diagonalSpeed * 2;
					worldX -= diagonalSpeed * 2;
				} else {
					worldY += diagonalSpeed;
					worldX -= diagonalSpeed;
				}

			}

			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// Collision == false => player can move
			if (collisionOn == false) {

				switch (direction) {
					case "up":
						if (keyH.sprint) {
							worldY -= playerSpeed * 2;
						} else {
							worldY -= playerSpeed;
						}
						break;
					case "down":
						if (keyH.sprint) {
							worldY += playerSpeed * 2;
						} else {
							worldY += playerSpeed;
						}
						break;
					case "left":
						if (keyH.sprint) {
							worldX -= playerSpeed * 2;
						} else {
							worldX -= playerSpeed;
						}
						break;
					case "right":
						if (keyH.sprint) {
							worldX += playerSpeed * 2;
						} else {
							worldX += playerSpeed;
						}
						break;
				}
			}

			// SPRITE-CHANGE
			spriteCounter++;
			if (keyH.sprint) {
				if (spriteCounter > 7) {
					if (spriteNum == 1) {
						spriteNum = 2;
					} else if (spriteNum == 2) {
						spriteNum = 1;
					}
					spriteCounter = 0;
				}
			} else {
				if (spriteCounter > 15) {
					if (spriteNum == 1) {
						spriteNum = 2;
					} else if (spriteNum == 2) {
						spriteNum = 1;
					}
					spriteCounter = 0;
				}
			}

			// if(gameState == playState) {
			// player.update();
			// }
		}
	}

	public void draw(Graphics2D g2) {

		// g2.setColor(Color.white);
		// g2.fillRect(playerX, playerY, gp.tileSize, gp.tileSize);

		BufferedImage image = null;

		switch (direction) {
			case "up":
				if (spriteNum == 1) {
					image = up1;
				}
				if (spriteNum == 2) {
					image = up2;
				}
				break;
			case "down":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = down2;
				}
				break;
			case "right":
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
				}
				break;
			case "left":
				if (spriteNum == 1) {
					image = left1;
				}
				if (spriteNum == 2) {
					image = left2;
				}
				break;
		}

		g2.drawImage(image, screenX, screenY, null);
	}
}
