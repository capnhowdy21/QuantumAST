
package com.ninjacannon.quantum.level;

import com.ninjacannon.quantum.entity.component.render.ImageLibrary;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class Background 
{
    float offset = 0;
    Image background = ImageLibrary.getInstance().getImage("background");

    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {   
        g.setDrawMode(Graphics.MODE_NORMAL);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight(), 
                background, offset, 0);
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        if(offset >= 1280){
            offset = 0;
        }
        offset+= 1;
    }
}
