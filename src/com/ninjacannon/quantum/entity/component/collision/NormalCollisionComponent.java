
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.Entity;


/**
 * @author Dan Cannon
 */
public class NormalCollisionComponent extends CollisionComponent
{
    public NormalCollisionComponent(String id, Allegiance allegiance){
        super(id, allegiance);
    }    

    @Override
    public void collide()
    {
        if(owner.getId() != Entity.EntityType.POWERUP && allegiance != other.getCollision().allegiance){
            if(other.isAlive()){
                other.getCollision().setCollision(owner);
            }
            other = null;
            collided = false;
            health--;
            if(health <= 0){
                owner.setAlive(false);
            }
        } else if((other.getId() == Entity.EntityType.PLAYER && owner.getId() == Entity.EntityType.POWERUP)){
            other = null;
            collided = false;
            health--;
            if(health <= 0){
                owner.setAlive(false);
            }
        }
    }

}
