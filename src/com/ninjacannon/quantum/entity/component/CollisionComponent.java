
package com.ninjacannon.quantum.entity.component;

import com.ninjacannon.quantum.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class CollisionComponent extends Component
{

    public CollisionComponent(String id){
        super(id);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        
    }
    
    public boolean collides(Entity e)
    {
        Vector2f me = owner.getPosition();
        Vector2f them = e.getPosition();
        
        if( (me.x + owner.getWidth() < them.x) || (me.x > them.x + e.getWidth()) ||
            (me.y + owner.getHeight() < them.y) || (me.y > them.y + e.getHeight())){
            return false;
        } 
        return true;
    }
    
    public void collided(Entity e)
    {
        
    }
}
