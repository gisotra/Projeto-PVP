package loop;

import javax.swing.JFrame;

public class GWindow {
    
    private JFrame janela;
    private GCanvas gc;

    public GWindow() {
        gc = new GCanvas(); // Inicia a thread
        janela = new JFrame();
        
        // Configurações da janela antes de exibi-la
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
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