package instances.obstacles;

import java.awt.geom.Rectangle2D;
import utilz.Universal;
import room.Ground;


public class Collisors {

    /*Uma classe com métodos auxiliares especializados em tratar das colisões do 
    player1 com o cenário*/

    //colisão com o CHÃO
    public static boolean CanMoveHere(float x, float y, float width, float height, Ground ground) {
        if (!IsSolid(x, y, ground)) // canto superior esquerdo
        {
            if (!IsSolid(x + width, y + height, ground)) // canto inferior direito
            {
                if (!IsSolid(x + width, y, ground)) // canto superior direito
                {
                    if (!IsSolid(x, y + height, ground)) // canto inferior esquerdo
                    {
                        return true; // não há colisão com NADA, então eu posso me mover 
                    }
                }
            }
        }

        return false;
    }
    
    private static boolean IsSolid(float x, float y, Ground ground) {
        //true -> é sólido, não pode se mover   ||    false -> não é sólido, não tem nada ali, pode se mover
        
        // Fora dos limites da tela? Então é sólido (para impedir que o jogador saia da tela)
        if (x < 0 || x >= Universal.GAME_WIDTH)
            return true;
        if (y < 0 || y >= Universal.GAME_HEIGHT)
            return true;

        // Verifica se o ponto está dentro da hitbox do chão
        if (ground.getHitbox().contains(x, y))
        return true;

        return false;
    }
    
    
    public static float GetEntityYPosAboveGround(Rectangle2D.Float hitbox, Ground ground) {
        // Pegamos o topo do chão
        float groundTop = (float) ground.getHitbox().getY();

        // Corrigimos a posição do player para que ele fique exatamente acima do chão
        return groundTop - hitbox.height;
    }
    
        public static boolean IsEntityOnGround(Rectangle2D.Float hitbox, Ground ground){
        //checar o pixel abaixo dos cantos inferiores direito e esquerdo
        if(!IsSolid(hitbox.x, hitbox.y+hitbox.height + 1, ground)){
            if(!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, ground)){
                return false; //não estamos no chão
            }
        }
        return true;
    }
}
