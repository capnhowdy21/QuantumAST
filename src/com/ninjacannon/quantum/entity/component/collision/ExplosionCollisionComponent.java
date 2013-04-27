
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.level.EntityFactory;

/**
 * @author Dan Cannon
 */
public class ExplosionCollisionComponent extends CollisionComponent
{
    private static double energyRate = .20;
    private static final double baseEnergyRate = .20;
    public ExplosionCollisionComponent(String id, Allegiance allegiance){
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
            health--;
            if(health <=0){
                owner.setAlive(false);
                EntityManager.manager.addEntity(EntityFactory.createExplosion(owner.getPosition(), .5f));
                EntityManager.manager.score += 10;
                if(Math.random() < energyRate){
                    EntityManager.manager.addEntity(EntityFactory.createEnergy(owner.getPosition()));
                    energyRate = baseEnergyRate;
                } else {
                    energyRate += .025;
                }
            }
        }
    }
}
