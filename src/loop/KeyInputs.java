package loop;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
                gameCanvas.getGame().getPlayer().setUp(false); //alteração do método receber para GameCanvas 
                break;
            case KeyEvent.VK_A:
                gameCanvas.getGame().getPlayer().setLeft(false); //alteração do método receber para GameCanvas 
                break;
            case KeyEvent.VK_S:
                gameCanvas.getGame().getPlayer().setDown(false); //alteração do método receber para GameCanvas 
                break; 
            case KeyEvent.VK_D:
                gameCanvas.getGame().getPlayer().setRight(false); //alteração do método receber para GameCanvas 
                break;
            case KeyEvent.VK_SPACE:
                gameCanvas.getGame().getPlayer().setJump(false); //alteração do método receber para GameCanvas 
                break; 
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gameCanvas.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_A:
                gameCanvas.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                gameCanvas.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                gameCanvas.getGame().getPlayer().setRight(true);
                break;
            /*case KeyEvent.VK_U:
                gameCanvas.getGame().getPlayer2().spawnWall(
                        gameCanvas.getGame().getObst(),
                        gameCanvas.getGame().getGround()
                );
                break;*/
            /*case KeyEvent.VK_I:
                gameCanvas.getGame().getPlayer2().spawnWall(
                        gameCanvas.getGame().getObst(),
                        gameCanvas.getGame().getGround()
                );
                break;*/
            case KeyEvent.VK_O:
                gameCanvas.getGame().getPlayer2().spawnWall(
                        gameCanvas.getGame().getObst(),
                        gameCanvas.getGame().getGround()
                );
                break;
            case KeyEvent.VK_SPACE:
                gameCanvas.getGame().getPlayer().setJump(true);
                break;
        }
    }
}
