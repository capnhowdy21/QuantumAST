
package com.ninjacannon.quantum.entity.component;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.Entity.EntityType;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.SoundLibrary;
import com.ninjacannon.quantum.entity.component.collision.NormalCollisionComponent;
import com.ninjacannon.quantum.entity.component.movement.LinearMovementComponent;
import com.ninjacannon.quantum.entity.component.render.ImageLibrary;
import com.ninjacannon.quantum.entity.component.render.ImageRenderComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class GunComponent extends Component{
    
    private Entity bullet;
    private int lastFired;
    private int fireInterval;
    private boolean firing;
    private EntityType type;
    
    public GunComponent(String id, EntityType type)
    {
        super(id); 
        lastFired = 0;
        bullet = null;
        firing = false;
        fireInterval = 250;
        this.type = type;
    }
    
    public GunComponent(String id, EntityType type, int interval)
    {
        this(id, type);
        fireInterval = interval;
    }
    
    public void setFiring(boolean firing){
        this.firing = firing;
    }
    
    public boolean isFiring(){
        return firing;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        lastFired += delta;
        if(firing && lastFired >= fireInterval){
            SoundLibrary.FX_LASER.play(.5f, .40f);
            lastFired = 0;
            bullet = new Entity(type);
            bullet.AddComponent(new ImageRenderComponent("Render", ImageLibrary.bullet));
            bullet.AddComponent(new LinearMovementComponent("Movement", 1, 0));
            bullet.AddComponent(new NormalCollisionComponent("Collision"));
            bullet.setPosition(owner.getPosition()
                    .add(new Vector2f(owner.getWidth(), + owner.getHeight()/2)));
            EntityManager.manager.addEntity(bullet);
        }
    }

    public void setFireSpeed(int interval){
        fireInterval = interval;
    }
    
    @Override
    public void reset()
    {
        lastFired = 0;
        firing = false;
        bullet = null;
    }
}
