package instances.entities;

import instances.Objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import loop.GCanvas;
import utilz.Screen;
import utilz.Spritesheet;
import utilz.Universal;

public abstract class Entities extends Objects{
    
    /*------------ ATRIBUTOS ------------*/
    protected Rectangle2D.Float hitbox;
    
    public Entities(Screen screen, GCanvas gc){
        super(screen, gc);
        initHitbox(getX(), getY(), Universal.TILES_SIZE, Universal.TILES_SIZE);
    }
    /*------------ MÉTODOS ------------*/
    protected void drawHitbox(Graphics g) {
        //para testar a hitbox
        g.setColor(Color.BLACK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }
    
    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }
    
    protected void updateHitbox(){
        hitbox.x = getX(); //atualizo a posição horizontal
        hitbox.y = getY(); //atualizo a posição vertical
    }

    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }
    
    public void setSpritesheet(BufferedImage spritesheet, float renderScale) { //criação da sprite + settar a velocidade
        sprite = new Spritesheet(spritesheet, width, height, 0.85, renderScale); 
    }

    
    @Override
    public abstract void update(double deltaTime);

    @Override
    public abstract void render(Graphics2D g2d);

}
