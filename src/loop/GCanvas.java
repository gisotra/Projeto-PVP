package loop;

import utilz.Universal;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import utilz.Screen;
import utilz.SpriteData;
import utilz.SpriteLoader;
import utilz.Spritesheet;

public class GCanvas extends Canvas {

    /*------------ ATRIBUTOS ------------*/
    private GRoom room;
    Thread loop; 
    public Screen screen = new Screen(this);
    Point mousePoint;
    Cursor cursor;
    BufferedImage cursorMouse;
    Spritesheet spriteMouse;
    

    /*------------ CONSTRUTOR ------------*/
    public GCanvas() {
        setPreferredSize(new Dimension(Universal.GAME_WIDTH, Universal.GAME_HEIGHT));
        setFocusable(true);
        requestFocus();
        initMouseSprites();
        addKeyListener(new KeyInputs(this)); 
    }

    /*------------ O LENDÁRIO MÉTODO RENDER ------------*/
    public void render() {
        //instancia o que é necessário para desenhar 
        BufferStrategy bs = getBufferStrategy(); 
        if (bs == null) {
            return; // ainda não foi criado
        }
        Graphics2D g2D = (Graphics2D) bs.getDrawGraphics();
        try {
            // Limpa o fundo para evitar artefatos de frames anteriores
            g2D.setColor(Color.WHITE);
            g2D.fillRect(0, 0, getWidth(), getHeight());
            if (Universal.showGrid) {
                drawGrid(g2D);
            }
            screen.renderAll(g2D);
            mousePoint = getMousePosition();
            if(mousePoint != null){
                spriteMouse.render(g2D, (int) mousePoint.getX(), (int)mousePoint.getY());
            }
            
        } finally {
            // Garante que o objeto Graphics será liberado mesmo que dê erro
            g2D.dispose();
        }

        // Exibe o buffer completo na tela
        bs.show();

        // Sincroniza a atualização gráfica com o hardware para reduzir tearing
        Toolkit.getDefaultToolkit().sync();
    }
    
    /*------------ MÉTODO QUE RETORNA A SALA PARA USO DO KEYINPUTS ------------*/
    public GRoom getGame() {
        return room;
    }
    
    public void update(double dT) {
        screen.updateAll(dT);
    }
    
    public void initCanvas() {
        createBufferStrategy(3); // você pode usar 2 ou 3 buffers
    }
    
    public void drawGrid(Graphics2D g2D) {
        g2D.setColor(Color.LIGHT_GRAY); // Cor preta para o grid
        for (int x = 0; x < Universal.GAME_WIDTH; x += Universal.TILES_SIZE) {
            for (int y = 0; y < Universal.GAME_HEIGHT; y += Universal.TILES_SIZE) {
                // Desenha o contorno de cada tile
                g2D.drawRect(x, y, Universal.TILES_SIZE, Universal.TILES_SIZE);
            }
        }
        
    }
    
    //mudar o sprite do meu cursor
    public void initMouseSprites(){
        
        SpriteData mouseData = SpriteLoader.spriteDataLoader().get("cursor");
        try {
            cursorMouse = ImageIO.read(getClass().getResource(mouseData.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //inicio as propriedades do meu sprite player
        this.spriteMouse = new Spritesheet(cursorMouse, 32, 32, 0.85, Universal.SCALE);
        
        cursor = Toolkit.getDefaultToolkit().createCustomCursor(
        new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor");
        setCursor(cursor);

    }
    
    
    public void initGame(){
        this.room = new GRoom(this);
        this.loop = new Thread(room);
        this.loop.start();
    }
}