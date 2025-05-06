package loop;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

public class GWindow {
    private JFrame janela;

    public GWindow(GPanel gamePanel) {
        janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setUndecorated(true); // remove barra de título e bordas
        janela.add(gamePanel);
        janela.setLocationRelativeTo(null);
        janela.pack();

        // Pega a tela e aplica o modo fullscreen
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(janela);
        } else {
            System.err.println("Fullscreen não suportado. Usando maximizado.");
            janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
            janela.setVisible(true);
        }

        // Listener para quando a janela perde o foco
        janela.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                // Pode pausar o jogo ou silenciar o áudio
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                // Retomar o jogo, se necessário
            }
        });
    }
}