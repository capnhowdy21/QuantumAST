
package com.ninjacannon.quantum.level;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class SceneManager 
{
    private int timer;
    private int delay;
    private boolean levelComplete;
    private Level level;
    
    public SceneManager(int difficulty, int length)
    {
        this.delay = 3000;
        this.timer = 0;
        level = new Level(difficulty, length);
        levelComplete = false;
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        timer += delta;
        if(timer > delay){
            timer -= delay;
            String wave = level.nextWave();
            if(wave != null){
                delay = Wave.createWave(wave);
            } else {
                levelComplete = true;
            }
        }
    }

    public boolean isComplete(){
        return levelComplete;
    }
}
