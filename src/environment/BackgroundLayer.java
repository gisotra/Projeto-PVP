package environment;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import loop.GRoom;

public class BackgroundLayer extends ParallaxLayer {
    
    public BackgroundLayer(BufferedImage layer, float speedFactor) {
        super(layer, speedFactor);
    }
    
    @Override
    public void render(Graphics g) {

        int gameWidth = GRoom.TILES_IN_WIDTH * GRoom.TILES_SIZE;
        int gameHeight = GRoom.TILES_IN_HEIGHT * GRoom.TILES_SIZE;

        int imgWidth = layer.getWidth();
        int imgHeight = layer.getHeight();

        float scaleX = (float) gameHeight / imgHeight;
        float scaledImgWidth = imgWidth * scaleX;
        int count = (int) (gameWidth / scaledImgWidth) + 2;

        for (int i = 0; i < count; i++) {
            int drawX = (int) (x + i * scaledImgWidth);
            g.drawImage(layer, drawX, 0, (int) scaledImgWidth, gameHeight, null);
        }
    }
    
    
}
