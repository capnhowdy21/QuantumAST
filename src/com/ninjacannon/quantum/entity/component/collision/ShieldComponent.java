
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.Entity.EntityType;
import com.ninjacannon.quantum.entity.component.upgrades.EnergyComponent;
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
    private int shieldDelay;
    private boolean invulnerable;
    private int invulnerableDelay;
    
    public ShieldComponent(String id, Allegiance allegiance, float max)
    {
        super(id, allegiance);
        shielded = false;
        this.maxStrength = max;
        currStrength = max;
        recharge = .02f;
        decay = .01f;
        shieldDelay = 2000;
        invulnerable = false;
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
    
    public float getShieldStr(){
        return currStrength;
    }
    
    public float getMaxStrength(){
        return maxStrength;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        if(shielded){
            shieldDelay = 3000;
            currStrength-= (decay*delta);
            if(currStrength <= 0){
                setShielded(false);
                currStrength = 0;
                shieldDelay = 4000;
            }
        }else if(currStrength < maxStrength){
            shieldDelay -= delta;
            if(shieldDelay < 0){
                currStrength+= (recharge * delta);
                shieldDelay = 0;
            }
        }
        if(invulnerable){
            invulnerableDelay += delta;
            if(invulnerableDelay >= 2000){
                invulnerableDelay = 0;
                invulnerable = false;
            }
        }
        if(collided){
            collide();
        }
    }

    @Override
    public void collide()
    {
        if(allegiance != other.getCollision().allegiance && !invulnerable){
            if(other.isAlive()){
                other.getCollision().setCollision(owner);
            }

            if(other.getId() == EntityType.BULLET && shielded){
                currStrength--;
            } else {
                owner.setAlive(false);
            }
        } else if(other.getId() ==Entity.EntityType.POWERUP && owner.getId() == Entity.EntityType.PLAYER){
            ((EnergyComponent)owner.getComponent("Energy")).addEnergy(5);
            other.getCollision().collides(owner);
        }
            other = null;
            collided = false;
    }
    
    public void setMaxStrength(int strength)
    {
        maxStrength = strength;
    }
    
    public void makeInvulnerable(){
        invulnerable = true;
    }
    
    @Override
    public void reset()
    {
        super.reset();
        currStrength = maxStrength;
        shielded = false;
        invulnerable = false;
    }
}
