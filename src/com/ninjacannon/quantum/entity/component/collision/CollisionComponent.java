
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.component.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public abstract class CollisionComponent extends Component
{
    public boolean collided;
    private String eId;
    
    public CollisionComponent(String id)
    {
        super(id);
        collided = false;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        if(collided){
            collide();
        }
    }
    
    public void collides(Entity e)
    {
        Vector2f me = owner.getPosition();
        Vector2f them = e.getPosition();
        if(me.x > them.x + e.getWidth() || me.x + owner.getWidth() < them.x ||
             me.y > them.y+e.getHeight() || me.y + owner.getWidth() < them.y){
            //do nothing
        } else if(!owner.getId().equalsIgnoreCase(e.getId())) {
            setCollision(e.getId());
            e.getCollision().setCollision(owner.getId());
        }
    }
    
    public void setCollision(String eId){
        collided = true;
        this.eId = id;
    }
    
    public abstract void collide();
}
