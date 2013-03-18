
package com.ninjacannon.quantum.entity.component.movement;

import com.ninjacannon.quantum.QuantumGame;
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
    float midY;
    float peak;
    float interval;
    
    public SineMovementComponent(String id)
    {
        super(id);
        this.dx = -.5f;
        if(owner != null){
            midY = owner.getPosition().y;
        } else {
            midY = 0;
        }
        peak = 200;
        interval = 250;
    }
    
    public SineMovementComponent(String id, float dx, float midY, float peak, float interval)
    {
        super(id);
        this.dx = dx;
        this.midY = midY;
        this.peak = peak;
        this.interval = interval;
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
        } else if((dx > 0)&& (pos.x > QuantumGame.GAMEWIDTH)){
            owner.setAlive(false);
        }
        
        owner.setPosition(pos);
    }
    
    @Override
    public void reset(){
        //do nothing;
    }
}
