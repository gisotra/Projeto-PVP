package utilz;

import instances.entities.Player1;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
    public int frameHeightOriginal, frameWidthOriginal; //tamanho do frame original
    public float scale; //escala aplicada para pintar o sprite na tela, utilizando a classe Universal
    private int renderWidth; //produto final dos tamanhos do frame * escala
    private int renderHeight; //produto final dos tamanhos do frame * escala
    BufferedImage spritesheet; //meu spritesheet full 
    BufferedImage[][] sprites; //o spritesheet dividido
    
    int frameAtual;
    int totalFrames;
    int indice;
    int contadorFrames;
    int totalIndices;
    int trocaDeFrames;
    
    int acaoAtual = Universal.IDLE;
    
    // Construtor do Sprite: carrega a sprite sheet e separa os frames
    public Spritesheet(BufferedImage spritesheet, int frameHeightOriginal, int frameWidthOriginal, double time, float scale) {
        this.spritesheet = spritesheet;
        this.frameHeightOriginal = frameHeightOriginal;
        this.frameWidthOriginal = frameWidthOriginal;
        this.scale = scale;
        this.renderHeight = (int)(frameHeightOriginal * scale);
        this.renderWidth = (int)(frameWidthOriginal * scale);
        totalIndices = spritesheet.getHeight()/frameHeightOriginal;
        totalFrames = spritesheet.getWidth()/frameWidthOriginal; 
        
        trocaDeFrames = (int)(Universal.FPS_SET * time / totalFrames);

        sprites = new BufferedImage[totalIndices][totalFrames];
        initSprites();
    }
    
    public void initSprites(){
        for(int i = 0; i < totalIndices; i++){
            for(int j = 0; j < totalFrames; j++){
                sprites[i][j] = getSpriteFromSheet(spritesheet, j * frameWidthOriginal, i * frameHeightOriginal, frameWidthOriginal, frameHeightOriginal);
            }
        }
    }
    
    public void setAtion(int acao){
        if(this.acaoAtual != acao){
            acaoAtual = acao;
            frameAtual = 0; //como eu mudei de animacao, reseto a contagem
            contadorFrames = 0;
        }
    }
    
    public BufferedImage getSpriteFromSheet(BufferedImage image, int x, int y, int largura, int altura) {
        return image.getSubimage(x, y, largura, altura);
    }
    
    public void render(Graphics2D g2d, int x, int y) {
        if(trocaDeFrames == 0){ //é um obstáculo
        g2d.drawImage(sprites[0][0], x, y, renderWidth, renderHeight, null);
        } else {
        contadorFrames++;
        if (contadorFrames % trocaDeFrames == 0) {
            frameAtual++;
        }
        if (frameAtual >= Universal.GetSpriteAmount(acaoAtual)) {
            frameAtual = 0;
        }
        g2d.drawImage(sprites[acaoAtual][frameAtual], x, y, renderWidth, renderHeight, null);
        }
    }

    public int getAltura() {
        return frameHeightOriginal;
    }

    public void setHeightSprite(int altura) {
        this.frameHeightOriginal = altura;
    }

    public int getLargura() {
        return frameWidthOriginal;
    }

    public void setWidthSprite(int largura) {
        this.frameWidthOriginal = largura;
    }
    
    public void setScale(float scale) {
        this.scale = scale;
        this.renderWidth = (int) (frameWidthOriginal * scale);
        this.renderHeight = (int) (frameHeightOriginal * scale);
    }
    
}