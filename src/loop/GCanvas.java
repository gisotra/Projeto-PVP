package loop;

import utilz.Universal;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GCanvas extends Canvas {

    /*------------ ATRIBUTOS ------------*/
    private GRoom room;

    /*------------ CONSTRUTOR ------------*/
    public GCanvas(GRoom room) {
        this.room = room;
        setPreferredSize(new Dimension(Universal.GAME_WIDTH, Universal.GAME_HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(new KeyInputs(this)); 
    }

    /*------------ O LENDÁRIO MÉTODO RENDER ------------*/
    public void render(BufferStrategy bs) {
        do {
            do {
                Graphics2D g2d = (Graphics2D) bs.getDrawGraphics(); //pincel muito melhor que o Graphics convencional
                try {
                    // Limpa o fundo da tela pra que não tenha "fantasmas" dos sprites
                    g2d.setColor(Color.WHITE); 
                    g2d.fillRect(0, 0, Universal.GAME_WIDTH, Universal.GAME_HEIGHT);

                    // Desenha todo conteúdo do jogo de uma vez só
                    room.render(g2d); 
                } finally {
                    g2d.dispose(); //libero tudo no meu canvas
                }
            } while (bs.contentsRestored());
            bs.show(); //mostro
        } while (bs.contentsLost());
    }
    
    /*------------ MÉTODO QUE RETORNA A SALA PARA USO DO KEYINPUTS ------------*/
    public GRoom getGame() {
        return room;
    }
}