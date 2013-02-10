/*
 * 
 */
package com.ninjacannon.quantum.entity.component.collision;

import com.ninjacannon.quantum.entity.Entity;
import org.junit.*;
import static org.junit.Assert.*;
import org.newdawn.slick.geom.Vector2f;


/**
 *
 * @author Dan Cannon
 */
public class CollisionComponentTest
{
    NormalCollisionComponent comp;
    Entity owner;
    Entity them;
    
    public CollisionComponentTest()
    {
        
    }
    
    @Before
    public void setUp(){
        comp = new NormalCollisionComponent("Collision");
        owner = new Entity("Owner");
        owner.AddComponent(comp);
        them = new Entity("Them");
    }
    
    @Test
    public void testCollidesTrue()
    {        
        owner.setPosition(new Vector2f(0,0));
        owner.setHeight(10);
        owner.setWidth(10);
        
        them.setPosition(new Vector2f(5,5));
        them.setHeight(10);
        them.setWidth(10);
        
        comp.collides(them);
        assertTrue(comp.collided);
    }
    
    @Test
    public void testCollidesFalse()
    {
        owner.setPosition(new Vector2f(0,0));
        owner.setHeight(10);
        owner.setWidth(10);
        
        them.setPosition(new Vector2f(11,11));
        them.setHeight(10);
        them.setWidth(10);
        
        comp.collides(them);
        assertFalse(comp.collided);
    }
}
