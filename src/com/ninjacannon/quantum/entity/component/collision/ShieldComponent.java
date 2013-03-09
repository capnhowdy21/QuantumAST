
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
    private float maxStrength; 
    private float currStrength;
    private float recharge;
    private float decay;
    private int delay;
    
    public ShieldComponent(String id, Allegiance allegiance, float max)
    {
        super(id, allegiance);
        shielded = false;
        this.maxStrength = max;
        currStrength = max;
        recharge = .2f;
        decay = .01f;
        delay = 2000;
    }
    
    public void setShielded(boolean shielded)
    {
        if (currStrength <= 0){
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
            delay = 2000;
            currStrength-= (decay*delta);
            if(currStrength <= 0){
                setShielded(false);
                currStrength = 0;
                delay = 3000;
            }
        }else if(currStrength < maxStrength){
            delay -= delta;
            if(delay < 0){
                currStrength+= (recharge * delta);
                delay = 0;
            }
        }
        if(collided){
            collide();
        }
    }

    @Override
    public void collide()
    {
        if(allegiance != eAllegiance){
            if(eId == EntityType.BULLET && shielded){
                currStrength--;
            } else {
                owner.setAlive(false);
            }
            eId = null;
            collided = false;
        }
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
