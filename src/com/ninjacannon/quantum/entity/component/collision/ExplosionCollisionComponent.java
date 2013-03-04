
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.render.AnimRenderComponent;
import com.ninjacannon.quantum.entity.component.render.ImageLibrary;

/**
 * @author Dan Cannon
 */
public class ExplosionCollisionComponent extends CollisionComponent
{
    
    public ExplosionCollisionComponent(String id){
        super(id);
    }

    @Override
    public void collide()
    {
        owner.setAlive(false);
        Entity explosion = new Entity(Entity.EntityType.EFFECT);
        explosion.setPosition(owner.getPosition());
        explosion.setHeight(64);
        explosion.setWidth(64);
        explosion.AddComponent(new AnimRenderComponent("redner", 
                ImageLibrary.explosion, 8, 3, 20));
        explosion.setScale(.5f);
        EntityManager.manager.addEntity(explosion);
    }
}
