package gameloop;

import javax.swing.*;
import java.awt.*;

public class GPanel extends JPanel{
    
    public int originalTileSize = 32;
    public int escala = 4;
    public int tileSize = originalTileSize * escala;

    int colunas = 16;
    int linhas = 9;
    public int comprimentoTela;
    public int alturaTela;

    public final double FPS = 240.0;

    public GPanel() {
        // Pega a resolução da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        alturaTela = screenSize.width;
        comprimentoTela = screenSize.height;

        setPreferredSize(new Dimension(alturaTela, comprimentoTela));
        setBackground(Color.black);
        setFocusable(true);
    }

/*    @Override
    public void paint (Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
    }*/
}
