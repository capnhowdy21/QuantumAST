
package com.ninjacannon.quantum.entity.component;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class TimedComponent extends Component
{   
    private int duration;
    private int timer;
    
    public TimedComponent(String id, int duration)
    {
        super(id);
        this.duration = duration;
        this.timer = duration;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        timer -= delta;
        if(timer <0){
            owner.setAlive(false);
            timer = 0;
        }
    }
    
    @Override
    public void reset(){
        timer = duration;
    }

}
