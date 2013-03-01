
package com.ninjacannon.quantum.entity.component.movement;

import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class SineMovementComponent extends Component
{
    float dx;
    float midY = 0;
    float peak = 200;
    float interval = 250;
    
    public SineMovementComponent(String id)
    {
        super(id);
        this.dx = -.5f;
    }
    
    public SineMovementComponent(String id, float dx)
    {
        super(id);
        this.dx = dx;
    }
    
    public void setDx(float dx){
        this.dx = dx;
    }
    
    public void setMid(float y){
        this.midY = y;
    }
    
    public void setPeak(float peak){
        this.peak = peak;
    }
    
    public void setInterval(float interval){
        this.interval = interval;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        Vector2f pos = owner.getPosition().copy();
        pos.x += (dx * delta);
        pos.y = midY + (float)(Math.sin(pos.x / interval) * peak);
        
        if((dx < 0)&& (pos.x + owner.getWidth() < 0)){
            owner.setAlive(false);
        } else if((dx > 0)&& (pos.x > gc.getWidth())){
            owner.setAlive(false);
        }
        
        owner.setPosition(pos);
    }
    
}
