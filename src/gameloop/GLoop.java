package gameloop;

public class GLoop implements Runnable {
    
    protected GPanel gP;
    
    public GLoop(GPanel gP){
        this.gP = gP;
    }
    
    @Override
    public void run() {
        int realFps = 0;

        double frameTime = 1_000_000_000.0/gP.FPS;
        double deltaTime;
        double lastTime = System.nanoTime();
        double nextFrame = System.nanoTime() + frameTime;
        double sleepTime;

        double start;
        double secondCounter = 0;

        while (true) {
            start = System.nanoTime();
            realFps++;

            deltaTime = (System.nanoTime() - lastTime) / 1_000_000_000;
            lastTime = start;

            update(deltaTime);
            gP.repaint();

            sleepTime = (nextFrame - System.nanoTime()) / 1_000_000.0 ;
            if(sleepTime < 0)
                sleepTime = 0;

            try {
                Thread.sleep((long)sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            secondCounter += (System.nanoTime() - start)/1_000_000_000;

            if(secondCounter >= 1){
                realFps = 0;
                secondCounter = 0;
            }

            nextFrame += frameTime;
        }
    }

    public void update(double deltaTime) {
        
    }
}

