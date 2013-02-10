
package com.ninjacannon.quantum.entity;

import com.ninjacannon.quantum.entity.component.collision.NormalCollisionComponent;
import com.ninjacannon.quantum.entity.component.movement.HorizontalMovementComponent;
import com.ninjacannon.quantum.entity.component.render.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class SceneManager 
{
    int timer;
    Entity mob;
    
    public SceneManager(){
        this.timer = 0;
        
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        timer += delta;
        if(timer > 1000){
            SpawnWave(gc.getScreenWidth());
            timer -= 2000;
        }
    }
    
    private void SpawnWave(int width)
    {   
        for(int i = 0; i < 9; i++){
            mob = new Entity("Enemy");
            mob.AddComponent(new ImageRenderComponent("Render", ImageLibrary.enemy));
            mob.AddComponent(new NormalCollisionComponent("Collision"));
            mob.AddComponent(new HorizontalMovementComponent("Movement", -.5f));
            mob.setHeight(64);
            mob.setWidth(64);
            mob.setScale(.75f);
            mob.setRotation(-90f);
            mob.setPosition(new Vector2f(width, (80*i) + 20));
            EntityManager.manager.addEntity(mob);
        }
    }
}
