
package com.ninjacannon.quantum.entity.component.upgrades;

import com.ninjacannon.quantum.entity.component.Component;

/**
 * @author Dan Cannon
 */
public abstract class UpgradeComponent extends Component
{
    protected EnergyComponent energy;
    
    public UpgradeComponent(String id, EnergyComponent ship){
        super(id);
        this.energy = ship;
    }
    
    
    protected int energyCost;
    
    public int getEnergyCost(){
        return energyCost;
    }
    
    abstract public void activate();
    abstract public void deactivate();
}
