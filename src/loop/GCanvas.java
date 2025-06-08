package loop;

import utilz.Universal;
import java.awt.*;
import java.awt.image.BufferStrategy;
import utilz.Screen;

public class GCanvas extends Canvas {

    /*------------ ATRIBUTOS ------------*/
    private GRoom room;
    Thread loop; 
    public Screen screen = new Screen(this);

    /*------------ CONSTRUTOR ------------*/
    public GCanvas() {
        setPreferredSize(new Dimension(Universal.GAME_WIDTH, Universal.GAME_HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(new KeyInputs(this)); 
    }

    /*------------ O LENDÁRIO MÉTODO RENDER ------------*/
    public void render() {
        //instancia o que é necessário para desenhar 
        BufferStrategy bs = getBufferStrategy(); 
        if (bs == null) {
            return; // ainda não foi criado
        }
        Graphics2D g2D = (Graphics2D) bs.getDrawGraphics();
        try {
            // Limpa o fundo para evitar artefatos de frames anteriores
            g2D.setColor(Color.WHITE);
            g2D.fillRect(0, 0, getWidth(), getHeight());
            if (Universal.showGrid) {
                drawGrid(g2D);
            }
            screen.renderAll(g2D);
        } finally {
            // Garante que o objeto Graphics será liberado mesmo que dê erro
            g2D.dispose();
        }

        // Exibe o buffer completo na tela
        bs.show();

        // Sincroniza a atualização gráfica com o hardware para reduzir tearing
        Toolkit.getDefaultToolkit().sync();
    }
    
    /*------------ MÉTODO QUE RETORNA A SALA PARA USO DO KEYINPUTS ------------*/
    public GRoom getGame() {
        return room;
    }
    
    public void update(double dT) {
        screen.updateAll(dT);
    }
    
    public void initCanvas() {
        createBufferStrategy(3); // você pode usar 2 ou 3 buffers
    }
    
    public void drawGrid(Graphics2D g2D) {
        g2D.setColor(Color.LIGHT_GRAY); // Cor preta para o grid
        for (int x = 0; x < Universal.GAME_WIDTH; x += Universal.TILES_SIZE) {
            for (int y = 0; y < Universal.GAME_HEIGHT; y += Universal.TILES_SIZE) {
                // Desenha o contorno de cada tile
                g2D.drawRect(x, y, Universal.TILES_SIZE, Universal.TILES_SIZE);
            }
        }
        
    }
    
    public void initGame(){
        this.room = new GRoom(this);
        this.loop = new Thread(room);
        this.loop.start();
    }
}