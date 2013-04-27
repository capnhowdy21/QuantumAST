
package com.ninjacannon.quantum;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.component.render.ImageLibrary;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class OvermapState extends BasicGameState{

    private int stateID = -1;
    MouseOverArea easy;
    MouseOverArea med;
    MouseOverArea hard;

    
    OvermapState(int stateID){
        this.stateID = stateID;
    }
    
    @Override
    public int getID(){
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        easy = new MouseOverArea(gc, ImageLibrary.getInstance().getSheet("levels").getSubImage(2, 2), 384, 64);
        med = new MouseOverArea(gc, ImageLibrary.getInstance().getSheet("levels").getSubImage(0, 0), 128, 384);
        hard = new MouseOverArea(gc, ImageLibrary.getInstance().getSheet("levels").getSubImage(1, 1), 640, 384);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
         g.setColor(Color.cyan);
        easy.render(gc, g);
        ImageLibrary.getInstance().large.drawString(480, 320, "Easy");
        if(easy.isMouseOver()){        
            g.drawRect(384, 64, 250, 250);
        }
        med.render(gc, g);
        ImageLibrary.getInstance().large.drawString(208, 640, "Medium");
        if(med.isMouseOver()){        
            g.drawRect(128, 384, 250, 250);
        }
        hard.render(gc, g);
        ImageLibrary.getInstance().large.drawString(732, 640, "Hard");
        if(hard.isMouseOver()){        
            g.drawRect(640, 384, 250, 250);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {
        Input input = gc.getInput();
        if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
            if(easy.isMouseOver()){
                loadLevel(sbg, 1);
            } else if(med.isMouseOver()){
                loadLevel(sbg, 2);
            } else if(hard.isMouseOver()){
                loadLevel(sbg, 3);
            }
        }
    }
    
    private void loadLevel(StateBasedGame sbg, int difficulty)
    {
        if(sbg.getState(QuantumGame.GAMEPLAYSTATE) == null){
            sbg.addState(new GamePlayState(QuantumGame.GAMEPLAYSTATE));
        }
        ((GamePlayState)sbg.getState(QuantumGame.GAMEPLAYSTATE)).loadLevel(difficulty);
        sbg.enterState(QuantumGame.GAMEPLAYSTATE);
    }

}
