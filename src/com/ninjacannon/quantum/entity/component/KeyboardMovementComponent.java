
package com.ninjacannon.quantum.entity.component;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class KeyboardMovementComponent extends Component
{
    float dx;
    float dy;
        
    public KeyboardMovementComponent(String id)
    {
        super(id);
        dx = 0;
        dy = 0;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        Vector2f position = owner.getPosition();
        dy=0;
        dx=0;
        
        Input input = gc.getInput();
        
        if(input.isKeyDown(Input.KEY_W)){
            dy = -.2f;
        }
        if(input.isKeyDown(Input.KEY_S)){
            dy = .2f;
        }
        if(input.isKeyDown(Input.KEY_D)){
            dx = .3f;
        }
        if(input.isKeyDown(Input.KEY_A)){
            dx = -.2f;
        }
        
        
        position.x += dx * delta;
        position.y += dy * delta;
        owner.setPosition(position);
    }
}
