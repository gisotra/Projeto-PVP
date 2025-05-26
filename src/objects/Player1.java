package objects;

import utilz.Spritesheet;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.Universal;
import static objects.Collisors.CanMoveHere;
import static objects.Collisors.GetEntityYPosAboveGround;
import static objects.Collisors.IsEntityOnGround;

import room.Ground;

public class Player1 extends Entities{
    
    /*------------ ATRIBUTOS ------------*/
    private BufferedImage[][] spritesheet;
    private int aniTick, aniIndex, aniSpeed = 22;
    private int playerAction = Universal.IDLE;
    private boolean moving = false;
    private boolean left, up, right, down;
    private float playerSpeed = 3.0f; 
    
    // Atributo do CHÃO
    private Ground ground;
    
    // Mecânica de pulo + Gravidade
    private boolean jump;
    private float airSpeed = 0f;
    private float gravity = 0.04f * Universal.SCALE;
    private float impulse = -2.0f * Universal.SCALE; // Força do meu salto
    private boolean inAir = false;
    
    
    /*------------ CONSTRUTOR ------------*/
    public Player1(float x, float y, Ground ground){
        super(x, y, 128, 128);
        this.ground = ground;
        loadAnimations();
        initHitbox(x, y, 71, 95); //tamanho do meu personagem no sprite * 4 (escala que estou usando)
        //initHitbox(x, y, p_height, p_width);
        //faço o player cair logo de primeira
        if(!IsEntityOnGround(hitbox, ground)){
            inAir = true;
        }
    }

    /*------------ UPDATE ------------*/
    public void update(){
        updatePos();
        updateHitbox();
        updateAnimationTick();
        setAnimation();
    }
    
    public void render(Graphics g) {
        
        g.drawImage(spritesheet[playerAction][aniIndex], (int) x, (int) y, 128, 128, null); //desenho o pedaço recortado na posição que eu quero, no tamanho que eu quero
        //drawHitbox(g); 
    }
    
    /*------------[ chamado no update] UPDATE DOS FRAMES ------------*/
    private void updateAnimationTick() {
        {
            /*
        Animation tick é tipo um contador que você usa pra controlar a velocidade da animação.

        Imagina assim:

        Você tem várias imagens do seu personagem correndo (um spritesheet com 6 imagens, por exemplo).

        Se você trocasse de imagem toda vez que desenha na tela, o boneco ia correr rápido demais — porque o monitor desenha 60 vezes por segundo!

        Então o que você faz?
        ➔ Você espera alguns ticks antes de mudar para o próximo frame da animação.    
             */
        }
        
        aniTick++;
            if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= Universal.GetSpriteAmount(playerAction)) { //se o índice superar o número de frames, eu reseto ele, e ele fica "em loop"
                    aniIndex = 0;
                }

            }
        
    }

    /*------------[ chamado no update] DEFINE SE O PERSONAGEM ESTÁ PARADO OU SE MOVENDO ------------*/
    public void setAnimation() {

        int startAni = playerAction;

        if (moving) {
            playerAction = Universal.RUNNING;
        } else {
            playerAction = Universal.IDLE;
        }
        
        if (inAir) {
            if (airSpeed < 0) { // estamos SUBINDO
                playerAction = Universal.JUMP;
            } else {
                playerAction = Universal.IS_FALLING;
            }
        }
        

        if (startAni != playerAction) {
            resetAniTick();
        }
    }
    
    public void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }
    
    /*------------[ chamado no update] MOVE O PLAYER ------------*/
        public void updatePos(){
		moving = false;
                
                if(jump){
                    jump();
                }
                
		if (!left && !right && !inAir) //se não estiver indo pra direita e esquerda E estiver no chão, ele está parado
			return;

		float xSpeed = 0;

		if (left)
			xSpeed -= playerSpeed;
                        moving = true;
		if (right)
			xSpeed += playerSpeed;
                        moving = true;

                if(!inAir){
                    if(!IsEntityOnGround(hitbox, ground)){
                        inAir = true;
                    }
                }
                
                
                if(inAir) { //se estamos no ar 
                    if(CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, ground)){ // checamos a direção x para colisão
                        hitbox.y += airSpeed;
                        airSpeed += gravity;
                        updateXPos(xSpeed);
                        y = hitbox.y;
                    } else { //vamos acertar o teto ou o chão
                        hitbox.y = GetEntityYPosAboveGround(hitbox, ground);
                        if(airSpeed > 0){
                            resetInAir();
                        } else {
                            updateXPos(xSpeed);
                        }
                    }
                    
                } else {
                    updateXPos(xSpeed);
                    moving = true;
                }
            
        }

    private void updateXPos(float xSpeed){
        if (xSpeed != 0) { // Só verifica a colisão horizontal quando há movimento
            if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, ground)) {
                hitbox.x += xSpeed;
                x += xSpeed;  // Atualiza a posição do jogador
            } else {
                // Se houver colisão, o personagem não se move
                return;
            }
        }
    }

    private void jump() {
        if (inAir) {
            return; // pulo não funciona
        }
        inAir = true;
        airSpeed = impulse; // aumento "subitamente" o meu y
    }
    
    private void resetInAir(){
        inAir = false;
        airSpeed = 0;
    }
    
    private void loadAnimations() {

        BufferedImage img = Spritesheet.GetSpritesheet(Spritesheet.PLAYER_ATLAS); //instancia BufferedImage lê o meu spritesheet 
        spritesheet = new BufferedImage[3][3]; //tamanho do spritesheeet
        for (int j = 0; j < spritesheet.length; j++) {
            for (int i = 0; i < spritesheet[j].length; i++) {
                spritesheet[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }
    }
    
    
    /*------------ GETTERS DE DIREÇÃO DO PLAYER ------------*/
    public boolean isLeft() {
        return left;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isDown() {
        return down;
    }

    /*------------ SETTERS DE DIREÇÃO DO PLAYER ------------*/
    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
    
    public void setJump(boolean jump){
        this.jump = jump;
    }
    

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }
}
    
    



