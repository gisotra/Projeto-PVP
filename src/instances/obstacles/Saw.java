package instances.obstacles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import loop.GCanvas;
import utilz.Screen;
import utilz.Spritesheet;
import utilz.Universal;

public class Saw extends Obstacles{ //extends Obstacles
    /*------------ ATRIBUTOS ------------*/
    BufferedImage birdSpriteSheet;
    /*------------ CONSTRUTOR ------------*/
    public Saw(Screen screen, GCanvas gc) {
        super(screen, gc);
        setY(Universal.SAW_SPAWN_Y);
        setX(Universal.OBST_SPAWN_X); //fora da tela na direita
        initSprite();
        initObstHitbox();
    }
    
    public void initSprite() {
        try {
            birdSpriteSheet = ImageIO.read(getClass().getResourceAsStream("/assets/player/saw.png")); 
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        setWidth(64); //largura em px do FRAME ORIGINAL 
        setHeight(32); //altura em px do FRAME ORIGINAL
        setSpritesheet(birdSpriteSheet, Universal.SCALE);
    }
    
    @Override
    protected void drawObstHitbox(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.drawRect((int) obs_hitbox.x, (int) obs_hitbox.y, (int) obs_hitbox.width, (int) obs_hitbox.height);
    }
    
    @Override
    public void initObstHitbox() {
        this.obs_hitbox = new Rectangle2D.Float(getX(), getY(), Universal.TILES_SIZE, Universal.TILES_SIZE); //metade do tamanho
    }
    
    @Override
    public void update(double deltaTime){
        this.setX(this.getX() + speed);
        updateObstHitbox();
    }
    
    @Override
    public void render(Graphics2D g2d){
    
    spritesheet.render(g2d, (int) getX(), (int) getY()); 
    drawObstHitbox(g2d);
    }
    
    @Override
    protected void updateObstHitbox(){
        obs_hitbox.x = (int)getX(); //atualizo a posição horizontal
    }
    
    @Override
    public void setSpritesheet(BufferedImage spritesheet, float renderScale) {
        this.spritesheet = new Spritesheet(spritesheet, heightO, widthO, 0.0, renderScale);
    }
}