
package com.ninjacannon.quantum.entity.component.upgrades;

import com.ninjacannon.quantum.entity.component.Component;

/**
 * @author Dan Cannon
 */
public abstract class UpgradeComponent extends Component
{
    public UpgradeComponent(String id){
        super(id);
    }
    
    protected int energyCost;
    
    public int getEnergyCost(){
        return energyCost;
    }
    
    abstract public void activate();
    abstract public void deactivate();
}
