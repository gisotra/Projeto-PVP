package ui;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
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
    Buttons[] botoes = new Buttons[2];
    BufferedImage botaoMenuSprite;
    BufferedImage botaoRestartSprite;
    
    
    public GameOver(){
        initSpriteMenu();
        botoes[0] = new Buttons(5*Universal.TILES_SIZE, 5*Universal.TILES_SIZE, 48, 48, botaoMenuSprite); //botão de voltar ao menu
        botoes[1] = new Buttons(7*Universal.TILES_SIZE, 5*Universal.TILES_SIZE, 48, 48, botaoRestartSprite); //botao de voltar ao loop do jogo
    }
    
    public void initSpriteMenu(){
        SpriteData gameoverData = SpriteLoader.spriteDataLoader().get("fundoGameOver");
        SpriteData buttMenuData = SpriteLoader.spriteDataLoader().get("menuButton");
        SpriteData buttRestartData = SpriteLoader.spriteDataLoader().get("restartButton");

        try {
            gameOverFundo = ImageIO.read(getClass().getResource(gameoverData.getPath()));
            botaoMenuSprite = ImageIO.read(getClass().getResource(buttMenuData.getPath()));
            botaoRestartSprite = ImageIO.read(getClass().getResource(buttRestartData.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //inicio as propriedades do meu sprite player
        this.gameoversheet = new Spritesheet(gameOverFundo, 256, 448, 0.0, Universal.SCALE);
    }
    
    public void update(){
        
    }
    
    public void render(Graphics2D g2D){

        //gameoversheet.render(g2D, 0, 0);

        for(Buttons but : botoes){
            but.render(g2D);
        }
    }
    
    public boolean isIn(MouseEvent e, Buttons mb) {
        return mb.getDimensoes().contains(e.getX(), e.getY());
    }
}
