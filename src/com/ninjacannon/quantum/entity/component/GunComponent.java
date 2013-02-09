
package com.ninjacannon.quantum.entity.component;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.movement.HorizontalMovementComponent;
import com.ninjacannon.quantum.entity.component.render.ImageFactory;
import com.ninjacannon.quantum.entity.component.render.ImageRenderComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class GunComponent extends Component{
    
    private Entity bullet;
    private int lastFired = 0;
    private int fireInterval = 300;
    
    public GunComponent(String id){
        super(id); 
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        lastFired += delta;
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_SPACE) && lastFired >= fireInterval){
            lastFired = 0;
            bullet = new Entity("Bullet");
            bullet.AddComponent(new ImageRenderComponent("Render", ImageFactory.bullet));
            bullet.AddComponent(new HorizontalMovementComponent("Movement", 20));
            bullet.setPosition(owner.getPosition().copy()
                    .add(new Vector2f(owner.getWidth(), + owner.getHeight()/2)));
            EntityManager.manager.addEntity(bullet);
        }
    }

}
