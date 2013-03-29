
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.level.EntityFactory;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author Dan Cannon
 */
public class MineCollisionComponent extends CollisionComponent
{

    public MineCollisionComponent(String id, Allegiance allegiance){
        super(id, allegiance);
    }
    
    @Override
    public void collide()
    {
        if(other.getId() != Entity.EntityType.POWERUP && allegiance != other.getCollision().allegiance &&
                other.getId() != Entity.EntityType.BULLET){
            if(other.isAlive()){
                other.getCollision().setCollision(owner);
            }
            other = null;
            collided = false;
            health--;
            if(health <= 0){
                owner.setAlive(false);
                Entity explosion = EntityFactory.createExplosion(owner.getPosition().sub(new Vector2f(32, 32)), 1f);
                explosion.setHeight(64);
                explosion.setWidth(64);
                explosion.AddComponent(new InvulnerableCollisionComponent("Collision", allegiance));
                EntityManager.manager.addEntity(explosion);
            }
        }
    }

}
