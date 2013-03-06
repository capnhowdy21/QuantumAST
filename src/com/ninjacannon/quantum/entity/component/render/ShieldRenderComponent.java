
package com.ninjacannon.quantum.entity.component.render;

import com.ninjacannon.quantum.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class ShieldRenderComponent extends RenderComponent
{
    private AnimRenderComponent shield;
    private ImageRenderComponent ship;
    public boolean renderShield;
    
    public ShieldRenderComponent(String id, Image img, SpriteSheet ss, int w, int h, int l)
    {
        super(id);
        ship  = new ImageRenderComponent(id, img);
        shield = new AnimRenderComponent(id, ss, w, h, l, false);
        shield.setOwnerEntity(owner);
        ship.setOwnerEntity(owner);
        renderShield = false;
    }
    
    @Override
    public void setOwnerEntity(Entity owner)
    {
        this.owner = owner;
        shield.setOwnerEntity(owner);
        ship.setOwnerEntity(owner);
    }
    
    public void setRenderShield(boolean render){
        renderShield = render;
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics g)
    {
        ship.render(gc, sb, g);
        if(renderShield){
            shield.render(gc, sb, g);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        ship.update(gc, sb, delta);
        shield.update(gc, sb, delta);
    }

    @Override
    public void reset()
    {
        renderShield = false;
    }
}
