
package com.ninjacannon.quantum.entity.component.movement;

import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class HorizontalMovementComponent extends Component
{
    float dx;
    
    public HorizontalMovementComponent(String id)
    {
        super(id);
        this.dx = -.5f;
    }
    
    public HorizontalMovementComponent(String id, float dx)
    {
        super(id);
        this.dx = dx;
    }
    
    public void setdX(float dx){
        this.dx = dx;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        Vector2f pos = owner.getPosition().copy();
        pos.x += (dx * delta);
        System.out.println();
        if((dx < 0)&& (pos.x + owner.getWidth() < 0)){
            EntityManager.manager.removeEntity(owner);
        } else if((dx > 0)&& (pos.x > gc.getWidth())){
            EntityManager.manager.removeEntity(owner);
        }
        
        owner.setPosition(pos);
    }
    
    
}
