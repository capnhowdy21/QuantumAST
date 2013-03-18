/*
 * 
 */
package com.ninjacannon.quantum.entity;

import com.ninjacannon.quantum.entity.Entity.EntityType;
import com.ninjacannon.quantum.entity.component.Component;
import com.ninjacannon.quantum.entity.component.upgrades.EnergyComponent;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dan Cannon
 */
public class EntityTest
{
    
    public EntityTest()
    {
    }

    /**
     * Test of getComponent method, of class Entity.
     */
    @Test
    public void testGetComponent()
    {
        System.out.println("getComponent");
        String id = "Energy";
        Entity instance = new Entity(EntityType.PLAYER);
        Component expResult = new EnergyComponent(id, 100);
        instance.AddComponent(expResult);
        Component result = instance.getComponent(id);
        assertEquals(expResult, result);
    }


}
