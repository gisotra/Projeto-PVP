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
import instances.obstacles.FallBlock;
import instances.obstacles.Obstacles;
import instances.obstacles.Saw;
import instances.obstacles.Wall;
import java.awt.Graphics2D;
import loop.GCanvas;
import java.util.LinkedList;
import java.util.Queue;

import ui.GameOver;
import ui.Menu;
import ui.MultiplayerMenu;

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
    /*--- obstáculos ---*/
    Ground groundlayer;
    Grass grasslayer;
    /*--- game states ---*/
    Menu menuscreen;
    GameOver gameoverscreen;
    MultiplayerMenu multmenuscreen;
    //para debug
    
    /*------------ CONSTRUTOR ------------*/
    public Screen(GCanvas gc){
        this.gc = gc;
        menuscreen = new Menu();
        gameoverscreen = new GameOver();
        multmenuscreen = new MultiplayerMenu();
        //grama 
        /*grasslayer = new Grass(this, this.gc);
        objectsOnScreen.add(grasslayer);*/
        
        
        
        //chão
        groundlayer = new Ground(this, this.gc);
        objectsOnScreen.add(groundlayer);
        
        
        player1 = new Player1(this, this.gc);
        objectsOnScreen.add(player1);
        spawner = new Spawner();
        for(int i = 0; i < 4; i++){ //3 por obstáculo, 9 no total. 
            objectsOnScreen.add(new Bird(this, this.gc));
            objectsOnScreen.add(new Wall(this, this.gc));
            objectsOnScreen.add(new Saw(this, this.gc));
            objectsOnScreen.add(new FallBlock(this, this.gc));
        }
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
            case MULTIPLAYER_MENU: {
                multmenuscreen.render(g2d);
                break;
            }
            case PLAYING_ONLINE:{
                break;
            }
            case ABOUT:{
                break;
            }
            case GAME_OVER:{
                for (Objects obj : objectsOnScreen) {
                    if (obj instanceof Environment || (obj.getX() >= -Universal.TILES_SIZE * 4 && !obj.getIsActive())) {
                        obj.render(g2d);
                    }
                }
                gameoverscreen.render(g2d);
                break;
            }
            case END:{
                System.exit(0);
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
                {/*if (!Universal.dead) {
                    //meu player está VIVO
                    for (Objects obj : objectsOnScreen) {
                        if (obj instanceof Entities) {
                            obj.setIsActive(true);
                        }
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
                } else { 
                    //meu player está MORTO
                    resetCoordenates();
                    break;
                }
            */}
                for(Objects obj : objectsOnScreen){
                    if(!obj.getIsActive()){
                        continue; //se estiver desativado, nada acontece, nao é atualizado
                    }
                    if (obj instanceof Environment) {
                        obj.update(variacaoTempo);
                        continue;
                    }
                    if (obj.getX() < -Universal.TILES_SIZE * 4) {
                        obj.setIsActive(false);
                        continue;
                    }
                    obj.update(variacaoTempo);
                }
                spawner.play();
                if(Universal.dead){
                    
                    /*for (Objects obj : objectsOnScreen){
                        if(obj instanceof Entities){
                            Universal.jump = true;
                        }
                    }*/
                    
                    for (Objects obj : objectsOnScreen){
                        if(obj instanceof Entities && obj.getY() > Universal.GAME_HEIGHT){
                            Gamestate.state = GAME_OVER;
                        }
                    }
                    break;
                }
            }break;
            case GAME_OVER:{
                for (Objects obj : objectsOnScreen) {
                        obj.setIsActive(false);
                }
                System.out.println("Status: GAME OVER");
                break;
            }
            default:{
                break;
            }
            
        }
        
    }   
    
    /*------------ MÉTODO QUE RESETA AS COORDENADAS DAS INSTANCIAS NA TELA ------------*/
    public static void startCoordenates(){
        for (Objects obj : objectsOnScreen) {
            if (obj instanceof Entities) {
                obj.setIsActive(true);
                obj.setX(120);
                obj.setY(360);
                ((Player1)obj).movement.isJumping = true;
            }
            if(obj instanceof Environment){
                obj.setIsActive(true);
            }
            
            if(obj instanceof Obstacles){
                ((Obstacles)obj).updateObstHitbox();
            }
        }
        Universal.dead = false;
    }
    
    public static void resetCoordenates(){
        for (Objects obj : objectsOnScreen) {
                obj.setIsActive(false);
                if(obj instanceof Obstacles){
                    obj.setX(Universal.OBST_SPAWN_X);
                }
        }
        Universal.resetGameValues();
    }
    
    /*------------- GETTERS E SETTERS QUE FORAM NECESSARIOS NA JORNADA -------------*/
    public Menu getMenu(){
        return menuscreen;
    }
    
    public GameOver getGameOver(){
        return gameoverscreen;
    }

    public MultiplayerMenu getMultMenu(){
        return multmenuscreen;
    }
    
}
