package room;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ground {
    /*------------ ATRIBUTOS ------------*/
    private int x, y, gr_height, gr_width;
    private Rectangle gHitbox;
    
    /*------------ CONSTRUTOR ------------*/
    public Ground (int x, int y, int gr_width, int gr_height){
        this.x = x;
        this.y = y;
        this.gr_height = gr_height;
        this.gr_width = gr_width;
        gHitbox = new Rectangle(x, y, gr_width, gr_height);
    }
    
    /*------------ RENDER ------------*/
    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(x, y, gr_width, gr_height);
    }
    
    /*------------ RETORNA A HITBOX ------------*/
    public Rectangle getHitbox() {
        return gHitbox;
    }
    
    public float getY() {
        return y;
    }
}
