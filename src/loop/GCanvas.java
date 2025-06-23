package loop;

import gamestates.Gamestate;
import static gamestates.Gamestate.*;
import utilz.Universal;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import utilz.Screen;
import utilz.SpriteData;
import utilz.SpriteLoader;
import utilz.Spritesheet;
import static utilz.Universal.SCALE;

public class GCanvas extends Canvas {

    /*------------ ATRIBUTOS ------------*/
    private GRoom room;
    Thread loop; 
    Font chickenFont;
    Font fontInGame;
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
        addMouseListener(new MouseInputs(this));
        try {
            InputStream is = getClass().getResourceAsStream("/assets/font/Chicken Font.ttf");
            chickenFont = Font.createFont(Font.TRUETYPE_FONT, is);
            fontInGame = chickenFont.deriveFont(Font.PLAIN, 25f); //aplico as mudanças de tamanho
        } catch (FontFormatException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        
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
            g2D.setColor(new Color(80, 48, 179));
            g2D.fillRect(0, 0, getWidth(), getHeight());
            if (Universal.showGrid) {
                drawGrid(g2D);
            }
            screen.renderAll(g2D);
            mousePoint = getMousePosition();
            if(mousePoint != null){
                spriteMouse.render(g2D, (int) mousePoint.getX(), (int)mousePoint.getY());
            }
            if(Gamestate.state == PLAYING_OFFLINE){
            g2D.setFont(fontInGame);
            g2D.setColor(Color.BLACK);
            g2D.drawString("SCORE:   " + String.valueOf(Universal.SCORE), Universal.GAME_WIDTH - 500, 40);
            }
            if(Gamestate.state == MULTIPLAYER_MENU){
            g2D.setFont(fontInGame);
            g2D.setColor(Color.WHITE);
            g2D.drawString("Criar Servidor", 4*Universal.TILES_SIZE - 60, 5*Universal.TILES_SIZE + 25);
            g2D.drawString("Jogar como Cliente", 8*Universal.TILES_SIZE + (Universal.TILES_SIZE/2) - 74, 5*Universal.TILES_SIZE + 25);
    
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
    
    public Screen getScreen() {
        return screen;
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
        Universal.OBST_SPEED = -1.8f * Universal.SCALE;
        this.room = new GRoom(this);
        this.loop = new Thread(room);
        this.loop.start();
    }
    
    public void sleepGame(){
        this.room.sleepEngine();
    }
}