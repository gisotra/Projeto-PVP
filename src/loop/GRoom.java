package loop;

import environment.BackgroundLayer;
import environment.ParallaxLayer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.Toolkit;
import java.util.ArrayList;
import objects.Player1;
import room.Ground;
import utilz.Spritesheet;

public class GRoom implements Runnable {
    
        /*------------ ATRIBUTOS ------------*/
	private GWindow gameWindow;
	private GPanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120; //120 FPS (frames por segundo) → ou seja, desenhar a tela 120 vezes a cada segundo pra ficar suave
	private final int UPS_SET = 200; //200 UPS (updates por segundo) → ou seja, atualizar as coisas do jogo (posição de personagens, lógica, etc) 200 vezes a cada segundo, pra ficar preciso.

        //instância do player
        private Player1 player1; //jogador 1
        
        //instância do chão
        private Ground ground;
        
        //camadas do cenário
        private List<ParallaxLayer> backLayers = new ArrayList<>();
        
        //tamanho da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        public int screenWidth = screenSize.width;
        public int screenHeight = screenSize.height;
        
        public final static int TILES_DEFAULT_SIZE = 32;
        public final static float SCALE = 1.5f;
        public final static int TILES_IN_WIDTH = 12;  //382px de COMPRIMENTO
        public final static int TILES_IN_HEIGHT = 7;  //215px ALTURA
        public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
        public static int GAME_WIDTH;
        public static int GAME_HEIGHT;
        
        /*------------ CONSTRUTOR ------------*/
	public GRoom() {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                GAME_WIDTH = screenSize.width;
                GAME_HEIGHT = screenSize.height;
            
            
		initClasses(); //instancia o player no jogo 
                gamePanel = new GPanel(this);
		gameWindow = new GWindow(gamePanel);
		gamePanel.requestFocus(); //permite inputs
                startGameLoop();
                
	}
        
        /*------------ INICIA AS CLASSES ------------*/
	private void initClasses() {
            ground = new Ground(0, GAME_HEIGHT - (6 * TILES_SIZE), GAME_WIDTH, TILES_SIZE * 6);
            player1 = new Player1(200, 200, ground); 
            loadParallaxBackground();

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
        }
        
        /*------------ MÉTODO RENDER ------------*/
        public void render(Graphics g){
        
                /*backLayers.get(0).render(g); // Céu
                backLayers.get(1).render(g); // Nuvens
                backLayers.get(2).render(g); // Cerca
                */
                player1.render(g);
                //backLayers.get(3).render(g); //grama
                ground.render(g);

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
                gamePanel.repaint();
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

    
    
        /*------------ MÉTODO LOADER DE SPRITES DAS CAMADAS ------------*/
    public void loadParallaxBackground(){
        backLayers.add(new BackgroundLayer(Spritesheet.GetSpriteAtlas(Spritesheet.LAYER_CEU), 0.1f));
        backLayers.add(new BackgroundLayer(Spritesheet.GetSpriteAtlas(Spritesheet.LAYER_NUVENS), 0.3f));
        backLayers.add(new BackgroundLayer(Spritesheet.GetSpriteAtlas(Spritesheet.LAYER_CERCA), 0.6f));
        backLayers.add(new BackgroundLayer(Spritesheet.GetSpriteAtlas(Spritesheet.LAYER_GRASS), 1.0f));
    }
    
    public Player1 getPlayer() {
        return player1;
    }
}

