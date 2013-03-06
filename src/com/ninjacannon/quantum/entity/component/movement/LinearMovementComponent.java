
package com.ninjacannon.quantum.entity.component.movement;

import com.ninjacannon.quantum.entity.component.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class LinearMovementComponent extends Component
{
    float dx;
    float dy;
    
    public LinearMovementComponent(String id, float dx, float dy)
    {
        super(id);
        this.dx = dx;
        this.dy = dy;
    }
    
    public void setDx(float dx){
        this.dx = dx;
    }
    
    public void setDy(float dy){
        
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        Vector2f pos = owner.getPosition();
        pos.x += (dx * delta);
        pos.y += (dy * delta);
        
        if((dx < 0) && (pos.x + owner.getWidth() < 0)){
            owner.setAlive(false);
        } else if((dx > 0) && (pos.x > gc.getWidth())){
            owner.setAlive(false);
        } else if((dy < 0) && (pos.y < 0)){
            owner.setAlive(false);
        } else if((dy > 0) && pos.y + owner.getHeight() > gc.getHeight()){
            owner.setAlive(false);
        }   
        
        owner.setPosition(pos);
    }
    
    @Override
    public void reset()
    {
        //Nothing needed.
    }
    
}
