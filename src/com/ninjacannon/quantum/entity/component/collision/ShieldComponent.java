
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.Entity.EntityType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class ShieldComponent extends CollisionComponent
{
    
    private boolean shielded;
    private int maxStrength; 
    private int currStrength;
    
    public ShieldComponent(String id, int max)
    {
        super(id);
        this.maxStrength = max;
        currStrength = max;

    }
    
    public void setShielded(boolean shielded)
    {
        if (currStrength < 1){
            this.shielded = false;
        } else {
            this.shielded = shielded;
        }
    }
    
    public boolean isShielded(){
        return shielded;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        if(shielded){
            currStrength--;
            if(currStrength < 1){
                setShielded(false);
            }
        }else if(currStrength < maxStrength){
            currStrength++;
        }
        if(collided){
            collide();
        }
    }

    @Override
    public void collide()
    {
        if(eId == EntityType.BULLET && shielded){
            //shield stuff
        } else if (eId == EntityType.BULLET || eId == EntityType.ENEMY){
            owner.setAlive(false);
            System.out.println("Player Dead!" + eId.toString());
        }
        eId = null;
        collided = false;
    }
    
    public void setMaxStrength(int strength)
    {
        maxStrength = strength;
    }
    
    @Override
    public void reset()
    {
        super.reset();
        currStrength = maxStrength;
        shielded = false;
    }
}
