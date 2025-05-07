package environment;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class ParallaxLayer {
    
    /*--------- ATRIBUTOS ---------*/
    protected BufferedImage layer;
    protected float speedFactor;
    protected float x;
    
    /*--------- CONSTRUTOR ---------*/
    public ParallaxLayer(BufferedImage image, float speedFactor) {
        this.layer = image;
        this.speedFactor = speedFactor;
        this.x = 0;
    }
    
    /*--------- UPDATE ---------*/
    public void update(float baseSpeed) {
        x -= baseSpeed * speedFactor;
        if (x <= -layer.getWidth()) {
            x += layer.getWidth();
        }
    }
    
    
    public abstract void render(Graphics g);
    
}
