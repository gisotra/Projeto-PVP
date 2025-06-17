package ui;

import gamestates.Gamestate;
import static gamestates.Gamestate.state;
import java.awt.*;
import java.awt.image.BufferedImage;
import utilz.Spritesheet;
import utilz.Universal;

public class Buttons {
    
    /*---------- ATRIBUTOS ----------*/
    public int x, y, width, height;
    public Rectangle dimensoes; //vou usar isso pra inserir o click do mouse
    public BufferedImage spriteButton;
    public Spritesheet spritesheetB;
    public boolean cursorOver, cursorPressed;
    public Gamestate state;
    
    /*---------- CONSTRUTOR ----------*/
    public Buttons(int x, int y, int width, int height, BufferedImage image, Gamestate state) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.spriteButton = image;
        this.state = state;
        initRectangle();
        initSpritesheet(image);
        
    }
    
    /*---------- métodos próprios ----------*/
    public void initRectangle(){
            dimensoes = new Rectangle(this.x, this.y, this.width * (int) Universal.SCALE, this.height * (int)Universal.SCALE);
    }
    
    public void initSpritesheet(BufferedImage image){
        this.spritesheetB = new Spritesheet(spriteButton, image.getHeight(), image.getWidth(), 0, Universal.SCALE);
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
    
    public Gamestate getState(){
        return state;
    }
    
    public void applyGamestate() {
        Gamestate.state = state;
    }
    
    public void resetBooleans(){
        this.cursorOver = false;
        this.cursorPressed = false;
    }
}
