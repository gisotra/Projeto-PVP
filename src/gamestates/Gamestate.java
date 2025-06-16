package gamestates;

public enum Gamestate {
    /*
    Classe especial: Enumerador 
    Aqui vai ter várias constantes 
    Os elementos de uma enumeração por padrão são considerados Static e Final
    
    Se eu tentar instanciar usando new algum objeto do tipo enumerador vai dar 
    erro de compilação
    */
    PLAYING_ONLINE, PLAYING_OFFLINE, MENU, TUTORIAL, ABOUT, GAME_OVER;
    
    public static Gamestate state = MENU;
}
