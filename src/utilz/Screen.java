package utilz;

import background.Environment;
import background.Grass;
import background.Ground;
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
    Ground groundlayer1;
    Ground groundlayer2;
    //Grass grasslayer;
    //para debug
    
    
    
    /*------------ CONSTRUTOR ------------*/
    public Screen(GCanvas gc){
        this.gc = gc;
        player1 = new Player1(this, this.gc);
        objectsOnScreen.add(player1);
        groundlayer1 = new Ground(this, this.gc);
        objectsOnScreen.add(groundlayer1);
        groundlayer2 = new Ground(this, this.gc);
        objectsOnScreen.add(groundlayer2);
        groundlayer2.setX(Universal.GAME_WIDTH);
        //grasslayer = new Grass(this, this.gc);
        //objectsOnScreen.add(grasslayer);
        player2 = new Player2();
        for(int i = 0; i < 3; i++){ //3 por obstáculo, 9 no total. 
            objectsOnScreen.add(new Bird(this, this.gc));
            objectsOnScreen.add(new Wall(this, this.gc));
            objectsOnScreen.add(new Saw(this, this.gc));
        }
    }
    
    /*------------ MÉTODO RENDER ------------*/
    public void renderAll(Graphics2D g2d) {
        
            for (Objects obj : objectsOnScreen) {
                if(obj.getX() >= 0 - Universal.TILES_SIZE && obj.getIsActive()){ //se estiver visível
                obj.render(g2d);
                }
                if(obj instanceof Environment){
                    obj.render(g2d);
                }
            }
    }
    
    /*------------ MÉTODO UPDATE ------------*/
    public void updateAll(double variacaoTempo) {
        if(!Universal.dead){ // se ele NÃO ESTIVER MORTO
            for (Objects obj : objectsOnScreen) {
                if (obj instanceof Environment) {
                    obj.update(variacaoTempo);
                    continue;
                }
                if(obj.getX() < 0 - Universal.TILES_SIZE*4){
                    obj.setIsActive(false);
                    continue;
                }
            
                if(obj.getX() >= 0 - Universal.TILES_SIZE*4 && obj.getIsActive()){ //se estiver visível E estiver ativo
                obj.update(variacaoTempo);
                }
            }
        player2.play();
        }
    }   
}
