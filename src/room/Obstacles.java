package room;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Obstacles { //muito similiar a classe Entities, porém direcionada unicamente aos obstáculos
    /*------------ ATRIBUTOS ------------*/
    protected float x, y;
    protected float speed; //ele vai sempre vir pra esquerda 
    protected int width, height;
    protected Rectangle2D.Float obs_hitbox;

    /*------------ CONSTRUTOR ------------*/
    public Obstacles(float x, float y, float speed, int width, int height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    protected void drawObstHitbox(Graphics g) {
        //para testar a hitbox
        g.setColor(Color.BLUE);
        g.drawRect((int) obs_hitbox.x, (int) obs_hitbox.y, (int) obs_hitbox.width, (int) obs_hitbox.height);
    }
    
    public void initObstHitbox(float x, float y, float width, float height) {
        obs_hitbox = new Rectangle2D.Float(x, y, width, height);
    }
    
    protected void updateObstHitbox(){
        obs_hitbox.x = (int)x; //atualizo a posição horizontal
        obs_hitbox.y = (int)y; //atualizo a posição vertical
    }

    public Rectangle2D.Float getObstHitbox(){
        return obs_hitbox;
    }
}

