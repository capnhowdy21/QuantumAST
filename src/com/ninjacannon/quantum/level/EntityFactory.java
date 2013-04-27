
package com.ninjacannon.quantum.level;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.component.GunComponent;
import com.ninjacannon.quantum.entity.component.collision.CollisionComponent.Allegiance;
import com.ninjacannon.quantum.entity.component.collision.ExplosionCollisionComponent;
import com.ninjacannon.quantum.entity.component.collision.NormalCollisionComponent;
import com.ninjacannon.quantum.entity.component.movement.LinearMovementComponent;
import com.ninjacannon.quantum.entity.component.movement.SineMovementComponent;
import com.ninjacannon.quantum.entity.component.render.AnimRenderComponent;
import com.ninjacannon.quantum.entity.component.render.ImageRenderComponent;
import com.ninjacannon.quantum.entity.component.render.SingleAnimRenderComponent;
import org.newdawn.slick.geom.Vector2f;

/**
 * @author Dan Cannon
 */
public final class EntityFactory 
{
    public static Entity createSuicide(int x, int y, float dx, float dy)
    {
        Entity e = new Entity(Entity.EntityType.ENEMY);
        e.setWidth(27);
        e.setHeight(24);
        e.setPosition(x, y);
        e.AddComponent(new ImageRenderComponent("Render", "suicide"));
        e.AddComponent(new ExplosionCollisionComponent("Collision", Allegiance.ENEMY));
        e.AddComponent(new LinearMovementComponent("Movement", dx, dy)); 
        e.getCollision().setPoints(10);
        return e;
    }
    
    public static Entity createSwirve(int x, int y, float dx, float midY, float peak, float interval)
    {
        Entity e = new Entity(Entity.EntityType.ENEMY);
        e.setWidth(27);
        e.setHeight(24);
        e.setPosition(x, y);
        e.AddComponent(new ImageRenderComponent("Render", "swirve"));
        e.AddComponent(new ExplosionCollisionComponent("Collision", Allegiance.ENEMY));
        e.AddComponent(new SineMovementComponent("Movemenet", dx, midY, peak, interval));
        e.getCollision().setPoints(20);
        return e;
    }
    
    public static Entity createTitan(int x, int y, float dx, float dy)
    {
        Entity e = new Entity(Entity.EntityType.ENEMY);
        e.setWidth(55);
        e.setHeight(54);
        e.setPosition(x, y);
        e.AddComponent(new ImageRenderComponent("Render", "titan"));
        e.AddComponent(new ExplosionCollisionComponent("Collision", Allegiance.ENEMY));
        e.getCollision().setHealth(3);
        e.AddComponent(new GunComponent("Gun", 1000, -.375f, 5, true));
        e.AddComponent(new LinearMovementComponent("Movement", dx, dy)); 
        e.getCollision().setPoints(100);
        return e;
    }
    
    public static Entity createShooter(int x, int y, float dx, float dy)
    {
        Entity e = new Entity(Entity.EntityType.ENEMY);
        e.setWidth(46);
        e.setHeight(20);
        e.setPosition(x, y);
        e.AddComponent(new ImageRenderComponent("Render", "shooter"));
        e.AddComponent(new ExplosionCollisionComponent("Collision", Allegiance.ENEMY));
        e.AddComponent(new LinearMovementComponent("Movement", dx, dy)); 
        e.AddComponent(new GunComponent("Gun", 1500, -.375f, 1, true));
        e.getCollision().setPoints(30);
        return e;
    }
    
    public static Entity createCloak(int x, int y, float dx, float dy){
        Entity e = new Entity(Entity.EntityType.ENEMY);
        e.setWidth(27);
        e.setHeight(24);
        e.setPosition(x, y);
        e.AddComponent(new AnimRenderComponent("Render", "cloak", 250, true));
        e.AddComponent(new ExplosionCollisionComponent("Collision", Allegiance.ENEMY));
        e.AddComponent(new LinearMovementComponent("Movement", dx, dy)); 
        e.getCollision().setPoints(50);
        return e;
    }
        
    public static Entity createEnergy(Vector2f vector)
    {
        Entity e = new Entity(Entity.EntityType.POWERUP);
        e.setPosition(vector);
        e.AddComponent(new ImageRenderComponent("Render", "energy"));
        e.AddComponent(new LinearMovementComponent("Movement", -.1f, 0f));
        e.AddComponent(new NormalCollisionComponent("Collision", Allegiance.PLAYER));
        e.getCollision().setPoints(5);
        return e;
    }
    
    public static Entity createExplosion(Vector2f vector, float scale)
    {
        Entity e = new Entity(Entity.EntityType.EFFECT);
        e.setPosition(vector);
        e.AddComponent(new SingleAnimRenderComponent("Render", 
                "explosion", 8, 2, 20));
        e.setScale(scale);
        return e;
    }
}
