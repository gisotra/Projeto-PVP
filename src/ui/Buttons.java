package ui;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import utilz.Spritesheet;

public class Buttons {
    public int x, y, width, height;
    public Rectangle dimensoes; //vou usar isso pra inserir o click do mouse
    public BufferedImage spriteButton;
    public Spritesheet spritesheetB;

    public Buttons(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /*public void initSprites(){
        SpriteData menuButtonData = 
        try{
            
        } catch(){
            
        }
    }*/
    
    public void initRectangle(){
            dimensoes = new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public void spritesheet(){
        this.spritesheetB = new Spritesheet(spriteButton, height, width, x, x);
    }
    
    
    
    
}
