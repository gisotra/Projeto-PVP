package loop;

import utilz.Universal;
import environment.BackgroundLayer;
import environment.ParallaxLayer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import objects.Player1;
import objects.Player2;
import objects.Wall;
import room.Ground;
import room.Obstacles;
import utilz.Spritesheet;

public class GRoom implements Runnable {
    
        /*------------ ATRIBUTOS ------------*/
	private GWindow gameWindow;
	private GCanvas gameCanvas;
        private BufferStrategy bufferStrategy;
	private Thread gameThread;
	private final int FPS_SET = 120; //120 FPS (frames por segundo) → ou seja, desenhar a tela 120 vezes a cada segundo pra ficar suave
	private final int UPS_SET = 200; //200 UPS (updates por segundo) → ou seja, atualizar as coisas do jogo (posição de personagens, lógica, etc) 200 vezes a cada segundo, pra ficar preciso.

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
       
        
        //camadas do cenário
        private List<ParallaxLayer> backLayers = new ArrayList<>();
        
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
            ground = new Ground(0, Universal.GAME_HEIGHT - (2 * Universal.TILES_SIZE), Universal.GAME_WIDTH, Universal.TILES_SIZE * 2);
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
            for (ParallaxLayer layer : backLayers) {
                layer.update(baseSpeed);
            }
            
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
        {
            /*
                O loop while (true) calcula o tempo decorrido entre cada iteração usando System.nanoTime():

                deltaU (delta Update) acumula o tempo para saber se já é hora de fazer uma atualização lógica.

                deltaF (delta Frame) acumula o tempo para saber se já é hora de redesenhar a tela.

                Se deltaU >= 1, chama-se update(). Se deltaF >= 1, chama-se repaint().

                Além disso, o loop controla e imprime o número real de FPS e UPS alcançados a cada segundo, para monitoramento de performance.
             */
        }
        double timePerFrame = 1000000000.0 / FPS_SET;  //intervalo de tempo ideal entre frames.
        double timePerUpdate = 1000000000.0 / UPS_SET; //intervalo de tempo ideal entre atualizações.

        long previousTime = System.nanoTime(); //armazena o tempo da última verificação para calcular o delta atual.

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0; //acumula o tempo para saber se já é hora de fazer uma atualização lógica.
        double deltaF = 0; //acumula o tempo para saber se já é hora de redesenhar a tela.

        while (true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) { // Se deltaU >= 1, chama-se update(). 
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) { //Se deltaF >= 1, chama-se repaint().
                if (bufferStrategy == null) {
                    gameCanvas.createBufferStrategy(3);
                    bufferStrategy = gameCanvas.getBufferStrategy();
                }
                gameCanvas.render(bufferStrategy);
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }

    /**/
    /*public void spawnWall() {
        int wallWidth = 70;
        int wallHeight = 120;
        float wallSpeed = -3.0f;

        // Cria o muro no lado direito da tela (fora da tela) e na altura do chão
        float x = GAME_WIDTH;
        float y = ground.getY() - wallHeight;

        Wall wall = new Wall(x, y, wallSpeed, wallWidth, wallHeight); //inithitbox no proprio construtor Wall
        obst.add(wall); 
    }*/
    
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

