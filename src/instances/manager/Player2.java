package instances.manager;

import utilz.Universal;

public class Player2{
    /*
    O que vai ser o player 2:
    Ele vai ter cooldowns próprios para cada obstáculo.
    */
    SpawnManager spm = new SpawnManager();
    
    /*Criei um nextSpawn para cada elemento porque eu quero que cada um tenha 
    um cooldown próprio*/
    long lastSpawnWall = 0;
    long lastSpawnBird = 0;
    long lastSpawnSaw = 0;
    long cooldownAll = 500; //meio segundo
    
    final long cooldownWall = 1000;
    final long cooldownSaw = 1500;
    final long cooldownBird = 700;
    
    // Cooldown global entre spawns (anti-spam)
    long lastGlobalSpawn = 0;
    final long globalCooldown = 500;
    
    public void play(){ //(currentTime - lastSpawn) >= SpawnWall )
        long currentTime = System.currentTimeMillis();
        
        if(currentTime - lastGlobalSpawn < globalCooldown){ //evita spam do player2 
            return;
        }
        
        if(Universal.wall && (currentTime - lastSpawnWall) >= cooldownWall){
            spm.spawnWall();
            
            lastSpawnWall = currentTime;
            lastGlobalSpawn = currentTime;
            return;
        }
        
        if(Universal.saw && (currentTime - lastSpawnSaw) >= cooldownSaw){
            spm.spawnSaw();
            
            lastSpawnSaw = currentTime;
            lastGlobalSpawn = currentTime;
            return;
        }
        if(Universal.bird && (currentTime - lastSpawnBird) >= cooldownBird){
            spm.spawnBird();
            
            lastSpawnBird = currentTime;
            lastGlobalSpawn = currentTime;
            return;
        }
        
    }
    
    

}
