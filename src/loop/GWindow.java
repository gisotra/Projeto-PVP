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
        janela.setLocationRelativeTo(null);
        janela.pack();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(janela);
        } else {
            System.err.println("Fullscreen não suportado. Usando maximizado.");
            janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
            janela.setVisible(true);
        }

        janela.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                
            }
        });
    }
}