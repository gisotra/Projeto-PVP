package background;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import loop.GCanvas;
import utilz.Screen;
import utilz.SpriteData;
import utilz.SpriteLoader;
import utilz.Spritesheet;
import utilz.Universal;

public class Trees1 extends Environment {
    
    float midValue = 5.4f;
    //private double speed = Universal.OBST_SPEED; // Velocidade relativa ao mundo
    BufferedImage groundSpriteSheet;
    /*
    float drawX = x + i * (widthO * spritesheet.scale);
    spritesheet.render(g2d, (int) drawX, (int) y);
    */
    
    public Trees1(Screen screen, GCanvas gc) {
        super(screen, gc);
        this.y = -80;
        this.x = 0;
        initSprite();
        setIsActive(true);
    }

    public void initSprite() {
        SpriteData grassData = SpriteLoader.spriteDataLoader().get("midLayer");
        try {
            groundSpriteSheet = ImageIO.read(getClass().getResource(grassData.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //altura e largura do meu sprite do chão (fullscreen)
        setWidth(896); //largura em px do FRAME ORIGINAL 
        setHeight(256); //altura em px do FRAME ORIGINAL
        setSpritesheet(groundSpriteSheet, Universal.SCALE);
    }
    
    public void setSpritesheet(BufferedImage spritesheet, float renderScale) {
        this.spritesheet = new Spritesheet(spritesheet, heightO, widthO, 0.0, renderScale);
    }
    
    @Override
    public void update(double deltaTime) {
        if (x + widthO * Universal.SCALE >= 0) {
            this.setX(this.getX() + (float) (Universal.OBST_SPEED + midValue * (float)deltaTime));
            if (x <= -Universal.GAME_WIDTH ) {
                x = 0;
            }
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        if (x + widthO * Universal.SCALE >= 0 && x <= Universal.GAME_WIDTH) {
            spritesheet.render(g2d, (int) x, (int) y);
        }
    }
    
}
