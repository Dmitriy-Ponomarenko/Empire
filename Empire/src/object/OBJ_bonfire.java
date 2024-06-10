package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_bonfire extends Entity {

	public OBJ_bonfire(GamePanel gp) {
		super(gp);

		name = "bonfire";
		down1 = setup("/objects/OBJ_bonfire1");
		// try {
		// image =
		// ImageIO.read(getClass().getResourceAsStream("/objects/OBJ_bonfire1.png"));
		// } catch(IOException e) {
		// e.printStackTrace();
		// }

		collision = true;

		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 96;
		solidArea.height = 96;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
