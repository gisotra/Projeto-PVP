package loop;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import utilz.Universal;

public class KeyInputs implements KeyListener {

    private GCanvas gameCanvas;

    public KeyInputs(GCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Ignorar por enquanto
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                Universal.up = false; //alteração do método receber para GameCanvas 
                break;
            case KeyEvent.VK_A:
                Universal.left = false;
                break;
            case KeyEvent.VK_S:
                Universal.down = false;
                break; 
            case KeyEvent.VK_D:
                Universal.right = false;
                break;
            case KeyEvent.VK_SPACE:
                Universal.jump = false;
                break; 
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                Universal.up = true;
                break;
            case KeyEvent.VK_A:
                Universal.left = true;
                break;
            case KeyEvent.VK_S:
                Universal.down = true;
                break;
            case KeyEvent.VK_D:
                Universal.right = true;
                break;
            /*case KeyEvent.VK_O:
                gameCanvas.getGame().getPlayer2().spawnWall(
                        gameCanvas.getGame().getObst(),
                        gameCanvas.getGame().getGround()
                );
                break;*/
            case KeyEvent.VK_SPACE:
                Universal.jump = true;
                break;
        }
    }
}
