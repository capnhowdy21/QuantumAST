
package com.ninjacannon.quantum.entity.component.movement;

import com.ninjacannon.quantum.entity.component.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class PlayerMovementComponent extends Component
{
    private float dx;
    private float dy;
        
    public PlayerMovementComponent(String id)
    {
        super(id);
        dx = 0;
        dy = 0;
    }
    
    public void setDx(float dX){
        this.dx = dX;
    }
    
    public void setDY(float dY){
        this.dy = dY;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        Vector2f position = owner.getPosition();
        
        position.x += dx * delta;
        position.y += dy * delta;        
        
        if(position.x <= 0){
            position.x = 0;
        } else if(position.x + owner.getWidth() >= gc.getWidth()){
            position.x = gc.getWidth() - owner.getWidth();
        }
        if(position.y <= 0){
            position.y = 0;
        } else if(position.y + owner.getHeight() >= gc.getHeight()){
            position.y = gc.getHeight() - owner.getHeight();
        }
        
        owner.setPosition(position);
    }
    
    public void reset()
    {
        dx = 0;
        dy = 0;
    }
}
