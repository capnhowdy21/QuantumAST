
package com.ninjacannon.quantum;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.PlayerGunComponent;
import com.ninjacannon.quantum.entity.component.collision.ShieldComponent;
import com.ninjacannon.quantum.entity.component.movement.KeyboardMovementComponent;
import com.ninjacannon.quantum.entity.component.render.ImageLibrary;
import com.ninjacannon.quantum.entity.component.render.ShieldRenderComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Dan Cannon
 */
public class Player 
{
    private Entity ship;
    private KeyboardMovementComponent move;
    private PlayerGunComponent gun;
    private ShieldRenderComponent render;
    private ShieldComponent shield;
    private int respawnTimer = 1200;
    private int shieldStr = 10;
    private int lives = 3;
    public boolean alive;
    
    public Player()
    {
        alive = true;
        ship = new Entity(Entity.EntityType.PLAYER);
        ship.setHeight(ImageLibrary.ship.getHeight());
        ship.setWidth(ImageLibrary.ship.getWidth());
        ship.setScale(.5f);
        
        gun = new PlayerGunComponent("Gun");
        move = new KeyboardMovementComponent("Movement");
        render = new ShieldRenderComponent("Render", ImageLibrary.ship, ImageLibrary.shield, 3, 1, 50);
        shield = new ShieldComponent("Shield", shieldStr);
        
        ship.AddComponent(gun);
        ship.AddComponent(move);
        ship.AddComponent(render);
        ship.AddComponent(shield);
    }
    
   
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        if(ship.isAlive())
        {
            Input input = gc.getInput();

            if(input.isKeyDown(Input.KEY_W)){
                move.setDY(-.2f);
            } else if(input.isKeyDown(Input.KEY_S)){
                move.setDY(.2f);
            } else {
                move.setDY(0);
            }

            if(input.isKeyDown(Input.KEY_D)){
                move.setDx(.3f);
            } else if(input.isKeyDown(Input.KEY_A)){
                move.setDx(-.2f);
            } else {
                move.setDx(0);
            }

            if(input.isKeyDown(Input.KEY_E)){
                shield.setShielded(true);
                render.setRenderShield(true);
                gun.setFiring(false);
            } else if (input.isKeyDown(Input.KEY_SPACE)){
                gun.setFiring(true);
                shield.setShielded(false);
                render.setRenderShield(false);
            } else {
                gun.setFiring(false);
                shield.setShielded(false);
                render.setRenderShield(false);
            }
        } else {
            respawnTimer -= delta;
            if(respawnTimer <= 0){
                death();
            }    
        }
    }
    
    public final Entity getShip(){
        return ship;
    }
    
    public void death(){
        lives--;
        if(lives > 0){
            ship.setPosition(new Vector2f(0,0));
            ship.setAlive(true);
            EntityManager.manager.addEntity(ship);
            respawnTimer = 1200;
            shield.reset();
        } else {
            alive = false;
        }
    }
    
    public void reset()
    {
        alive = true;
        ship.setPosition(new Vector2f(0,0));
        ship.setAlive(true);
        respawnTimer = 1200;
        lives = 3;
        shield.reset();
        gun.reset();
        move.reset();
    }
}
