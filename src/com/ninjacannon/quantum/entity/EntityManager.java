
package com.ninjacannon.quantum.entity;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public final class EntityManager 
{
    
    public final static EntityManager manager = new EntityManager();
    private ArrayList<Entity> entities;
    
    private EntityManager()
    {
        entities = new ArrayList<Entity>();
    }
    
    public synchronized void addEntity(Entity e){
        entities.add(e);        
    }
    
    public void removeEntity(Entity e){
        entities.remove(e);
    }
    
    public Entity getEntity(String id){
        for(int i = 0; i < entities.size(); i++){
            if(entities.get(i).getId().equalsIgnoreCase(id)){
                return entities.get(i);
            }
        }
        return null;
    }
      
    public synchronized void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        
        for(int i = 0; i < entities.size(); i++){
            entities.get(i).update(gc, sbg, delta);
            if(entities.get(i).collisionComponent != null){
                for(int j = i; j < entities.size(); j++){
                    entities.get(i).collisionComponent.collided(entities.get(j));
                }
            }
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {
        for(int i = 0; i < entities.size(); i++){
            entities.get(i).render(gc, sbg, g);
        }
    }
}
