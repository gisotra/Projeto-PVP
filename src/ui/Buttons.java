package ui;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import utilz.Spritesheet;

public class Buttons {
    public int x, y, width, height;
    public Rectangle dimensoes; //vou usar isso pra inserir o click do mouse
    public BufferedImage spriteButton;
    public Spritesheet spritesheetB;

    public Buttons(int x, int y, int width, int height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initRectangle();
        initSpritesheet(image);
    }
    
    public void initRectangle(){
            dimensoes = new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public void initSpritesheet(BufferedImage image){
        this.spritesheetB = new Spritesheet(spriteButton, height, width, x, x);
    }
    
    
    
    
    
}
