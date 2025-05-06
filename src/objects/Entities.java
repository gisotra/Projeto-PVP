package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Entities {
    
    /*------------ ATRIBUTOS ------------*/
    protected float x,y; //todas as classes que extenderão Entities vão ter x e y para herdar
    protected int p_width, p_height;
    protected Rectangle2D.Float hitbox;
    

    /*------------ CONSTRUTOR ------------*/
    public Entities(float x, float y, int p_width, int p_height){
        this.x = x;
        this.y = y;
        this.p_width = p_width;
        this.p_height = p_height;        
    }
    
    
    protected void drawHitbox(Graphics g) {
        //para testar a hitbox
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }
    
    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }
    
    protected void updateHitbox(){
        hitbox.x = (int)x; //atualizo a posição horizontal
        hitbox.y = (int)y; //atualizo a posição vertical
    }

    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }
}
