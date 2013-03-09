
package com.ninjacannon.quantum.entity.component.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class ImageRenderComponent extends RenderComponent
{
    Image image;
    
    public ImageRenderComponent(String id, String ref)
    {
        super(id);
        this.image = ImageLibrary.getInstance().getImage(ref);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {
        Vector2f pos = owner.getPosition();
        float scale = owner.getScale();
        image.draw(pos.x, pos.y, scale);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta){
        image.rotate(owner.getRotation() - image.getRotation());
    }
    
    @Override
    public void reset(){
        //do nothing;
    }
}
