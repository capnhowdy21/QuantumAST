
package com.ninjacannon.quantum.entity.component.render;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class AnimRenderComponent extends RenderComponent
{
    protected Animation anim;
    
    public AnimRenderComponent(String id, SpriteSheet images, int w, int h, int length, boolean pingpong)
    {
        super(id);
        anim = new Animation();
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                anim.addFrame(images.getSprite(x, y), length);
            }
        }
        anim.setAutoUpdate(false);
        anim.setLooping(true);
        anim.setPingPong(pingpong);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {
        Vector2f pos = owner.getPosition();
        float scale = owner.getScale();
        anim.getCurrentFrame().draw(pos.x, pos.y, scale);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        anim.update(delta);
    }
    
    @Override
    public void reset(){
        anim.setCurrentFrame(0);
    }
}
