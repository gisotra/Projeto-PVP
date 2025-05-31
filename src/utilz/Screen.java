package utilz;

import instances.Objects;
import instances.entities.Player1;
import instances.obstacles.Bird;
import instances.obstacles.Wall;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import loop.GCanvas;

public class Screen { 
    /*
    Essa vai ser a classe em que eu vou manipular e desenhar todos os elementos componentes da 
    minha tela de 1 vez dentro de 1 único método, isso será feito a partir da implementação de 
    um arraylist do tipo da superclasse abstrata "Objects", e através do polimorfismo, 
    vou chamar o método update e o render de cada componente desse list
    */
    /*------------ ATRIBUTOS ------------*/
    public GCanvas gc;
    List<Objects> objects = new ArrayList<Objects>(); //vou usar pra dar update e render no player e nos obstaculos simultaneamente (mto amigavel com a cpu)
    Player1 player1;
    //para debug
    Bird bird;
    Wall wall;
    
    /*------------ CONSTRUTOR ------------*/
    public Screen(GCanvas gc){
        this.gc = gc;
        player1 = new Player1(this, this.gc);
        bird = new Bird(this, this.gc);
        wall = new Wall(this, this.gc);
        objects.add(player1);
        objects.add(bird);
        objects.add(wall);
    }
    
    /*------------ MÉTODO RENDER ------------*/
    public void renderAll(Graphics2D g2d) {
        for (Objects obj : objects) {
            obj.render(g2d);
        }
    }
    
    /*------------ MÉTODO UPDATE ------------*/
    public void updateAll(double variacaoTempo) {
        for (Objects obj : objects) {
            obj.update(variacaoTempo);
        }
    }
    
    
}
