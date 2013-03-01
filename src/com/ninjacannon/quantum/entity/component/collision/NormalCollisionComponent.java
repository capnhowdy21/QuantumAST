
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.EntityManager;

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
        owner.setAlive(false);
    }

}
