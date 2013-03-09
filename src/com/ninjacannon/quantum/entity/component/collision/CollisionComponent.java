
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.Entity.EntityType;
import com.ninjacannon.quantum.entity.component.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public abstract class CollisionComponent extends Component
{
    public enum Allegiance{ENEMY, PLAYER};
    protected boolean collided;
    protected Allegiance allegiance;
    protected EntityType eId;
    protected Allegiance eAllegiance;
    
    
    public CollisionComponent(String id, Allegiance allegiance)
    {
        super(id);
        collided = false;
        this.allegiance = allegiance;
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
        } else if(owner.getId() != e.getId()){
           setCollision(e.getId(), e.getCollision().getAllegiance());
           e.getCollision().setCollision(owner.getId(), allegiance);
           System.out.println(owner.getId());
        }
    }
    
    public void setCollision(EntityType id, Allegiance eAllegiance)
    {
        collided = true;
        this.eId = id;
        this.eAllegiance = eAllegiance;
    }
    
    public boolean hasCollided(){
        return collided;
    }
    
    @Override
    public void reset()
    {
        collided = false;
        eId = null;
        eAllegiance = null;
    }
    
    public Allegiance getAllegiance(){
        return this.allegiance;
    }
    
    public abstract void collide();
    
}
