
package com.ninjacannon.quantum.entity;

import com.ninjacannon.quantum.entity.component.Component;
import com.ninjacannon.quantum.entity.component.collision.CollisionComponent;
import com.ninjacannon.quantum.entity.component.render.RenderComponent;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class Entity 
{
    public enum EntityType{PLAYER, FRIENDLY, BULLET, ENEMY, EFFECT};
    protected EntityType id;
    protected Vector2f position;
    protected int width;
    protected int height;
    protected float scale;
    protected float rotation;
    protected boolean alive;
    
    protected CollisionComponent collisionComponent = null;
    protected RenderComponent renderComponent = null;
    private ArrayList<Component> components = null;
    
    public Entity(EntityType id)
    {
        this.id = id;
        
        components = new ArrayList<Component>();
        
        position = new Vector2f(0,0);
        width = 0;
        height = 0;
        scale = 1;
        rotation = 0;
        alive = true;
    }
    
    public Entity(EntityType id, Vector2f pos)
    {
        this(id);
        position = pos;
    }
    
    public void AddComponent(Component component)
    {
        if(RenderComponent.class.isInstance(component)){
            renderComponent = (RenderComponent)component;
        } else if (CollisionComponent.class.isInstance(component)){
            collisionComponent = (CollisionComponent)component;
        }
        
        component.setOwnerEntity(this);
        components.add(component);
    }
    
    public Component getComponent(String id)
    {
        for(Component component : components){
            if(component.getID().equalsIgnoreCase(id)){
                return component;
            }
        }
        return null;
    }
    
    public void RemoveComponent(String id)
    {
        for(Component component : components){
            if(component.getID().equalsIgnoreCase(id)){
                components.remove(component);
            }
        }
    }
    
    public Vector2f getPosition(){
        return position.copy();
    }
    
    public void setPosition(Vector2f position){
        this.position = position;
    }
    
    public void setPostition(float x, float y){
        this.position = new Vector2f(x, y);
    }
    
    public void setPosition(int x, int y){
        this.position = new Vector2f(x, y);
    }
    
    public float getScale(){
        return scale;
    }
    
    public void setScale(float scale){
        this.scale = scale;
        width = (int)(scale * width);
        height = (int)(scale * height);
    }
    
    public float getRotation(){
        return rotation;
    }
    
    public void setRotation(float rotation){
        this.rotation = rotation;
    }
    
    public int getWidth(){
        return width;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setHeight(int height){
        this.height = height;
    }
    
    public EntityType getId(){
        return id;
    }
    
    public boolean isAlive(){
        return alive;
    }
    
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    
    public CollisionComponent getCollision(){
        return collisionComponent;
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        for(Component component : components){
            component.update(gc, sbg, delta);
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {
        if(renderComponent != null){
            renderComponent.render(gc, sbg, g);
        }
    }
}
