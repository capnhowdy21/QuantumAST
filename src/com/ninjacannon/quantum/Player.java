
package com.ninjacannon.quantum;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import com.ninjacannon.quantum.entity.component.GunComponent;
import com.ninjacannon.quantum.entity.component.collision.ShieldComponent;
import com.ninjacannon.quantum.entity.component.movement.PlayerMovementComponent;
import com.ninjacannon.quantum.entity.component.render.ImageLibrary;
import com.ninjacannon.quantum.entity.component.render.ShieldRenderComponent;
import com.ninjacannon.quantum.entity.component.upgrades.MineUpgradeComponent;
import com.ninjacannon.quantum.entity.component.upgrades.NukeUpgradeComponent;
import com.ninjacannon.quantum.entity.component.upgrades.TurretUpgradeComponent;
import com.ninjacannon.quantum.entity.component.upgrades.UpgradeComponent;
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
    private PlayerMovementComponent move;
    private GunComponent gun;
    private ShieldRenderComponent render;
    private ShieldComponent shield;
    private UpgradeComponent upgrade1;
    private UpgradeComponent upgrade2;
    private UpgradeComponent upgrade3;
    private int respawnTimer = 1200;
    private int shieldStr = 10;
    private int lives = 3;
    private int maxEnergy = 100;
    private int energy = 0;
    public boolean alive;
    
    public Player()
    {
        alive = true;
        ship = new Entity(Entity.EntityType.PLAYER);
        ship.setHeight(ImageLibrary.ship.getHeight());
        ship.setWidth(ImageLibrary.ship.getWidth());
        ship.setScale(1f);
        
        gun = new GunComponent("Gun", Entity.EntityType.FRIENDLY);
        move = new PlayerMovementComponent("Movement");
        render = new ShieldRenderComponent("Render", ImageLibrary.ship, ImageLibrary.shield, 3, 1, 50);
        shield = new ShieldComponent("Shield", shieldStr);
        upgrade1 = new MineUpgradeComponent("Upgrade");
        upgrade2 = new TurretUpgradeComponent("Upgrade");
        upgrade3 = new NukeUpgradeComponent("Upgrade");
        
        ship.AddComponent(gun);
        ship.AddComponent(move);
        ship.AddComponent(render);
        ship.AddComponent(shield);
        ship.AddComponent(upgrade1);
        ship.AddComponent(upgrade2);
        ship.AddComponent(upgrade3);
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
            
            if(input.isKeyDown(Input.KEY_1)){
                upgrade1.activate();
            }
            if(input.isKeyDown(Input.KEY_2)){
                upgrade2.activate();
            }
            if(input.isKeyDown(Input.KEY_3)){
                upgrade3.activate();
            }
            
        } else {
            upgrade1.deactivate();
            upgrade2.deactivate();
            upgrade3.deactivate();
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
            ship.reset();
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
        energy = 0;
        ship.reset();
    }
}
