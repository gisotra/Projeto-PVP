package instances.obstacles;

import java.awt.geom.Rectangle2D;
import loop.GCanvas;
import utilz.Screen;

public class Bird { //extends Obstacles{
    /*------------ ATRIBUTOS ------------*/
    public float speedBird; //ele vai sempre vir pra esquerda 
    public Rectangle2D.Float obs_hitbox; //desenvolver na criação dos objetos wall, saw, bird, etc

    /*------------ CONSTRUTOR ------------*/
    public Bird(Screen screen, GCanvas gc) {
        //super(screen, gc);
        //width = Xpx; a ser definido
        //height = Xpx; a ser definido
    }
}
