package utilz;

public class Universal {
    
    /*
    largura: 8 * 32 = 256 | OU | 16 * 32 = 512
    altura: 7 * 32 = 224
    */
    /*Configurações de resolução da tela*/
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 3f;
    public final static int TILES_IN_WIDTH = 16;  //512px de COMPRIMENTO
    public final static int TILES_IN_HEIGHT = 7;  //224px ALTURA
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_IN_WIDTH * TILES_SIZE;
    public final static int GAME_HEIGHT = TILES_IN_HEIGHT * TILES_SIZE;
    
    /*Direções de movimento do player*/
    public static final int LEFT = 0;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;

    /*Índices das animações dos sprites do player (vertical)*/
    public static final int IDLE = 0;
    public static final int RUNNING = 0;
    public static final int JUMP = 1;
    public static final int LANDING = 2;
    public static final int IS_FALLING = 2;
    public static final int IS_DEAD = 4;
    
    /*Método que retorna quantos frames cada ação possui*/
    public static int GetSpriteAmount(int player_action) {
        switch (player_action) {

            case RUNNING:
                return 2;
            case IS_FALLING:
                return 1;
            case IS_DEAD:
                return 1;
            case JUMP:
                return 3;
            default:
                return 1;
        }
    }
}
