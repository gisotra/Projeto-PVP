package loop;

import static loop.GRoom.GAME_HEIGHT;
import static loop.GRoom.GAME_WIDTH;
import javax.swing.*;
import java.awt.*;

public class GPanel extends JPanel{
    
    /*------------ ATRIBUTOS ------------*/
    private GRoom room;
    
    /*------------ CONSTRUTOR ------------*/
    public GPanel(GRoom room) {
        this.room = room;
        setPanelSize(); //define o tamanho do painel 
        addKeyListener(new KeyInputs(this)); //adiciona o keyboard listener

    }

    /*------------ TAMANHO DA JANELA ------------*/
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);

        setPreferredSize(size);
        System.out.println("size : " + GAME_WIDTH + " : " + GAME_HEIGHT);

    }

    /*------------ UPDATE DOS DADOS DO JOGO ------------*/
    public void updateGame() {

    }

    /*------------ PINTAR OS SPRITES ------------*/
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        room.render(g);

    }

    public GRoom getGame() {
        return room;
    }
}
