
package com.ninjacannon.quantum.entity;

import com.ninjacannon.quantum.entity.component.Component;
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
    private String id;
    private Vector2f position;
    private float scale;
    private float rotation;
    
    RenderComponent renderComponent = null;
    ArrayList<Component> components = null;
    
    public Entity(String id)
    {
        this.id = id;
        
        components = new ArrayList<Component>();
        
        position = new Vector2f(0,0);
        scale = 1;
        rotation = 0;
    }
    
    public Entity(String id, Vector2f pos)
    {
        this(id);
        position = pos;
    }
    
    public void AddComponent(Component component)
    {
        if(RenderComponent.class.isInstance(component)){
            renderComponent = (RenderComponent)component;
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
    
    public Vector2f getPosition(){
        return position;
    }
    
    public void setPosition(Vector2f position){
        this.position = position;
    }
    
    public float getScale(){
        return scale;
    }
    
    public void setScale(float scale){
        this.scale = scale;
    }
    
    public float getRotation(){
        return rotation;
    }
    
    public void setRotation(float rotation){
        this.rotation = rotation;
    }
    
    public String getId(){
        return id;
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
