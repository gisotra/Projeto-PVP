package instances.obstacles;

import instances.Objects;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import loop.GCanvas;
import utilz.Screen;
import utilz.Spritesheet;
import utilz.Universal;

public abstract class Obstacles extends Objects{ //muito similiar a classe Entities, porém direcionada unicamente aos obstáculos
    /*------------ ATRIBUTOS ------------*/
    protected float speed =  Universal.OBST_SPEED; //ele vai sempre vir pra esquerda 
    protected Rectangle2D.Float obs_hitbox; //desenvolver na criação dos objetos wall, saw, bird, etc

    /*------------ CONSTRUTOR ------------*/
    public Obstacles(Screen screen, GCanvas gc) {
        super(screen, gc);
    }

    /*------------ MÉTODOS HERDADOS ------------*/
    protected abstract void drawObstHitbox(Graphics2D g2d); // método para debug
    {/*
    protected abstract void drawObstHitbox(Graphics g) {
        //para testar a hitbox
        g.setColor(Color.BLUE);
        g.drawRect((int) obs_hitbox.x, (int) obs_hitbox.y, (int) obs_hitbox.width, (int) obs_hitbox.height);
    }        
    
    */
    }
    
    /*transformar esse método em um abstrato, e modificar ele pra cada classe subsequente*/
    protected abstract void initObstHitbox();
    
    {/* versão desenvolvida do initObst
        public void initObstHitbox(float x, float y, float width, float height) {
        obs_hitbox = new Rectangle2D.Float(x, y, width, height);
    }
    */}
    
    protected void updateObstHitbox(){ //pode tirar e definir no obstacles
        obs_hitbox.x = (int)getX(); //atualizo a posição horizontal
    }
    
    public abstract void setSpritesheet(BufferedImage spritesheet, float renderScale);
    
    @Override
    public void update(double deltaTime) {
        this.setX(this.getX() + speed);
        updateObstHitbox();
    }
    
    @Override
    public void render(Graphics2D g2d){
    spritesheet.render(g2d, (int) getX(), (int) getY()); 
    drawObstHitbox(g2d);
    }
    
    public Rectangle2D.Float getObstHitbox(){
        return obs_hitbox;
    }
}

