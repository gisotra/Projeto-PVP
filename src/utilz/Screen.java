package utilz;

import background.Environment;
import background.Grass;
import background.Ground;
import background.Trees1;
import background.Trees2;
import instances.Objects;
import instances.entities.Player1;
import instances.manager.Player2;
import instances.obstacles.Bird;
import instances.obstacles.Saw;
import instances.obstacles.Wall;
import java.awt.Graphics2D;
import loop.GCanvas;
import java.util.LinkedList;
import java.util.Queue;

public class Screen { 
    /*
    Essa vai ser a classe em que eu vou manipular e desenhar todos os elementos componentes da 
    minha tela de 1 vez dentro de 1 único método, isso será feito a partir da implementação de 
    um LinkedList do tipo da superclasse abstrata "Objects", e através do polimorfismo, 
    vou chamar o método update e o render de cada componente desse list.
    
    Porque uma linkedList e não um ArrayList normal? Porque eu vou reciclar os obstáculos gerados, deixando 
    o jogo com um desempenho mais leve e amigável com a CPU.
    */
    /*------------ ATRIBUTOS ------------*/
    public GCanvas gc;
    public static Queue<Objects> objectsOnScreen = new LinkedList<>(); //vou usar pra dar update e render no player e nos obstaculos simultaneamente (mto amigavel com a cpu)
    
    Player1 player1;
    Player2 player2;
    Ground groundlayer;
    Grass grasslayer;
    //para debug
    
    /*------------ CONSTRUTOR ------------*/
    public Screen(GCanvas gc){
        this.gc = gc;
        //grama
        grasslayer = new Grass(this, this.gc);
        objectsOnScreen.add(grasslayer);
        
        player1 = new Player1(this, this.gc);
        objectsOnScreen.add(player1);
        
        for(int i = 0; i < 4; i++){ //3 por obstáculo, 9 no total. 
            objectsOnScreen.add(new Bird(this, this.gc));
            objectsOnScreen.add(new Wall(this, this.gc));
            objectsOnScreen.add(new Saw(this, this.gc));
        }

        
        //chão
        groundlayer = new Ground(this, this.gc);
        objectsOnScreen.add(groundlayer);
        
        player2 = new Player2();
    }
    
    /*------------ MÉTODO RENDER ------------*/
    public void renderAll(Graphics2D g2d) {
        
            for (Objects obj : objectsOnScreen) {
            if (obj instanceof Environment || (obj.getX() >= -Universal.TILES_SIZE && obj.getIsActive())) {
                obj.render(g2d);
            }
        }
    }
    
    /*------------ MÉTODO UPDATE ------------*/
    public void updateAll(double variacaoTempo) {
        if (!Universal.dead) {
            for (Objects obj : objectsOnScreen) {
                if (!obj.getIsActive()) {
                    continue;
                }

                // Atualiza fundo sempre
                if (obj instanceof Environment) {
                    obj.update(variacaoTempo);
                    continue;
                }

                // Se saiu completamente da tela, desativa
                if (obj.getX() < -Universal.TILES_SIZE * 4) {
                    obj.setIsActive(false);
                    continue;
                }

                obj.update(variacaoTempo);
            }

            player2.play();
        }
    }   
}
