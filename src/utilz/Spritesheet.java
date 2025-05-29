package utilz;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Spritesheet { /*Classe para gerenciamento dos sprites*/
    {// sprites player
    /*public static final String PLAYER_ATLAS = "player/playerAtualizado4.png";
    
    // sprites dos cenários
    public static final String LAYER_GRASS = "environment/grama2.png";
    public static final String LAYER_CEU = "environment/ceu2.png";
    public static final String LAYER_CERCA = "environment/cerca2.png";
    public static final String LAYER_NUVENS = "environment/nuvens2.png";*/
    }
    {/*public static BufferedImage GetSpritesheet(String fileName){
        
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
    }*/}
    public int altura, largura;
    BufferedImage spritesheet;
    BufferedImage[][] sprites;
    
    int frameAtual;
    int totalFrames;
    int indice;
    int contadorFrames;
    int totalIndices;
    int trocaDeFrames;
    
    // Construtor do Sprite: carrega a sprite sheet e separa os frames
    public Spritesheet(BufferedImage spritesheet, int altura, int largura, double time) {
        this.spritesheet = spritesheet;
        this.altura = altura;
        this.largura = largura;
        totalFrames = spritesheet.getWidth()/largura; 
        totalIndices = spritesheet.getHeight()/altura;
        
        trocaDeFrames = (int)(Universal.FPS_SET * time / totalFrames);

        sprites = new BufferedImage[totalIndices][totalFrames];
        initSprites();
    }
    
    public void initSprites(){
        for(int i = 0; i < totalIndices; i++){
            for(int j = 0; j < totalFrames; j++){
                sprites[i][j] = getSpriteFromSheet(spritesheet, j * largura, i * altura, largura, altura);
            }
        }
    }
    
    public BufferedImage getSpriteFromSheet(BufferedImage image, int x, int y, int largura, int altura){ 
        BufferedImage spriteNovo = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
        
        for (int j = 0; j < altura; j++) {
            for (int i = 0; i < largura; i++) {
                int pixel = image.getRGB(x + i, y + j);  
                spriteNovo.setRGB(i, j, pixel);             
            }
        }
        return spriteNovo;
    }
    
    public void render(Graphics2D g2d, int x, int y) {
        contadorFrames++;
        if (contadorFrames % trocaDeFrames == 0) {
            frameAtual++;
        }
        if (frameAtual >= totalFrames) {
            frameAtual = 0;
        }
        g2d.drawImage(sprites[indice][frameAtual], x, y,
                (int) (largura * Universal.SCALE), (int) (altura * Universal.SCALE), null);
    }
}