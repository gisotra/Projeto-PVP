package loop;

import utilz.Universal;

public class GRoom implements Runnable {
    
    /*------------ ATRIBUTOS ------------*/
    
    GCanvas gc;
        
    /*------------ CONSTRUTOR ------------*/
	public GRoom(GCanvas gc) {
            this.gc = gc;
	}
        /*------------ MÉTODO RUN DA THREAD ------------*/
    @Override
    public void run() {
        double tempoPorFrame = 1_000_000_000.0 / Universal.FPS_SET;
        double ultimoTempo = System.nanoTime();
        double dT;
        double threadSleep;
        long threadSleepMS;
        int threadSleepNano;
        double proximoFrame = System.nanoTime() + tempoPorFrame;
        

        long timer = System.currentTimeMillis();
        int frames = 0;

        while (true) {
            if(!gc.isDisplayable() || !gc.isFocusOwner()){
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
                continue;
            }
            
            double agora = System.nanoTime();
            dT = (System.nanoTime() - ultimoTempo) / 1_000_000_000;
            ultimoTempo = agora;
            dT = Math.min(dT, 1.0 / 30.0);
        
            update(dT);
            render();
            Universal.SCORE += (int) (100 * dT);
            //Universal.globalCooldown -= dT;
            
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
}

