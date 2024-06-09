package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_bonfire extends SuperObject {

	public OBJ_bonfire() {

		name = "bonfire";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/OBJ_bonfire1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		collision = true;
	}
}
