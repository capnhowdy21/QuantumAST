
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.Entity.EntityType;

/**
 * @author Dan Cannon
 */
public class NormalCollisionComponent extends CollisionComponent
{
    public NormalCollisionComponent(String id){
        super(id);
    }    

    @Override
    public void collide()
    {
        if(owner.getId() == EntityType.FRIENDLY && eId == EntityType.PLAYER){
            //do nothing.
        } else if(owner.getId() != eId){
            owner.setAlive(false);
            System.out.println("Dead" + eId.toString());
        }
        
        eId = null;
        collided = false;
    }

}
