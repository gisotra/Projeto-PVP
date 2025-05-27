package loop;

import utilz.Universal;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import instances.entities.Player1;
import instances.entities.Player2;
import instances.obstacles.Wall;
import room.Ground;
import room.Obstacles;
import utilz.Spritesheet;

public class GRoom implements Runnable {
    
        /*------------ ATRIBUTOS ------------*/
	private GWindow gameWindow;
	private GCanvas gameCanvas;
        private BufferStrategy bufferStrategy;
	private Thread gameThread;
	private final int FPS_SET = 60; //60 FPS (frames por segundo) → ou seja, desenhar a tela 120 vezes a cada segundo pra ficar suave
        //instância do player 1
        private Player1 player1; //jogador 1
        
        //instância do player 2
        private Player2 player2;
        
        //instância do chão
        private Ground ground;
        
        //instância do tipo colisor
        /*logica: Crio um arraylist que vai ser incrementado até que o player morra, ou seja, até o jogo acabar
        Isso vai ser feito através da biblioteca random*/
        private List<Obstacles> obst;
        private Random r = new Random();
       
        
        //tamanho da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        public int screenWidth = screenSize.width;
        public int screenHeight = screenSize.height;
        
        /*public final static int TILES_DEFAULT_SIZE = 32;
        public final static float SCALE = 1.5f;
        public final static int TILES_IN_WIDTH = 12;  //382px de COMPRIMENTO
        public final static int TILES_IN_HEIGHT = 7;  //215px ALTURA
        public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
        public static int GAME_WIDTH;
        public static int GAME_HEIGHT;*/
        
        /*------------ CONSTRUTOR ------------*/
	public GRoom() {
                r = new Random(); //inicio a minha constante aleatória
                //nextSpawn = getRandomCooldown();
		
                initClasses(); //instancia o player no jogo 
                player2.setNextSpawn(getRandomCooldown());
                
            gameCanvas = new GCanvas(this);
            gameWindow = new GWindow(gameCanvas);
            
            gameWindow.showWindow();

            try {
                Thread.sleep(50); // 50ms, só pra garantir que o Canvas já esteja no display
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            
            // Cria triple buffering (recomendado para renderização suave)
            gameCanvas.createBufferStrategy(3);
            bufferStrategy = gameCanvas.getBufferStrategy();

            // Garante foco para receber os inputs
            gameCanvas.requestFocus();

            // Inicia o loop do jogo
            startGameLoop();
                
	}
        
        /*------------ INICIA AS CLASSES ------------*/
	private void initClasses() {
            ground = new Ground(0, Universal.GAME_HEIGHT - (2 * Universal.TILES_SIZE), Universal.GAME_WIDTH, 10);
            player1 = new Player1(200, 200, ground); 
            player2 = new Player2();
            obst = new ArrayList<>(); //inicio o meu arraylist
            //loadParallaxBackground();

        }
        
        /*------------ INICIA A THREAD ------------*/
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
        
        /*------------ MÉTODO UPDATE ------------*/
        public void update(){
            player1.update();
            float baseSpeed = 3.5f; // ajuste conforme a velocidade do seu jogo

            
            // Atualiza os obstáculos
            for (int i = 0; i < obst.size(); i++) { 
                Obstacles o = obst.get(i); 
                if (o instanceof Wall) { // boolean
                    Wall wall = (Wall) o; //pego minha instancia "o" (do tipo obstacle) e converto em wall
                    wall.update(); //dou update

                    // Remove da lista se sair da tela
                    if (wall.isOffScreen()) {
                        obst.remove(i);
                        i--;
                    }
                }
            }
        }
        
        /*------------ MÉTODO RENDER ------------*/
        public void render(Graphics g){
            /*chamo TODOS os renders de TODOS os componentes do meu jogo e isso 
            será desenhado tudo de uma vez no meu Canvas, dessa forma, melhorando 
            MUITO a performance
            */
                /*backLayers.get(0).render(g); // Céu
                backLayers.get(1).render(g); // Nuvens
                backLayers.get(2).render(g); // Cerca
                */
                player1.render(g);
                //backLayers.get(3).render(g); //grama
                ground.render(g);
                // Renderiza os obstáculos
            for (Obstacles o : obst) { //for each
                if (o instanceof Wall) { //verifico se é uma instancia wall
                    ((Wall) o).render(g); //caso afirmativo, renderizo ele com o render da classe Wall
                    // ((Wall)o).drawObstHitbox(g); // se quiser testar hitbox
                }
            }

        }
        
        
        /*------------ MÉTODO RUN DA THREAD ------------*/
    @Override
    public void run() {
        final double tempoPorFrame = 1_000_000_000.0 / FPS_SET;
        long ultimoTempo = System.nanoTime();
        double delta = 0;

        long timer = System.currentTimeMillis();
        int frames = 0;

        while (true) {
            long agora = System.nanoTime();
            delta += (agora - ultimoTempo) / tempoPorFrame;
            ultimoTempo = agora;

            if (delta >= 1) {
                update();
                gameCanvas.render(bufferStrategy);
                frames++;
                delta--;
            }

            // Exibe o FPS a cada segundo
            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }

            // Dorme um pouco pra não travar a CPU
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    
    /*------------ TEMPO ALEATÓRIO PARA SPAWNAR OS OBJETOS ------------*/
    public int getRandomCooldown(){
        return 1000 + r.nextInt(500); 
    }
    
    
    public Player1 getPlayer() {
        return player1;
    }
    
    public Player2 getPlayer2() {
        return player2;
    }
    
    public Ground getGround() {
        return ground;
    }

    public List<Obstacles> getObst() {
        return obst;
    }
}

