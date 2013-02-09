
package com.ninjacannon.quantum.entity.component.render;

import com.ninjacannon.quantum.entity.component.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public abstract class RenderComponent extends Component
{
    public RenderComponent(String id){
        super(id);
    }
    
    public abstract void render(GameContainer gc, StateBasedGame sb, Graphics g);
}
