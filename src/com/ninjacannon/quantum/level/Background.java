
package com.ninjacannon.quantum.level;

import com.ninjacannon.quantum.entity.component.render.ImageLibrary;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class Background 
{
    float parallax_1_offset = 0;
    float parallax_2_offset = 0;
    float parallax_3_offset = 0;
    
    float parallax_1_speed = .05f;
    float parallax_2_speed = .10f;
    float parallax_3_speed = .15f;
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {   /*
        g.setDrawMode(Graphics.MODE_NORMAL);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight(), 
                ImageLibrary.getInstance().getImage("background1"), parallax_1_offset, 0);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight(), 
                ImageLibrary.getInstance().getImage("background2"), parallax_2_offset, 10);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight(), 
                ImageLibrary.getInstance().getImage("background3"), parallax_3_offset, 30);
                */
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        if(parallax_1_offset > 500){
            parallax_1_offset -= 500;
        } else {
            parallax_1_offset += (parallax_1_speed * delta);
        }
        if(parallax_2_offset > 500){
            parallax_2_offset -= 500;
        } else {
            parallax_2_offset += (parallax_2_speed * delta);
        }
        if(parallax_3_offset > 500){
            parallax_3_offset -= 500;
        } else {
            parallax_3_offset += (parallax_3_speed * delta);
        }
    }
}
