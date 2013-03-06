
package com.ninjacannon.quantum.entity.component.upgrades;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.TimedComponent;
import com.ninjacannon.quantum.entity.component.collision.NukeCollisionComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class NukeUpgradeComponent extends UpgradeComponent
{
    private Entity nuke;
    public boolean fired;
    
    public NukeUpgradeComponent(String id)
    {
        super(id);
        fired = false;
        energyCost = 100;
    }
    
    @Override
    public void activate(){
        fired = true;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        if(fired){
            nuke = new Entity(Entity.EntityType.FRIENDLY);
            nuke.setPosition(0,0);
            nuke.AddComponent(new NukeCollisionComponent("Collision"));
            nuke.AddComponent(new TimedComponent("Timer", 40));
            nuke.setHeight(gc.getHeight());
            nuke.setWidth(gc.getWidth());
            nuke.setAlive(true);
            EntityManager.manager.addEntity(nuke);
            fired = false;
        }
    }
    
    public void reset(){
        fired = false;
        nuke.setAlive(false);
    }
}