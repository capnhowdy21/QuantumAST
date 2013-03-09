
package com.ninjacannon.quantum.entity;

import com.ninjacannon.quantum.entity.component.collision.ExplosionCollisionComponent;
import com.ninjacannon.quantum.entity.component.movement.LinearMovementComponent;
import com.ninjacannon.quantum.level.EntityFactory;
import com.ninjacannon.quantum.level.Wave;
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
    Wave level;
    
    public SceneManager()
    {
        this.timer = 3000;
        type = 0;
        level = new Wave();
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        timer += delta;
        if(timer > 4000){
            SpawnWave(type);
            type++;
            timer -= 4000;
            if(type > 2){
                type = 0;
            }
        }
    }
    
    private void SpawnWave(int type)
    {
        switch(type){
            case 0: SpawnSineWave();
                break;
            case 1: SpawnStraightWave();
                break;
            case 2: SpawnTitanWave();
                break;
        }
    }
    
    private void SpawnSineWave()
    {   
        for (int i = 0; i < 10; i++)
        {
            EntityManager.manager.addEntity(EntityFactory.createSwirve( 1280 + i * 84, 300, 300, 250, 500));
        }
    }
    
    private void SpawnStraightWave()
    {   
        for (int i = 0; i < 10; i++)
        {
            EntityManager.manager.addEntity(EntityFactory.createSuicide( 1280, i*84 + 10));
        }
    }
    
        private void SpawnTitanWave()
    {   
        for (int i = 0; i < 5; i++)
        {
            EntityManager.manager.addEntity(EntityFactory.createTitan(1280, i * 200 + 20));
        }
    }
}
