package instances.entities;

import instances.obstacles.Wall;
import java.awt.image.BufferedImage;
import java.util.List;
import utilz.Universal;
import room.Ground;
import room.Obstacles;

public class Player2 {

    /*------------ ATRIBUTOS ------------*/
    private BufferedImage[][] spritesheet;
    private long lastSpawn = 0;
    private long nextSpawn;
    
    /*------------ SPAWNAR O MURO ------------*/
    public boolean spawnWall(List<Obstacles> obst, Ground ground) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSpawn >= nextSpawn) { //se o meu tempo atual menos o meu ultimo spawn for MAIOR que o "nextSpawn", eu posso spawnar

            float wall_spawn_y = ground.getY() - Universal.wall_height;
            Wall wall = new Wall(Universal.wall_spawn_x, wall_spawn_y, Universal.obst_speed, Universal.wall_width, Universal.wall_height);
            obst.add(wall);

            lastSpawn = currentTime;
            nextSpawn = 1000; 
            return true; 
        }
        return false; // ainda estou no meu cooldown
        
        
        /*resumo do método:
            Eu vou chamar esse método quando eu apertar a tecla O no meu 
            keyInputs, e esse método possui dentro de si uma mecânica 
            de temporizador, em que eu registro no momento o horário do 
            meu sistema, caso esse tempo atual for MAIOR que o meu cooldown,
            eu crio um muro com as definições que eu quero fora da tela e o 
            adiciono no arraylist passado como parâmetro (obst), decreto 
            o meu lastSpawn como o "tempo atual do programa", e defino um novo
            horário pro meu nextSpawn
        */
    }
    
    /*------------ GETTERS E SETTERS ------------*/
    public long getLastSpawn() {
        return lastSpawn;
    }

    public void setLastSpawn(long lastSpawn) {
        this.lastSpawn = lastSpawn;
    }

    public long getNextSpawn() {
        return nextSpawn;
    }

    public void setNextSpawn(long nextSpawn) {
        this.nextSpawn = nextSpawn;
    }
    

}
