package instances.entities;

import utilz.Universal;

public class Movement {
    /*
    Classe utilizada unicamente para implementação dos movimentos verticais
    */
    
    Player1 player1;
    public double speed = 1.8*Universal.SCALE;
    public double speedDT;
    public double MAX_SPEED = 2.5f*Universal.SCALE;
    public float horizontalSpeed;
    public float atrito = 0.7f*Universal.SCALE;
    public boolean isJumping = false;
    public float airSpeed = 0f; //Y
    public float gravity = 0.08f * Universal.SCALE;
    public float jumpPower = -2.8f * Universal.SCALE; // Força do meu salto
    public boolean inAir = false;
    public float heightGY; //usado para achar a posição Y em que o player tá "no chão"
    public float groundLvl;
    
    public Movement(Player1 player1){
        this.player1 = player1;
        heightGY = player1.getHitboxHeight();
        groundLvl = Universal.groundY - heightGY; // 5 Tiles - 1 = 4 tiles
    }
    /*
    groundY, and height is used to find the value of y where the object is 
    considered grounded.

jumpPower and jumpHeight controls the speed and distance the object can jump, 
    and gravity controls how fast the object should return to the ground position
    after jumps.

The class has three methods called update(), jump(), and isGrounded(). The method
    update() implements the jumping and gravity behaviour of the player. It 
    contains four if statements with the following purpose:*/
    
    public void updatePosY(double deltaTime){ //ainda vou usar o deltaTime para movimentação horizontal depois
        if(Universal.jump && isGrounded()){
            player1.playerAction = Universal.JUMP;
            airSpeed = jumpPower;
            isJumping = true;
        }
        
        if(isJumping){ //caso eu esteja pulando, eu continuamente somo a gravidade na airSpeed
            airSpeed += gravity;
            player1.setY(player1.getY() + airSpeed); //altero o Y do player
                if(airSpeed > 0){ //estou caindo
                    player1.playerAction = Universal.IS_FALLING;
                }
            

            //cheguei no chão, então preciso resetar o pulo
            if (player1.getY() >= groundLvl) {
                player1.setY(groundLvl);
                airSpeed = 0f;
                isJumping = false;
                player1.playerAction = Universal.IDLE;
                
            }
        } else if (!isGrounded()){ //cai de uma plataforma ou qualquer evento alternativo
            airSpeed += gravity;
            player1.setY(player1.getY() + airSpeed);
            player1.playerAction = Universal.IS_FALLING;
        }
    }
    
    public void updatePosX(double deltaTime) {
        speedDT = speed * deltaTime;

        if (Universal.right) {

            horizontalSpeed += speedDT;

        } else if (Universal.left) {

            horizontalSpeed -= speedDT;

        } else {

            //não estou apertando tecla alguma E estou no chão 
            if (horizontalSpeed > 0) { //freio ele na direita
                horizontalSpeed -= atrito * deltaTime;
                if (horizontalSpeed < 0) {
                    horizontalSpeed = 0; //paro
                }
            } else if (horizontalSpeed < 0) {
                horizontalSpeed += atrito * deltaTime;
                if (horizontalSpeed > 0) {
                    horizontalSpeed = 0; //paro
                }
            }
        }

        if (horizontalSpeed > MAX_SPEED) {
            horizontalSpeed = (float) MAX_SPEED;
        }
        if (horizontalSpeed < -MAX_SPEED) {
            horizontalSpeed = (float) -MAX_SPEED;
        }

        //aplico a mudança no player
        player1.setX(player1.getX() + horizontalSpeed);
        if(player1.getX() < 0){
            player1.setX(0);
        } else if (player1.getX() >= Universal.GAME_WIDTH - (Universal.TILES_SIZE)/2){
            player1.setX((Universal.GAME_WIDTH - (Universal.TILES_SIZE)/2));
        }
    }
    
    private boolean isGrounded() {
        if (player1.getY() >= groundLvl) {
            return true;
        } else {
            return false;
        }
    }
    
}
