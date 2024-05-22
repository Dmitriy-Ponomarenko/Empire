package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	public Player (GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		setDefoultValues();
		getPlayerImage();
	}
	
	public void setDefoultValues() {
		
		playerX = 100;
		playerY = 100;
		playerSpeed = 3;
		diagonalSpeed =  (int) (playerSpeed / Math.sqrt(2));
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/ModelUp1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/ModelUp2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/ModelDown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/ModelDown2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/ModelRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/ModelRight2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/ModelLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/ModelLeft2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		// Sprite-change pause/action
		if (keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true) {
			
		    // WALK
		    if (keyH.upPressed == true) {
		    	direction = "up";
		    	
		        if (keyH.sprint) {
		            playerY -= playerSpeed * 2;
		        } else {
		            playerY -= playerSpeed;
		        }

		    } else if (keyH.downPressed == true) {
		    	direction = "down";
		    	
		        if (keyH.sprint) {
		            playerY += playerSpeed * 2;
		        } else {
		            playerY += playerSpeed;
		        }

		    } else if (keyH.rightPressed == true) {
		    	direction = "right";
		    	
		        if (keyH.sprint) {
		            playerX += playerSpeed * 2;
		        } else {
		            playerX += playerSpeed;
		        }

		    } else if (keyH.leftPressed == true) {
		    	direction = "left";
		    	
		        if (keyH.sprint) {
		            playerX -= playerSpeed * 2;
		        } else {
		            playerX -= playerSpeed;
		        }

		    }
			
			// Diagonal directions
		    if (keyH.upPressed && keyH.rightPressed) {
		        if (keyH.sprint) {
		            playerY -= diagonalSpeed * 2;
		            playerX += diagonalSpeed * 2;
		        } else {
		            playerY -= diagonalSpeed;
		            playerX += diagonalSpeed;
		        }
		    } else if (keyH.upPressed && keyH.leftPressed) {
		        if (keyH.sprint) {
		            playerY -= diagonalSpeed * 2;
		            playerX -= diagonalSpeed * 2;
		        } else {
		            playerY -= diagonalSpeed;
		            playerX -= diagonalSpeed;
		        }
		    } else if (keyH.downPressed && keyH.rightPressed) {
		        if (keyH.sprint) {
		            playerY += diagonalSpeed * 2;
		            playerX += diagonalSpeed * 2;
		        } else {
		            playerY += diagonalSpeed;
		            playerX += diagonalSpeed;
		        }
		    } else if (keyH.downPressed && keyH.leftPressed) {
		        if (keyH.sprint) {
		            playerY += diagonalSpeed * 2;
		            playerX -= diagonalSpeed * 2;
		        } else {
		            playerY += diagonalSpeed;
		            playerX -= diagonalSpeed;
		        }

		    }
			
		 // SPRITE-CHANGE
		    spriteCounter++;
		    if (keyH.sprint) {
		        if(spriteCounter > 7) {
		            if(spriteNum == 1) {
		                spriteNum = 2;
		            } else if(spriteNum == 2) {
		                spriteNum = 1;
		            }
		            spriteCounter = 0;
		        }
		    } else {
		        if(spriteCounter > 15) {
		            if(spriteNum == 1) {
		                spriteNum = 2;
		            } else if(spriteNum == 2) {
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
		
//		g2.setColor(Color.white);
//		g2.fillRect(playerX, playerY, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image =  up1;
			}
			if(spriteNum == 2) {
				image =  up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image =  down1;
			}
			if(spriteNum == 2) {
				image =  down2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image =  right1;
			}
			if(spriteNum == 2) {
				image =  right2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image =  left1;
			}
			if(spriteNum == 2) {
				image =  left2;
			}
			break;
		}
		
		g2.drawImage(image, playerX, playerY, gp.tileSize, gp.tileSize, null);
	}
}
