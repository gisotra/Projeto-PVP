package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import loop.GRoom;

public class Spritesheet {
    
    public static final String PLAYER_ATLAS = "playerAtualizado2.png";
    
    public static BufferedImage GetSpriteAtlas(String fileName){
        
        BufferedImage img = null;
                InputStream is = Spritesheet.class.getResourceAsStream("/assets/player/" + fileName); //leitura do spritesheet

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
    
    /*public static int[][] GetLevelData(){
        int[][] lvlData = new int[GRoom.TILES_IN_HEIGHT][GRoom.TILES_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        
        for(int j = 0; j < img.getHeight(); j++){
            for(int i = 0; i < img.getWidth(); i++){
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if(value >= 48){
                    value = 0;
                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }*/
}