package instances.entities;

import utilz.Universal;

public class Movement {
    Player1 player1;
    public double speed;
    public double speedDT; 
    public float groundY = 5 * Universal.TILES_SIZE; //usado para achar a posição Y em que o player tá "no chão"
    public float heightGY; //usado para achar a posição Y em que o player tá "no chão"
    public boolean isJumping = false;
    public float airSpeed = 0f; //Y
    public float gravity = 0.04f * Universal.SCALE;
    public float jumpPower = -2.0f * Universal.SCALE; // Força do meu salto
    public boolean inAir = false;
    public float groundLvl;
    
    public Movement(Player1 player1){
        this.player1 = player1;
        heightGY = player1.getHeight();
        groundLvl = this.groundY - this.heightGY;
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
    
    public void updatePos(double deltaTime){ //ainda vou usar o deltaTime para movimentação horizontal depois
        if(Universal.jump && isGrounded()){
            airSpeed = jumpPower;
            isJumping = true;
        }
        
        if(isJumping){ //caso eu esteja pulando, eu continuamente somo a gravidade na airSpeed
            airSpeed += gravity;
            player1.setY(player1.getY() + airSpeed); //altero o Y do player

            //cheguei no chão, então preciso resetar o pulo
            if (player1.getY() >= groundLvl) {
                player1.setY(groundLvl);
                airSpeed = 0f;
                isJumping = false;
            }
        } else if (!isGrounded()){ //cai de uma plataforma ou qualquer evento alternativo
            airSpeed += gravity;
            player1.setY(player1.getY() + airSpeed);
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
