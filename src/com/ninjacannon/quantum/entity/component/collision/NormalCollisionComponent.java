
package com.ninjacannon.quantum.entity.component.collision;


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
        if(allegiance != eAllegiance){
            eId = null;
            eAllegiance = null;
            collided = false;
            owner.setAlive(false);
        }
    }

}
