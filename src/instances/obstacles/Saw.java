package instances.obstacles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import loop.GCanvas;
import utilz.Screen;
import utilz.SpriteData;
import utilz.SpriteLoader;
import utilz.Spritesheet;
import utilz.Universal;

public class Saw extends Obstacles{ //extends Obstacles
    /*------------ ATRIBUTOS ------------*/
    BufferedImage sawSpriteSheet;
    /*------------ CONSTRUTOR ------------*/
    public Saw(Screen screen, GCanvas gc) {
        super(screen, gc);
        setY(Universal.SAW_SPAWN_Y);
        setX(Universal.OBST_SPAWN_X); //fora da tela na direita
        initSprite();
        initObstHitbox();
        setIsActive(false);
    }
    
    public void initSprite() {
        SpriteData sawData = SpriteLoader.spriteDataLoader().get("saw");
        try {
            sawSpriteSheet = ImageIO.read(getClass().getResource(sawData.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        setWidth(64); //largura em px do FRAME ORIGINAL 
        setHeight(32); //altura em px do FRAME ORIGINAL
        setSpritesheet(sawSpriteSheet, Universal.SCALE);
    }
    
    @Override
    protected void drawObstHitbox(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.drawRect((int) obs_hitbox.x, (int) obs_hitbox.y, (int) obs_hitbox.width, (int) obs_hitbox.height);
    }
    
    @Override
    public void initObstHitbox() { //x, y, largura, altura
        this.obs_hitbox = new Rectangle2D.Float(getX(), getY(), 2*Universal.TILES_SIZE, Universal.TILES_SIZE); //metade do tamanho
    }
    
    @Override
    public void setSpritesheet(BufferedImage spritesheet, float renderScale) {
        this.spritesheet = new Spritesheet(spritesheet, heightO, widthO, 1.0, renderScale);
    }
}