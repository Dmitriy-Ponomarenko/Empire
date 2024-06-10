package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	KeyHandler keyH;

	public final int screenX;
	public final int screenY;

	public Player(GamePanel gp, KeyHandler keyH) {

		super(gp);
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle(0, 0, 32, 32);
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		setDefoultValues();
		getPlayerImage();
		getPlayerAttackImage();
	}

	public void setDefoultValues() {

		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 23;
		playerSpeed = 3;
		playerDiagonalSpeed = (int) (playerSpeed / Math.sqrt(2));
		direction = "down";

		// PLAYER STATUS
		maxLife = 100;
		life = maxLife;
	}

	public void getPlayerImage() {

		up1 = setup("/player/playerStandingUp");
		up2 = setup("/player/playerStandingUp");
		down1 = setup("/player/playerStandingDown");
		down2 = setup("/player/playerStandingDown");
		right1 = setup("/player/playerStandingRight");
		right2 = setup("/player/playerStandingRight");
		left1 = setup("/player/playerStandingLeft");
		left2 = setup("/player/playerStandingLeft");
	}

	public void getPlayerAttackImage() {

		// attackUp1 = setup("/enemy/warrior-attack-up1");
		// attackUp2 = setup("/player/warrior-attack-up2");
		// attackDown1 = setup("/player/warrior-attack-down1");
		// attackDown2 = setup("/player/warrior-attack-down2");
		// attackRight1 = setup("/player/warrior-attack-right1");
		// attackRight2 = setup("/player/warrior-attack-right2");
		// attackLeft1 = setup("/player/warrior-attack-left1");
		// attackLeft2 = setup("/player/warrior-attack-left2");
	}

	public void update() {

		if (attacking == true) {
			attacking();
		}

		// Sprite-change pause/action
		else if (keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {

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
					worldY -= playerDiagonalSpeed * 2;
					worldX += playerDiagonalSpeed * 2;
				} else {
					worldY -= playerDiagonalSpeed;
					worldX += playerDiagonalSpeed;
				}
			} else if (keyH.upPressed && keyH.leftPressed) {
				if (keyH.sprint) {
					worldY -= playerDiagonalSpeed * 2;
					worldX -= playerDiagonalSpeed * 2;
				} else {
					worldY -= playerDiagonalSpeed;
					worldX -= playerDiagonalSpeed;
				}
			} else if (keyH.downPressed && keyH.rightPressed) {
				if (keyH.sprint) {
					worldY += playerDiagonalSpeed * 2;
					worldX += playerDiagonalSpeed * 2;
				} else {
					worldY += playerDiagonalSpeed;
					worldX += playerDiagonalSpeed;
				}
			} else if (keyH.downPressed && keyH.leftPressed) {
				if (keyH.sprint) {
					worldY += playerDiagonalSpeed * 2;
					worldX -= playerDiagonalSpeed * 2;
				} else {
					worldY += playerDiagonalSpeed;
					worldX -= playerDiagonalSpeed;
				}

			}

			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// CHECK OBJECT COLLISION
			int objectIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objectIndex);

			// CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			// CHECK ENEMY COLLISION
			int enemyIndex = gp.cChecker.checkEntity(this, gp.enemy);
			contactEnemy(enemyIndex);

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
						// break;
						// case "upRight":
						// if (keyH.sprint) {
						// worldX += playerSpeed * 2;
						// worldY -= playerSpeed * 2;
						// } else {
						// worldX -= playerSpeed;
						// worldY -= playerSpeed;
						// }
						// break;
						// case "upLeft" :
						// if (keyH.sprint) {
						// worldX -= playerSpeed * 2;
						// worldY -= playerSpeed * 2;
						// } else {
						// worldX -= playerSpeed;
						// worldY -= playerSpeed;
						// }
						// break;
						// case "downRight":
						// if (keyH.sprint) {
						// worldX += playerSpeed * 2;
						// worldY += playerSpeed * 2;
						// } else {
						// worldX += playerSpeed;
						// worldY += playerSpeed;
						// }
						// break;
						// case "downLeft":
						// if (keyH.sprint) {
						// worldX -= playerSpeed * 2;
						// worldY += playerSpeed * 2;
						// } else {
						// worldX -= playerSpeed;
						// worldY += playerSpeed;
						// }
						// break;
				}
			}

			gp.keyH.enterPressed = false;

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

		}

		// INVINCIBILITY TIMER
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}

	public void pickUpObject(int i) {

	}

	public void attacking() {

		spriteCounter++;

		if (spriteCounter <= 5) {
			spriteNum = 1;
		}
		if (spriteCounter >= 5 && spriteCounter <= 25) {
			spriteNum = 2;
		}
		if (spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}

	public void interactNPC(int i) {

		if (i != 999) {

		}
	}

	public void contactEnemy(int i) {

		if (i != 999) {

			if (invincible == false) {
				life -= 15;
				invincible = true;
			}
		}
	}

	public void draw(Graphics2D g2) {

		// g2.setColor(Color.white);
		// g2.fillRect(playerX, playerY, gp.tileSize, gp.tileSize);

		BufferedImage image = null;

		switch (direction) {
			case "up":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = up1;
					}
					if (spriteNum == 2) {
						image = up2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackUp1;
					}
					if (spriteNum == 2) {
						image = attackUp2;
					}
				}
				break;
			case "down":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = down1;
					}
					if (spriteNum == 2) {
						image = down2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackDown1;
					}
					if (spriteNum == 2) {
						image = attackDown2;
					}
				}
				break;
			case "right":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = right1;
					}
					if (spriteNum == 2) {
						image = right2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackRight1;
					}
					if (spriteNum == 2) {
						image = attackRight2;
					}
				}
				break;
			case "left":
				if (attacking == false) {
					if (spriteNum == 1) {
						image = left1;
					}
					if (spriteNum == 2) {
						image = left2;
					}
				}
				if (attacking == true) {
					if (spriteNum == 1) {
						image = attackLeft1;
					}
					if (spriteNum == 2) {
						image = attackLeft2;
					}
				}
				break;
		}

		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}

		g2.drawImage(image, screenX, screenY, null);

		// Reset alpha sprite channel
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
}
