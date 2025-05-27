package instances.obstacles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import room.Obstacles;

public class Wall extends Obstacles{
    /*------------ ATRIBUTOS ------------*/    
    private BufferedImage[][] obstSpritesheet;
    
    /*------------ CONSTRUTOR ------------*/
    public Wall(float x, float y, float speed, int width, int height) {
        super(x, y, speed, width, height);
        initObstHitbox(x, y, width, height);
    }
    
    /*------------ RENDER ------------*/
    public void render(Graphics g){
        //por hora vai ser somente um retângulo cinza
        g.setColor(Color.GRAY);
        g.fillRect( (int)x, (int)y, width, height);
    }
    
    /*------------ UPDATE ------------*/
    public void update(){
        x += speed;
        updateObstHitbox();   
    }
    
    /*------------ BOOLEAN ESTÁ FORA DA TELA ------------*/
        public boolean isOffScreen() {
        return x + width < 0;
    }
}
