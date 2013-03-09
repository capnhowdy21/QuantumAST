
package com.ninjacannon.quantum;

import com.ninjacannon.quantum.entity.component.render.ImageLibrary;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.*;


/**
 * @author Dan Cannon
 */
public class MainMenuState extends BasicGameState
{
    private int stateID = -1;
    
    private Image background = null;
    private Image startOption = null;
    private Image exitOption = null;
    
    private static int menuX = 592;
    private static int menuY = 350;
    
    private float startGameScale = 1;
    private float exitScale = 1;
    private float scaleStep = 0.0001f;
    
    MainMenuState(int stateID){
        this.stateID = stateID;
    }
    
    @Override
    public int getID(){
        return stateID;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) 
            throws SlickException
    {   
        ImageLibrary.getInstance().init();
        background = ImageLibrary.getInstance().getImage("menu");
        startOption = ImageLibrary.getInstance().getImage("start");
        exitOption = ImageLibrary.getInstance().getImage("exit");
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException
    {
        background.draw(0, 0);
        startOption.draw(menuX, menuY, startGameScale);
        exitOption.draw(menuX + 6, menuY + 60, exitScale);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException
    {
        gc.setMouseGrabbed(false);
        Input input = gc.getInput();
  
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();
  
        boolean insideStartGame = false;
        boolean insideExit = false;
  
        if( ( mouseX >= menuX && mouseX <= menuX + startOption.getWidth()) &&
            ( mouseY >= menuY && mouseY <= menuY + startOption.getHeight()) )
        {
            insideStartGame = true;
        }else if( ( mouseX >= menuX + 6 && mouseX <= menuX + 6 + exitOption.getWidth()) &&
            ( mouseY >= menuY+60 && mouseY <= menuY+60 + exitOption.getHeight()) )
        {
            insideExit = true;
        }
  
        if(insideStartGame)
        {
            if(startGameScale < 1.05f){
                startGameScale += scaleStep * delta;
            }
  
            if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
                sbg.enterState(QuantumGame.GAMEPLAYSTATE);
            }
        }else{
            if(startGameScale > 1.0f){
                startGameScale -= scaleStep * delta;
            }
        }
  
        if(insideExit)
        {
            if(exitScale < 1.05f){
                exitScale +=  scaleStep * delta;
            }
            
            if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
                gc.exit();
            }
        }else{
            if(exitScale > 1.0f){
                exitScale -= scaleStep * delta;
            }
        }
    }
}
