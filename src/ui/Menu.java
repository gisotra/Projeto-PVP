package ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import utilz.SpriteData;
import utilz.SpriteLoader;
import utilz.Spritesheet;
import utilz.Universal;

public class Menu implements ScreenStates {
    BufferedImage menuFundo;
    Spritesheet menusheet;
    Buttons[] botoesMenu = new Buttons[3];
    
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

    @Override
    public void update(){
        
    }

    @Override
    public void render(Graphics2D g2D){
        menusheet.render(g2D, 0, 0);
    }

    @Override
    public boolean isIn(MouseEvent e, Buttons mb){
        return mb.getDimensoes().contains(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e){

    }

    @Override
    public void mouseReleased(MouseEvent e){

    }

    @Override
    public void mouseMoved(MouseEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){

    }

    @Override
    public void keyReleased(KeyEvent e){

    }
}
