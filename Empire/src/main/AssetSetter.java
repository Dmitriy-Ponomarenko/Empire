package main;

import entity.NPC_Sheep;
import object.OBJ_bonfire;
import enemy.ENM_NormalEnemy;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {

		gp.obj[0] = new OBJ_bonfire(gp);
		gp.obj[0].worldX = gp.tileSize * 36;
		gp.obj[0].worldY = gp.tileSize * 12;

		gp.obj[1] = new OBJ_bonfire(gp);
		gp.obj[1].worldX = gp.tileSize * 34;
		gp.obj[1].worldY = gp.tileSize * 40;
	}

	public void setNPC() {

		gp.npc[0] = new NPC_Sheep(gp);
		gp.npc[0].worldX = gp.tileSize * 13;
		gp.npc[0].worldY = gp.tileSize * 15;

		gp.npc[1] = new NPC_Sheep(gp);
		gp.npc[1].worldX = gp.tileSize * 11;
		gp.npc[1].worldY = gp.tileSize * 33;

		gp.npc[2] = new NPC_Sheep(gp);
		gp.npc[2].worldX = gp.tileSize * 32;
		gp.npc[2].worldY = gp.tileSize * 27;

		gp.npc[3] = new NPC_Sheep(gp);
		gp.npc[3].worldX = gp.tileSize * 18;
		gp.npc[3].worldY = gp.tileSize * 11;

		gp.npc[4] = new NPC_Sheep(gp);
		gp.npc[4].worldX = gp.tileSize * 40;
		gp.npc[4].worldY = gp.tileSize * 30;
	}

	public void setEnemy() {

		gp.enemy[0] = new ENM_NormalEnemy(gp);
		gp.enemy[0].worldX = gp.tileSize * 13;
		gp.enemy[0].worldY = gp.tileSize * 38;

		gp.enemy[1] = new ENM_NormalEnemy(gp);
		gp.enemy[1].worldX = gp.tileSize * 25;
		gp.enemy[1].worldY = gp.tileSize * 31;

		gp.enemy[2] = new ENM_NormalEnemy(gp);
		gp.enemy[2].worldX = gp.tileSize * 9;
		gp.enemy[2].worldY = gp.tileSize * 18;

		gp.enemy[3] = new ENM_NormalEnemy(gp);
		gp.enemy[3].worldX = gp.tileSize * 34;
		gp.enemy[3].worldY = gp.tileSize * 14;

	}
}
