package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import utilz.Spritesheet;

public class Buttons {
    public int x, y, width, height;
    public Rectangle dimensoes; //vou usar isso pra inserir o click do mouse
    public BufferedImage spriteButton;
    public Spritesheet spritesheetB;
    public boolean cursorOver, cursorPressed;

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

    public void render(Graphics2D g2d){
        this.spritesheetB.render(g2d, this.x, this.y);
    }

    /*------------- GETTERS & SETTERS -------------*/
    
    public Rectangle getDimensoes(){
        return dimensoes;
    }

    public boolean isCursorOver() {
        return cursorOver;
    }

    public void setCursorOver(boolean cursorOver) {
        this.cursorOver = cursorOver;
    }

    public boolean isCursorPressed() {
        return cursorPressed;
    }

    public void setCursorPressed(boolean cursorPressed) {
        this.cursorPressed = cursorPressed;
    }
    
    
    
    
    
}
