
package com.ninjacannon.quantum.entity;

import com.ninjacannon.quantum.QuantumGame;
import com.ninjacannon.quantum.level.Level;
import com.ninjacannon.quantum.level.Wave;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class SceneManager 
{
    int timer;
    int delay;
    Level level;
    
    public SceneManager(int difficulty, int length)
    {
        this.delay = 3000;
        this.timer = 0;
        level = new Level(difficulty, length);
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        timer += delta;
        if(timer > delay){
            timer -= delay;
            String wave = level.nextWave();
            if(wave != null){
                System.out.println("Wave");
                delay = Wave.createWave(wave);
            } else {
                sbg.enterState(QuantumGame.MAINMENUSTATE);
            }
        }
    }

    
}
