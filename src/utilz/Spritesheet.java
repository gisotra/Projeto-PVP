package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import loop.GRoom;

public class Spritesheet { /*Image loader dos sprites*/
    
    // sprites player
    public static final String PLAYER_ATLAS = "player/playerAtualizado2.png";
    
    // sprites dos cenários
    public static final String LAYER_GRASS = "environment/grama2.png";
    public static final String LAYER_CEU = "environment/ceu2.png";
    public static final String LAYER_CERCA = "environment/cerca2.png";
    public static final String LAYER_NUVENS = "environment/nuvens2.png";
    
    public static BufferedImage GetSpriteAtlas(String fileName){
        
        BufferedImage img = null;
                InputStream is = Spritesheet.class.getResourceAsStream("/assets/" + fileName); //leitura do spritesheet

        try {
            img = ImageIO.read(is); //instancia BufferedImage lê o meu spritesheet 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return img;
    }
}