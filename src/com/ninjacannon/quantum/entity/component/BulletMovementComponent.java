
package com.ninjacannon.quantum.entity.component;

import com.ninjacannon.quantum.entity.EntityManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class BulletMovementComponent extends Component
{
    float dx;
    public BulletMovementComponent(String id)
    {
        super(id);
        dx = 10;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        owner.setPosition(owner.getPosition().add(new Vector2f(dx,0f)));
        if(owner.getPosition().x > gc.getScreenWidth()){
            EntityManager.manager.removeEntity(owner);
        }
    }
    
    
}
