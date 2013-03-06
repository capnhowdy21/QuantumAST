
package com.ninjacannon.quantum.entity.component.collision;

/**
 * @author Dan Cannon
 */
public class InvulnerableCollisionComponent extends CollisionComponent
{
    
    public InvulnerableCollisionComponent(String id){
        super(id);
    }
    
    @Override
    public void collide()
    {
        //do nothing.
    }

}
