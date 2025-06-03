package instances.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import loop.GCanvas;
import utilz.Screen;
import utilz.SpriteData;
import utilz.SpriteLoader;
import utilz.Universal;

public class Player1 extends Entities{
    
    //https://www.youtube.com/watch?v=rTVoyWu8r6g
    /*------------ ATRIBUTOS ------------*/
    Movement movement;
    Collider collider;
    BufferedImage playerSpriteSheet;
    public int playerAction = Universal.IDLE;
    
    public Player1(Screen screen, GCanvas gc){
        super(screen, gc);
        movement = new Movement(this);
        initSprite();
        setX(120);
        setY(360);
        movement.isJumping = true;
    }     
   
    @Override
    public void update(double deltaTime){
        movement.updatePosY(deltaTime);
        movement.updatePosX(deltaTime);
        updateHitbox();
    }

    @Override
    public void render(Graphics2D g2d){
        spritesheet.setAtion(playerAction); // altero ou mantenho a linha do spritesheet
        spritesheet.render(g2d, (int) getX(), (int) getY());
    }
    
    public void initSprite(){
        SpriteData playerData = SpriteLoader.spriteDataLoader().get("player1");
        try {
            playerSpriteSheet = ImageIO.read(getClass().getResource(playerData.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //inicio as propriedades do meu sprite player
        setWidth(32);
        setHeight(32);
        setSpritesheet(playerSpriteSheet, Universal.SCALE);
    }

    /*------------ GETTERS AND SETTERS ------------*/
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getHeight() {
        return heightO;
    }
}
    
    



