
package com.ninjacannon.quantum.level;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.component.GunComponent;
import com.ninjacannon.quantum.entity.component.collision.CollisionComponent.Allegiance;
import com.ninjacannon.quantum.entity.component.collision.ExplosionCollisionComponent;
import com.ninjacannon.quantum.entity.component.movement.LinearMovementComponent;
import com.ninjacannon.quantum.entity.component.movement.SineMovementComponent;
import com.ninjacannon.quantum.entity.component.render.ImageRenderComponent;

/**
 * @author Dan Cannon
 */
public final class EntityFactory 
{
    public static Entity createSuicide(int x, int y)
    {
        Entity e = new Entity(Entity.EntityType.ENEMY);
        e.setWidth(27);
        e.setHeight(24);
        e.setPosition(x, y);
        e.AddComponent(new ImageRenderComponent("Render", "suicide"));
        e.AddComponent(new ExplosionCollisionComponent("Collision", Allegiance.ENEMY));
        e.AddComponent(new LinearMovementComponent("Movement", -.35f, 0f)); 
        return e;
    }
    
    public static Entity createSwirve(int x, int y, float midY, float peak, float interval)
    {
        Entity e = new Entity(Entity.EntityType.ENEMY);
        e.setWidth(27);
        e.setHeight(24);
        e.setPosition(x, y);
        e.AddComponent(new ImageRenderComponent("Render", "swirve"));
        e.AddComponent(new ExplosionCollisionComponent("Collision", Allegiance.ENEMY));
        e.AddComponent(new SineMovementComponent("Movemenet", -.35f, midY, peak, interval));
        return e;
    }
    
    public static Entity createTitan(int x, int y)
    {
        Entity e = new Entity(Entity.EntityType.ENEMY);
        e.setWidth(55);
        e.setHeight(54);
        e.setPosition(x, y);
        e.AddComponent(new ImageRenderComponent("Render", "titan"));
        e.AddComponent(new ExplosionCollisionComponent("Collision", Allegiance.ENEMY));
        e.AddComponent(new GunComponent("Gun", 1000, -.50f, true));
        e.AddComponent(new LinearMovementComponent("Movement", -.1f, 0f)); 
        return e;
    }
}
