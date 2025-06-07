package loop;

import javax.swing.JFrame;

public class GWindow {
    
    private JFrame janela;
    private GCanvas gc;

    public GWindow() {
        gc = new GCanvas();
        janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setUndecorated(true);
        janela.add(gc);
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        gc.initCanvas();
    }

    public JFrame getJanela() {
        return janela;
    }
}