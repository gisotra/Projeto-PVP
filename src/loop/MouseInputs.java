package loop;

import gamestates.Gamestate;
import static gamestates.Gamestate.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
     
    private GCanvas gc;

    public MouseInputs(GCanvas gc) {
        this.gc = gc;
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
    
    }

    @Override
    public void mouseMoved(MouseEvent e){
        switch(Gamestate.state){
            case MENU:{
            }
                break;
            case PLAYING_OFFLINE:{
                
            }
                break;
            case PLAYING_ONLINE:{
                
            }
                break;
        
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        switch(Gamestate.state){
            case MENU:{
            }
                break;
            case PLAYING_OFFLINE:{
                
            }
                break;
            case PLAYING_ONLINE:{
                
            }
                break;            
        }
    }
    
    
    @Override
    public void mousePressed(MouseEvent e){
        switch (Gamestate.state) {
            case MENU: {
            }
            break;
            case PLAYING_OFFLINE: {

            }
            break;
            case PLAYING_ONLINE: {

            }
            break;
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        switch (Gamestate.state) {
            case MENU: {
            }
            break;
            case PLAYING_OFFLINE: {

            }
            break;
            case PLAYING_ONLINE: {

            }
            break;
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e){
        //TODO
    }
    
    @Override
    public void mouseExited(MouseEvent e){
        //TODO
    }
    
    
}
