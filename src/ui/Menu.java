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

public class Menu {
    BufferedImage menuFundo;
    Spritesheet menusheet;
    
    public Menu(){
        initSpriteMenu();
    }
    
    public void initSpriteMenu(){
        SpriteData menuData = SpriteLoader.spriteDataLoader().get("fundoMenu");
        try {
            menuFundo = ImageIO.read(getClass().getResource(menuData.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //inicio as propriedades do meu sprite player
        this.menusheet = new Spritesheet(menuFundo, 256, 448, 0.0, Universal.SCALE); 
    }
    
    public void update(){
        
    }
    
    public void render(Graphics2D g2D){
        menusheet.render(g2D, 0, 0);
    }
    
    public boolean isIn(MouseEvent e, Buttons mb) {
        return mb.getDimensoes().contains(e.getX(), e.getY());
    }
}
