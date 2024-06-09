package entity;

import main.GamePanel;
import java.util.Random;

public class NPC_Sheep extends Entity {

	public NPC_Sheep(GamePanel gp) {
		super(gp);

		direction = "down";
		defoltSpeed = 1;

		getImage();
	}

	public void getImage() {

		up1 = setup("/npc/NPC_sheepUp");
		up2 = setup("/npc/NPC_sheepUp");
		down1 = setup("/npc/NPC_sheepDown");
		down2 = setup("/npc/NPC_sheepDown");
		right1 = setup("/npc/NPC_sheepRight");
		right2 = setup("/npc/NPC_sheepRight");
		left1 = setup("/npc/NPC_sheepLeft");
		left2 = setup("/npc/NPC_sheepLeft");
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
