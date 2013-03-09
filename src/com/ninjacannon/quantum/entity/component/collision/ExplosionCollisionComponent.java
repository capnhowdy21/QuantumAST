
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.render.SingleAnimRenderComponent;

/**
 * @author Dan Cannon
 */
public class ExplosionCollisionComponent extends CollisionComponent
{
    
    public ExplosionCollisionComponent(String id, Allegiance allegiance){
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
            Entity explosion = new Entity(Entity.EntityType.EFFECT);
            explosion.setPosition(owner.getPosition());
            explosion.AddComponent(new SingleAnimRenderComponent("Render", 
                    "explosion", 8, 3, 20));
            explosion.setScale(.5f);
            EntityManager.manager.addEntity(explosion);
        }
    }
}
