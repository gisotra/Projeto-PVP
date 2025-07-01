package utilz;

import instances.entities.Player1;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Spritesheet { /*Classe para gerenciamento dos sprites*/
    public int frameHeightOriginal, frameWidthOriginal; //tamanho do frame original
    public float scale; //escala aplicada para pintar o sprite na tela, utilizando a classe Universal
    private int renderWidth; //produto final dos tamanhos do frame * escala
    private int renderHeight; //produto final dos tamanhos do frame * escala
    BufferedImage spritesheet; //meu spritesheet full 
    BufferedImage[][] sprites; //o spritesheet dividido
    BufferedImage[][] spritesEscalonados; //Evita cálculo desnecessario na renderização
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
        
        /*Caso eu mosque e passe um sprite com tamanho bizonho*/
        if (spritesheet.getWidth() % frameWidthOriginal != 0
                || spritesheet.getHeight() % frameHeightOriginal != 0) {
            System.out.println("spritesheet.getWidth(): " + spritesheet.getWidth());
            System.out.println("spritesheet.getHeight(): " + spritesheet.getHeight());
            System.out.println("frameWidthOriginal: " + frameWidthOriginal);
            System.out.println("frameHeightOriginal: " + frameHeightOriginal);
            throw new IllegalArgumentException("Tamanho da spritesheet não é múltiplo do tamanho do frame.");
        }
        
        //Caso a spritesheet seja composta de 1 único frame
        if(spritesheet.getWidth() == frameWidthOriginal){
                totalFrames = 0;
                trocaDeFrames = 0;
            spritesEscalonados = new BufferedImage[1][1];
            BufferedImage scaled = new BufferedImage(renderWidth, renderHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = scaled.createGraphics();
            g.drawImage(spritesheet, 0, 0, renderWidth, renderHeight, null);
            g.dispose();
            spritesEscalonados[0][0] = scaled;
                //vou usar somente meu atributo spritesheet
        } else {
        
            totalIndices = spritesheet.getHeight()/frameHeightOriginal; 
            totalFrames = spritesheet.getWidth()/frameWidthOriginal; 
        
            trocaDeFrames = (int)(Universal.FPS_SET * time / totalFrames);

            sprites = new BufferedImage[totalIndices][totalFrames];
            spritesEscalonados = new BufferedImage[totalIndices][totalFrames];
            initSprites();
        }
    }
    
    public void initSprites(){
        if(totalIndices == 0 && totalFrames == 0){
            return;
        }
        for(int i = 0; i < totalIndices; i++){
            for(int j = 0; j < totalFrames; j++){
                sprites[i][j] = getSpriteFromSheet(spritesheet,
                        j * frameWidthOriginal,
                        i * frameHeightOriginal, frameWidthOriginal, frameHeightOriginal);

                BufferedImage scaled = new BufferedImage(renderWidth, renderHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = scaled.createGraphics();
                g.drawImage(sprites[i][j], 0, 0, renderWidth, renderHeight, null);
                g.dispose();

                spritesEscalonados[i][j] = scaled;
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
        if(totalIndices == 0 && totalFrames == 0 && trocaDeFrames == 0){ //é um obstáculo estático
            g2d.drawImage(spritesEscalonados[acaoAtual][frameAtual], x, y, null);
            
        } else {
        contadorFrames++;
        if (contadorFrames % trocaDeFrames == 0) {
            frameAtual++;
        }
        if (frameAtual >= Universal.GetSpriteAmount(acaoAtual)) {
            frameAtual = 0;
        }
        if(Universal.dead){
            contadorFrames = 0;
        }

            g2d.drawImage(spritesEscalonados[acaoAtual][frameAtual], x, y, null);
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