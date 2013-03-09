
package com.ninjacannon.quantum.entity.component.upgrades;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.TimedComponent;
import com.ninjacannon.quantum.entity.component.collision.CollisionComponent;
import com.ninjacannon.quantum.entity.component.collision.CollisionComponent.Allegiance;
import com.ninjacannon.quantum.entity.component.collision.NormalCollisionComponent;
import com.ninjacannon.quantum.entity.component.render.AnimRenderComponent;
import com.ninjacannon.quantum.entity.component.render.ImageLibrary;
import java.util.ArrayList;
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
    private int maxMines;
    private Entity mine;
    private ArrayList<Entity> mines;
    
    public MineUpgradeComponent(String id){
        super(id);
        fired = false;
        lastFired = 0;
        firedInterval = 250;
        maxMines = 5;
        energyCost = 10;
        mines = new ArrayList<Entity>();
    }
    
    public void setFiredInterval(int interval){
        this.firedInterval = interval;
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
        if(fired && lastFired >= firedInterval && !(mines.size() >= maxMines)){
            lastFired = 0;
            mine = new Entity(Entity.EntityType.FRIENDLY);
            mine.AddComponent(new AnimRenderComponent("Render", "mine", 100, true));
            mine.AddComponent(new TimedComponent("Timer", 12000));
            mine.setPosition(owner.getPosition());
            mine.setHeight(32);
            mine.setWidth(32);
            mine.AddComponent(new NormalCollisionComponent("Collision", Allegiance.PLAYER));
            EntityManager.manager.addEntity(mine);
            mines.add(mine);
        }
        
        fired = false;
        
        for(int i = 0; i < mines.size(); i++){
            if(!mines.get(i).isAlive()){
                mines.remove(i);
            }
        }
    }
    
    @Override
    public void reset()
    {
        deactivate();
    }
    
    @Override
    public void deactivate()
    {
        for(int i = 0; i < mines.size(); i++){
            mines.get(i).setAlive(false);
        }
        fired = false;
        lastFired = 0;
        mine = null;
        mines.clear();
    }
}
