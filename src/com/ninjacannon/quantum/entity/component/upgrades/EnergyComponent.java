
package com.ninjacannon.quantum.entity.component.upgrades;

import com.ninjacannon.quantum.entity.component.Component;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class EnergyComponent extends Component
{
    
    private int maxEnergy;
    private int currEnergy;
    
    public EnergyComponent(String id, int maxEnergy)
    {
        super(id);
        this.maxEnergy = maxEnergy;
        currEnergy = 0;
    }
    
    public int getEnergy(){
        return currEnergy;
    }
    
    public int getMaxEnergy(){
        return maxEnergy;
    }
    
    public void addEnergy(int energy)
    {
        currEnergy += energy;
        if(currEnergy > maxEnergy){
            currEnergy = maxEnergy;
        }
    }
            
    public void removeEnergy(int energy)
    {
        currEnergy -= energy;
        if(currEnergy < 0){
            currEnergy = 0;
        }
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta)
    {
        if(currEnergy > maxEnergy){
            currEnergy = maxEnergy;
        } else if(currEnergy < 0){
            currEnergy = 0;
        }
    }

    @Override
    public void reset()
    {
        currEnergy = 0;
    }

}
