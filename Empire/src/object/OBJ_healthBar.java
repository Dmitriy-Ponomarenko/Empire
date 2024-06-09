package object;

import java.io.IOException;
import main.GamePanel;
import javax.imageio.ImageIO;

public class OBJ_healthBar extends SuperObject {

	GamePanel gp;

	public OBJ_healthBar(GamePanel gp) {

		name = "healthBar";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/health-bar-10.png"));
			image1 = ImageIO.read(getClass().getResourceAsStream("/objects/health-bar-20.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/objects/health-bar-30.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/objects/health-bar-40.png"));
			image4 = ImageIO.read(getClass().getResourceAsStream("/objects/health-bar-50.png"));
			image5 = ImageIO.read(getClass().getResourceAsStream("/objects/health-bar-60.png"));
			image6 = ImageIO.read(getClass().getResourceAsStream("/objects/health-bar-70.png"));
			image7 = ImageIO.read(getClass().getResourceAsStream("/objects/health-bar-80.png"));
			image8 = ImageIO.read(getClass().getResourceAsStream("/objects/health-bar-90.png"));
			image9 = ImageIO.read(getClass().getResourceAsStream("/objects/health-bar-100.png"));

			image = uTool.scaleImage(image, gp.tileSize * 3, gp.tileSize * 2);
			image1 = uTool.scaleImage(image1, gp.tileSize, gp.tileSize);
			image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
			image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
			image4 = uTool.scaleImage(image4, gp.tileSize, gp.tileSize);
			image5 = uTool.scaleImage(image5, gp.tileSize, gp.tileSize);
			image6 = uTool.scaleImage(image6, gp.tileSize, gp.tileSize);
			image7 = uTool.scaleImage(image7, gp.tileSize, gp.tileSize);
			image8 = uTool.scaleImage(image8, gp.tileSize, gp.tileSize);
			image9 = uTool.scaleImage(image9, gp.tileSize * 4, gp.tileSize + 70);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
