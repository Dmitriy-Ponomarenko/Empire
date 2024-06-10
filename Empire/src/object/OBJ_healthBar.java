package object;

import main.GamePanel;
import entity.Entity;
import main.UtilityTool;

import java.awt.image.BufferedImage;

public class OBJ_healthBar extends Entity {

    public OBJ_healthBar(GamePanel gp) {
        super(gp);

        name = "healthBar";

        image = scaleHealthBarImage(gp, "/objects/health-bar-10.png");
        image1 = scaleHealthBarImage(gp, "/objects/health-bar-20.png");
        image2 = scaleHealthBarImage(gp, "/objects/health-bar-30.png");
        image3 = scaleHealthBarImage(gp, "/objects/health-bar-40.png");
        image4 = scaleHealthBarImage(gp, "/objects/health-bar-50.png");
        image5 = scaleHealthBarImage(gp, "/objects/health-bar-60.png");
        image6 = scaleHealthBarImage(gp, "/objects/health-bar-70.png");
        image7 = scaleHealthBarImage(gp, "/objects/health-bar-80.png");
        image8 = scaleHealthBarImage(gp, "/objects/health-bar-90.png");
        image9 = scaleHealthBarImage(gp, "/objects/health-bar-100.png");
    }

    private BufferedImage scaleHealthBarImage(GamePanel gp, String imagePath) {
        UtilityTool uTool = new UtilityTool();

        return uTool.scaleImage(setup("/objects/health-bar-100"), gp.tileSize * 4, gp.tileSize + 70);
    }
}
