package loop;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

public class GWindow {
    
    private JFrame janela;
    

    public GWindow(Canvas gameCanvas) {
        janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setUndecorated(true);
        janela.add(gameCanvas);
        janela.pack();
        janela.setLocationRelativeTo(null);        
    }
    
    //Criei um método separado para ele tornar a tela visível somente DEPOIS
    public void showWindow() {
        janela.setVisible(true);
    }

    public JFrame getJanela() {
        return janela;
    }
}