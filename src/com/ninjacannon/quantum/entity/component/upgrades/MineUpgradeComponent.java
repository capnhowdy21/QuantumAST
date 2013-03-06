
package com.ninjacannon.quantum.entity.component.upgrades;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.TimedComponent;
import com.ninjacannon.quantum.entity.component.collision.NormalCollisionComponent;
import com.ninjacannon.quantum.entity.component.render.ImageLibrary;
import com.ninjacannon.quantum.entity.component.render.LoopAnimRenderComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class MineUpgradeComponent extends UpgradeComponent
{
    
    private boolean fired;
    private int lastFired;
    private int firedInterval;
    private Entity mine;
    
    public MineUpgradeComponent(String id){
        super(id);
        fired = false;
        lastFired = 0;
        firedInterval = 250;
        energyCost = 10;
    }
    
    @Override
    public void activate()
    {
        fired = true;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        lastFired += delta;
        if(fired && lastFired >= firedInterval){
            lastFired = 0;
            mine = new Entity(Entity.EntityType.FRIENDLY);
            mine.AddComponent(new LoopAnimRenderComponent("Render", ImageLibrary.mine, 4, 1, 100));
            mine.AddComponent(new TimedComponent("Timer", 12000));
            mine.setPosition(owner.getPosition());
            mine.setHeight(32);
            mine.setWidth(32);
            mine.AddComponent(new NormalCollisionComponent("Collision"));
            EntityManager.manager.addEntity(mine);
        }
        fired = false;
    }
    
    @Override
    public void reset()
    {
        fired = false;
        mine = null;
        lastFired = 0;
    }
}
