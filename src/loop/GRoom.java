package loop;

import gamestates.Gamestate;
import static gamestates.Gamestate.*;
import utilz.Universal;

public class GRoom implements Runnable {
    
    /*------------ ATRIBUTOS ------------*/
    
    GCanvas gc;
    public double tempoPorFrame = 1_000_000_000.0 / Universal.FPS_SET;
    public double ultimoTempo = System.nanoTime();
    public double dT;
    public double threadSleep;
    public long threadSleepMS;
    public int threadSleepNano;
    public double proximoFrame = System.nanoTime() + tempoPorFrame;
    long timer = System.currentTimeMillis();
    int frames = 0;
        
    /*------------ CONSTRUTOR ------------*/
	public GRoom(GCanvas gc) {
            this.gc = gc;
	}
        /*------------ MÉTODO RUN DA THREAD ------------*/
    @Override
    public void run() {
        while (true) {
            if(!gc.isDisplayable() || !gc.isFocusOwner()){
                sleepEngine();
                continue;
            }
            
            double agora = System.nanoTime();
            dT = (System.nanoTime() - ultimoTempo) / 1_000_000_000;
            ultimoTempo = agora;
            dT = Math.min(dT, 1.0 / 30.0);
        
            update(dT);
            render();
            
            if(Gamestate.state == PLAYING_OFFLINE){
                Universal.SCORE += (int) (100 * dT);
                if(Universal.SCORE % 1000 == 0){
                    Universal.globalCooldown -= 500; 
                }
            }

            threadSleep = ((proximoFrame - System.nanoTime()) / 1_000_000);
            if(threadSleep < 0){
                threadSleep = 0;
            }
           
            threadSleepMS = (long) threadSleep;
            threadSleepNano = (int) ((threadSleep - threadSleepMS) * 1_000_000); 

            // Dorme um pouco pra não travar a CPU
            try {
                Thread.sleep(threadSleepMS, threadSleepNano);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            proximoFrame += tempoPorFrame;
            System.out.println("Global Cooldown: " + Universal.globalCooldown);
        }
        
    }
    /*------------ MÉTODO UPDATE ------------*/
    public void update(double dT) {
        gc.update(dT);
    }

    /*------------ MÉTODO RENDER ------------*/
    public void render() {
        gc.render();
    }
    
    public void sleepEngine(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        proximoFrame = System.nanoTime() + tempoPorFrame; 
        ultimoTempo = System.nanoTime();
    }
}

