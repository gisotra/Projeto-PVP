package loop;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import utilz.Universal;

public class GWindow {
    
    private JFrame janela;
    private GCanvas gc;
    private JProgressBar bar; 

    public GWindow() {
        gc = new GCanvas(); // Inicia a thread
        janela = new JFrame();
        bar = new JProgressBar(0, 100);
        
        bar.setBounds((Universal.GAME_WIDTH / 2) - Universal.TILES_SIZE, (Universal.GAME_HEIGHT - Universal.TILES_SIZE), Universal.TILES_SIZE * 2, 34);
        janela.add(bar);
        
        
        // Configurações da janela antes de exibi-la
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setUndecorated(true);  // Defina isso antes de setVisible(true)
        
        janela.add(gc);

        janela.pack();  // Ajusta o layout
        janela.setLocationRelativeTo(null);  // Centraliza a janela
        
        gc.initCanvas();  // Inicializa o canvas
        gc.initGame();  // Inicializa o jogo
        
        janela.setVisible(true);  // Por fim, torna a janela visível
    }

    public JFrame getJanela() {
        return janela;
    }
}