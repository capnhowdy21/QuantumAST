
package com.ninjacannon.quantum.entity.component;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.Entity.EntityType;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.collision.CollisionComponent.Allegiance;
import com.ninjacannon.quantum.entity.component.collision.ExplosionCollisionComponent;
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
    
    public GunComponent(String id)
    {
        super(id); 
        lastFired = 0;
        bullet = null;
        firing = false;
        fireInterval = 250;
        bulletSpeed = 1;
        spread = 1;
    }
    
    public GunComponent(String id, int interval, float bulletSpeed, boolean firing)
    {
        this(id);
        fireInterval = interval;
        this.bulletSpeed = bulletSpeed;
        this.firing = firing;
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
                bullet.AddComponent(new LinearMovementComponent("Movement", bulletSpeed, i)); 
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
    
    @Override
    public void reset()
    {
        lastFired = 0;
        firing = false;
        bullet = null;
    }
}
