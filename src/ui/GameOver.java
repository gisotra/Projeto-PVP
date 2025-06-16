package ui;

import java.awt.Button;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import utilz.SpriteData;
import utilz.SpriteLoader;
import utilz.Spritesheet;
import utilz.Universal;

public class GameOver {
    /*Imagens do fundo da tela de Game Over*/
    BufferedImage gameOverFundo;
    Spritesheet gameoversheet;
    /*Botões e seus respectivos sprites*/
    Button[] botoes;
    BufferedImage botaoMenuSprite;
    BufferedImage botaoRestartSprite;
    
    
    public GameOver(){
        initSpriteMenu();
    }
    
    public void initSpriteMenu(){
        SpriteData menuData = SpriteLoader.spriteDataLoader().get("fundoGameOver");
        try {
            gameOverFundo = ImageIO.read(getClass().getResource(menuData.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //inicio as propriedades do meu sprite player
        this.gameoversheet = new Spritesheet(gameOverFundo, 256, 448, 0.0, Universal.SCALE); 
    }
    
    public void update(){
        
    }
    
    public void render(Graphics2D g2D){
        gameoversheet.render(g2D, 0, 0);
    }
}
