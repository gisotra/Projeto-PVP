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
        final double tempoPorFrame = 1_000_000_000.0 / Universal.FPS_SET;
        long ultimoTempo = System.nanoTime();
        double dT = 0;

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
            
            long agora = System.nanoTime();
            dT += (agora - ultimoTempo) / tempoPorFrame;
            //dT = Math.min(dT, 0.1);
            ultimoTempo = agora;
            
            boolean renderizou = false;

            if (dT >= 1) {
                update(dT);
                dT--;
                renderizou = true;
            }
            
            if(renderizou){
                render();
                frames++;
            }   else {
            // Se não renderizou, libera a CPU pra outras threads
            Thread.yield();
        }

            // Exibe o FPS a cada segundo
            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                System.out.println("Deltatime: " + dT);
                frames = 0;
                timer += 1000;
            }

            // Dorme um pouco pra não travar a CPU
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
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

