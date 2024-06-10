package enemy;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class ENM_NormalEnemy extends Entity {

	GamePanel gp;

	public ENM_NormalEnemy(GamePanel gp) {
		super(gp);

		this.gp = gp;

		type = 2;
		name = "Normal Enemy";
		defoltSpeed = 2;
		maxLife = 25;
		life = maxLife;

		solidArea.x = 3;
		solidArea.y = 3;
		solidArea.width = 90;
		solidArea.height = 90;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		getImage();
	}

	public void getImage() {

		up1 = setup("/enemy/normal-enemy-up");
		up2 = setup("/enemy/normal-enemy-up");
		right1 = setup("/enemy/normal-enemy-right");
		right2 = setup("/enemy/normal-enemy-right");
		down1 = setup("/enemy/normal-enemy-down");
		down2 = setup("/enemy/normal-enemy-down");
		left1 = setup("/enemy/normal-enemy-left");
		left2 = setup("/enemy/normal-enemy-left");
	}

	public void setAction() {

		actionInterval++;

		if (actionInterval == 180) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;

			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "right";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}

			actionInterval = 0;
		}

	}

}
