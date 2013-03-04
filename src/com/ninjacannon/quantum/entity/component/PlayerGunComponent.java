
package com.ninjacannon.quantum.entity.component;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.collision.NormalCollisionComponent;
import com.ninjacannon.quantum.entity.component.movement.HorizontalMovementComponent;
import com.ninjacannon.quantum.entity.component.render.ImageLibrary;
import com.ninjacannon.quantum.entity.component.render.ImageRenderComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class PlayerGunComponent extends Component{
    
    private Entity bullet;
    private int lastFired = 0;
    private int fireInterval = 250;
    private boolean firing;
    
    public PlayerGunComponent(String id){
        super(id); 
        firing = false;
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
            lastFired = 0;
            bullet = new Entity(Entity.EntityType.FRIENDLY);
            bullet.AddComponent(new ImageRenderComponent("Render", ImageLibrary.bullet));
            bullet.AddComponent(new HorizontalMovementComponent("Movement", 1));
            bullet.AddComponent(new NormalCollisionComponent("Collision"));
            bullet.setPosition(owner.getPosition()
                    .add(new Vector2f(owner.getWidth(), + owner.getHeight()/2)));
            EntityManager.manager.addEntity(bullet);
        }
    }

    public void setFireSpeed(int interval){
        fireInterval = interval;
    }
    
    public void reset()
    {
        lastFired = 0;
        firing = false;
    }
}
