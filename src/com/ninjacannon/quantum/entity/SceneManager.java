
package com.ninjacannon.quantum.entity;

import com.ninjacannon.quantum.entity.component.collision.ExplosionCollisionComponent;
import com.ninjacannon.quantum.entity.component.movement.HorizontalMovementComponent;
import com.ninjacannon.quantum.entity.component.movement.SineMovementComponent;
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
    int type;
    Entity mob;
    
    public SceneManager(){
        this.timer = 3000;
        type = 0;
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        timer += delta;
        if(timer > 4000){
            SpawnWave(gc.getScreenWidth(), gc.getHeight(), type);
            type++;
            timer -= 4000;
        }
    }
    
    private void SpawnWave(int width, int height, int type)
    {
        if(type % 2 == 1){
            SpawnStraightWave(width, height);
        } else {
            SpawnSineWave(width, height);
        }
    }
    
    private void SpawnSineWave(int width, int height)
    {   
        for(int i = 0; i < 9; i++){
            mob = new Entity(Entity.EntityType.ENEMY);
            mob.AddComponent(new ImageRenderComponent("Render", ImageLibrary.enemy));
            mob.AddComponent(new ExplosionCollisionComponent("Collision"));
            mob.setHeight(64);
            mob.setWidth(64);
            mob.setScale(.75f);
            mob.setRotation(-90f);
            mob.setPosition(new Vector2f(width + ((i) * 70), height/2));
            SineMovementComponent sine = new SineMovementComponent("Movement");
            sine.setMid(mob.position.y);
            mob.AddComponent(sine);
            EntityManager.manager.addEntity(mob);
        }
    }
    
    private void SpawnStraightWave(int width, int height)
    {   
        for(int i = 0; i < 9; i++){
            mob = new Entity(Entity.EntityType.ENEMY);
            mob.AddComponent(new ImageRenderComponent("Render", ImageLibrary.enemy));
            mob.AddComponent(new ExplosionCollisionComponent("Collision"));
            mob.setHeight(64);
            mob.setWidth(64);
            mob.setScale(.75f);
            mob.setRotation(-90f);
            mob.setPosition(new Vector2f(width, (i)*80 + 20));
            HorizontalMovementComponent move = new HorizontalMovementComponent("Movement");
            mob.AddComponent(move);
            EntityManager.manager.addEntity(mob);
        }
    }
}
