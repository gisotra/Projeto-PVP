package instances.obstacles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import loop.GCanvas;
import utilz.Screen;
import utilz.Universal;

public class Bird extends Obstacles{ //extends Obstacles{
    /*------------ ATRIBUTOS ------------*/
    BufferedImage birdSpriteSheet;
    /*------------ CONSTRUTOR ------------*/
    public Bird(Screen screen, GCanvas gc) {
        super(screen, gc);
        setY(Universal.BIRD_SPAWN_Y);
        setX(Universal.OBST_SPAWN_X); //fora da tela na direita
        initSprite();
        initObstHitbox();
    }
    
    public void initSprite() {
        try {
            birdSpriteSheet = ImageIO.read(getClass().getResourceAsStream("/assets/player/playerAtualizado4.png")); //ainda nao tenho sprites :(
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //altura e largura do meu passarinho
        setWidth(32);
        setHeight(32);
        setSpritesheet(birdSpriteSheet, Universal.SCALE);
    }
    
    @Override
    protected void drawObstHitbox(Graphics2D g2d) {
        // nao vou implementar agora
    }
    
    @Override
    public void initObstHitbox() {
        this.obs_hitbox = new Rectangle2D.Float(getX(), getY(), getWidth(), (getHeight() / 2)); //metade do tamanho
    }
    
    @Override
    public void update(double deltaTime){
        this.setX(this.getX() + speed);
        updateObstHitbox();
    }
    
    @Override
    public void render(Graphics2D g2d){
    
        //para teste
        g2d.setColor(Color.GRAY);


        g2d.fillRect( (int)x, (int)y, width, height);
    //sprite.render(g2d, (int) getX(), (int) getY()); vou usar esse
    }
    
    @Override
    protected void updateObstHitbox(){
        obs_hitbox.x = (int)getX(); //atualizo a posição horizontal
    }
}
