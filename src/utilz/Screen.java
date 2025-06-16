package utilz;

import background.Environment;
import background.Grass;
import background.Ground;
import gamestates.Gamestate;
import static gamestates.Gamestate.*;
import instances.Objects;
import instances.entities.Entities;
import instances.entities.Player1;
import instances.manager.Spawner;
import instances.obstacles.Bird;
import instances.obstacles.Obstacles;
import instances.obstacles.Saw;
import instances.obstacles.Wall;
import java.awt.Graphics2D;
import loop.GCanvas;
import java.util.LinkedList;
import java.util.Queue;

import ui.GameOver;
import ui.Menu;

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
    Spawner spawner;
    Ground groundlayer;
    Grass grasslayer;
    /*--- game states ---*/
    Menu menuscreen;
    GameOver gameoverscreen;
    //para debug
    
    /*------------ CONSTRUTOR ------------*/
    public Screen(GCanvas gc){
        this.gc = gc;
        menuscreen = new Menu();
        gameoverscreen = new GameOver();
        //grama 
        /*grasslayer = new Grass(this, this.gc);
        objectsOnScreen.add(grasslayer);*/
        
        player1 = new Player1(this, this.gc);
        objectsOnScreen.add(player1);
        
        for(int i = 0; i < 5; i++){ //3 por obstáculo, 9 no total. 
            objectsOnScreen.add(new Bird(this, this.gc));
            objectsOnScreen.add(new Wall(this, this.gc));
            objectsOnScreen.add(new Saw(this, this.gc));
        }
        
        
        //chão
        groundlayer = new Ground(this, this.gc);
        objectsOnScreen.add(groundlayer);
        
        spawner = new Spawner();
    }
    
    /*------------ MÉTODO RENDER ------------*/
    public void renderAll(Graphics2D g2d) {
        switch (Gamestate.state) {
            case MENU: {
                menuscreen.render(g2d);
                break;
            }
            case PLAYING_OFFLINE:{
                for (Objects obj : objectsOnScreen) {
                        if (obj instanceof Environment || (obj.getX() >= -Universal.TILES_SIZE * 4 && obj.getIsActive())) {
                            obj.render(g2d);
                        }    
                }
                break;
            }
            case PLAYING_ONLINE:{
                break;
            }
            case ABOUT:{
                break;
            }
            case GAME_OVER:{
                gameoverscreen.render(g2d);
                break;
            }
        }
    }
    
    /*------------ MÉTODO UPDATE ------------*/
    public void updateAll(double variacaoTempo) {
        switch(Gamestate.state){
            case MENU:{

            break;
            }
            case PLAYING_OFFLINE:{
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

                spawner.play();
                } else { //o player morreu
                    Universal.resetGameValues();
                    resetCoordenates();
                    Gamestate.state = GAME_OVER;
                    break;
                }
            }
            break;
            case GAME_OVER:{
                System.out.println("morreu");
                this.gc.sleepGame();
                break;
            }
            default:{
                break;
            }
            
        }
        
    }   
    
    /*------------ MÉTODO QUE RESETA AS COORDENADAS DAS INSTANCIAS NA TELA ------------*/
    public void resetCoordenates(){
        for (Objects obj : objectsOnScreen) {
                        if (obj instanceof Obstacles) {
                            obj.setIsActive(false);
                            obj.setX(Universal.OBST_SPAWN_X);
                        }    
                        if (obj instanceof Entities){
                            obj.setX(120);
                            obj.setY(360);
                        }
                }
    }
}
