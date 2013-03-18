
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.QuantumGame;
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
    public enum Allegiance{ENEMY, PLAYER};
    protected boolean collided;
    protected Allegiance allegiance;
    protected Entity other;
    protected int health;
    
    
    public CollisionComponent(String id, Allegiance allegiance)
    {
        super(id);
        collided = false;
        this.allegiance = allegiance;
        health = 1;
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
             me.y > them.y+e.getHeight() || me.y + owner.getWidth() < them.y
                || them.x > QuantumGame.GAMEWIDTH){
            //do nothing
        } else if(owner.getId() != e.getId()){
           setCollision(e);
        }
    }
    
    public void setCollision(Entity e)
    {
        collided = true;
        other = e;
    }
    
    public boolean hasCollided(){
        return collided;
    }
    
    @Override
    public void reset()
    {
        collided = false;
        other = null;
    }
    
    public Allegiance getAllegiance(){
        return this.allegiance;
    }
    
    public abstract void collide();
    
    public void setHealth(int health){
        this.health = health;
    }
}
