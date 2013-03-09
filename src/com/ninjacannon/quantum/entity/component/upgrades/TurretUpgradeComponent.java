
package com.ninjacannon.quantum.entity.component.upgrades;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.GunComponent;
import com.ninjacannon.quantum.entity.component.TimedComponent;
import com.ninjacannon.quantum.entity.component.collision.CollisionComponent.Allegiance;
import com.ninjacannon.quantum.entity.component.collision.NormalCollisionComponent;
import com.ninjacannon.quantum.entity.component.render.ImageRenderComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class TurretUpgradeComponent extends UpgradeComponent
{
    private boolean alive;
    Entity turret;
    public TurretUpgradeComponent(String id)
    {
        super(id);
        energyCost = 50;
        alive = false;
    }
    
    @Override
    public void activate()
    {   
        if(!alive){
            turret = new Entity(Entity.EntityType.FRIENDLY);
            turret.AddComponent(new ImageRenderComponent("Render", "turret"));
            turret.AddComponent(new NormalCollisionComponent("Collision", Allegiance.PLAYER));
            turret.AddComponent(new TimedComponent("Timer", 20000));
            turret.AddComponent(new GunComponent("Gun", 750, .5f, true));
            turret.setAlive(true);
            turret.setPostition(owner.getPosition().x, owner.getPosition().y + 100);
            ((GunComponent)turret.getComponent("Gun")).setFiring(true);
            EntityManager.manager.addEntity(turret);
        }
    }

    @Override
    public void reset()
    {
        deactivate();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        if(turret != null){
            turret.setPostition(owner.getPosition().x, owner.getPosition().y + 100);
            alive = turret.isAlive();
        }
    }
    
    @Override
    public void deactivate()
    {
        if(turret != null){
            turret.setAlive(false);
        }
        turret = null;
        alive = false;
    }
}
