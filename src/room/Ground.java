package room;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ground {
    private int x, y, gr_height, gr_width;
    private Rectangle hitbox;
    
    public Ground (int x, int y, int gr_width, int gr_height){
        this.x = x;
        this.y = y;
        this.gr_height = gr_height;
        this.gr_width = gr_width;
        
        hitbox = new Rectangle(x, y, gr_width, gr_height);
    }
    
    public void render(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(x, y, gr_width, gr_height);
    }
    
    public Rectangle getHitbox() {
        return hitbox;
    }
}
