
package com.ninjacannon.quantum.entity.component;

import com.ninjacannon.quantum.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public abstract class Component 
{
    protected String id;
    protected Entity owner;
    
    
    public Component(String id){
        this.id = id;
    }
    
    public String getID(){
        return id;
    }
    
    public void setOwnerEntity(Entity owner){
        this.owner = owner;
    }
    
    public abstract void update(GameContainer gc, StateBasedGame sb, int delta);
}
