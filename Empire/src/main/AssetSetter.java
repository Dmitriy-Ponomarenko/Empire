package main;

import entity.NPC_Sheep;
import object.OBJ_bonfire;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {

		gp.obj[0] = new OBJ_bonfire();
		gp.obj[0].worldX = gp.tileSize * 36;
		gp.obj[0].worldY = gp.tileSize * 12;

		gp.obj[1] = new OBJ_bonfire();
		gp.obj[1].worldX = gp.tileSize * 34;
		gp.obj[1].worldY = gp.tileSize * 40;
	}

	public void setNPC() {

		gp.npc[0] = new NPC_Sheep(gp);
		gp.npc[0].worldX = gp.tileSize * 15;
		gp.npc[0].worldY = gp.tileSize * 15;

		gp.npc[1] = new NPC_Sheep(gp);
		gp.npc[1].worldX = gp.tileSize * 11;
		gp.npc[1].worldY = gp.tileSize * 31;
	}
}
