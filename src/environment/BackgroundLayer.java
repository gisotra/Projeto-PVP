package environment;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.Universal;
public class BackgroundLayer extends ParallaxLayer {
    
    public BackgroundLayer(BufferedImage layer, float speedFactor) {
        super(layer, speedFactor);
    }
    
    @Override
    public void render(Graphics g) {
        float scale = 4f; // Escala fixa (igual ao player)

        int scaledImgWidth = (int) (layer.getWidth() * scale);
        int scaledImgHeight = (int) (layer.getHeight() * scale);

        int count = (Universal.GAME_WIDTH / scaledImgWidth) + 2;

        for (int i = 0; i < count; i++) {
            int drawX = (int) (x + i * scaledImgWidth);
            g.drawImage(layer, drawX, 0, scaledImgWidth, scaledImgHeight, null);
        }
    }
    
    
}
