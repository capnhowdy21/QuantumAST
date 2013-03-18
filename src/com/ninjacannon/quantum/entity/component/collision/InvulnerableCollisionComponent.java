
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.Entity;

/**
 * @author Dan Cannon
 */
public class InvulnerableCollisionComponent extends CollisionComponent
{
    
    public InvulnerableCollisionComponent(String id, Allegiance allegiance){
        super(id, allegiance);
    }
    
    @Override
    public void collide()
    {
        if(other.getId() != Entity.EntityType.POWERUP && allegiance != other.getCollision().allegiance){
            if(other.isAlive()){
                other.getCollision().setCollision(owner);
            }
            other = null;
            collided = false;
        }
    }

}
