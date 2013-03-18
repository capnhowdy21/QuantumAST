
package com.ninjacannon.quantum.entity.component;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.Entity.EntityType;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.collision.CollisionComponent.Allegiance;
import com.ninjacannon.quantum.entity.component.collision.NormalCollisionComponent;
import com.ninjacannon.quantum.entity.component.movement.LinearMovementComponent;
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
    private float bulletSpeed;
    private int spread;
    private boolean firing;
    private static final float ySpeed[] = {0, -.25f, .25f, -.125f, .125f, -.375f, -.375f, -.5f, .5f};
    
    public GunComponent(String id)
    {
        super(id); 
        lastFired = 0;
        bullet = null;
        firing = false;
        fireInterval = 250;
        bulletSpeed = 1;
        setSpread(1);
    }
    
    public GunComponent(String id, int interval, float bulletSpeed, int spread, boolean firing)
    {
        this(id);
        fireInterval = interval;
        this.bulletSpeed = bulletSpeed;
        this.firing = firing;
        setSpread(spread);
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
            for(int i = 0; i < spread; i++){
                bullet = new Entity(EntityType.BULLET);
                if(owner.getId() == EntityType.FRIENDLY || owner.getId() == EntityType.PLAYER){
                    bullet.AddComponent(new ImageRenderComponent("Render", "green bullet"));
                    bullet.AddComponent(new NormalCollisionComponent("Collision", Allegiance.PLAYER));                                
                    bullet.setPosition(owner.getPosition()
                        .add(new Vector2f(owner.getWidth(), + owner.getHeight()/2)));
                }
                else {
                    bullet.setHeight(i);
                    bullet.AddComponent(new ImageRenderComponent("Render", "red bullet"));
                    bullet.AddComponent(new NormalCollisionComponent("Collision", Allegiance.ENEMY));           
                    bullet.setPosition(owner.getPosition()
                        .add(new Vector2f(0, + owner.getHeight()/2)));
                }
                bullet.setHeight(3);
                bullet.setWidth(5);
                bullet.AddComponent(new LinearMovementComponent("Movement", bulletSpeed, ySpeed[i])); 
                EntityManager.manager.addEntity(bullet);
            }
        }
    }

    public void setFireSpeed(int interval){
        fireInterval = interval;
    }
    
    public void setBulletSpeed(float bulletSpeed){
        this.bulletSpeed = bulletSpeed;
    }
    
    public final void setSpread(int spread){
        if(spread < 1){
            this.spread = 0;
        } else if(spread > 9){
            this.spread = 9;
        } else if(spread % 2 != 1){
            this.spread = spread - 1;
        } else {
            this.spread = spread;
        }
    }
    
    @Override
    public void reset()
    {
        lastFired = 0;
        firing = false;
        bullet = null;
    }
}
