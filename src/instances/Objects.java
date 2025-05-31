package instances;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import loop.GCanvas;
import utilz.Screen;
import utilz.Spritesheet;

/*Objects vai ser uma classe que todos os elementos do meu jogo vão herdar, tanto
o player1, quantos os obstáculos quanto o chão.*/

public abstract class Objects { 
    /*------------ ATRIBUTOS ------------*/
    Screen screen;
    GCanvas gc;
    public float x, y;
    public int width, height;
    public Spritesheet sprite;
    
    public Objects(Screen screen, GCanvas gc){
        this.screen = screen;
        this.gc = gc;
    }
    
    /*------------ GETTERS ------------*/
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Spritesheet getSprite() {
        return sprite;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

    /*------------ MÉTODOS ABSTRATOS ------------*/
    public abstract void update(double deltaTime);

    public abstract void render(Graphics2D g2d);
}
