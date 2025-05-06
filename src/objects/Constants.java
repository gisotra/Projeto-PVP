package objects;

public class Constants {
    
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUNNING = 0;
        public static final int JUMP = 1;
        public static final int LANDING = 2;
        public static final int IS_FALLING = 2;
        public static final int IS_DEAD = 4;
        
        public static int GetSpriteAmount(int player_action){
            switch(player_action){
                
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
    }
    
