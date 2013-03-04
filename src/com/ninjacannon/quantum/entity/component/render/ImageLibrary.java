
package com.ninjacannon.quantum.entity.component.render;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * @author Dan Cannon
 */
public class ImageLibrary{
    
    public final static Image ship = load("images/player.png");
    public final static Image bullet = load("images/bullet.png");
    public final static Image exit = load("images/exit.png");
    public final static Image start = load("images/start.png");
    public final static Image menu = load("images/menu.png");
    public final static Image background = load("images/background.png");
    public final static Image enemies = load("images/enemy.gif");
    public final static Image enemy = cut(enemies, 0, 0, 64, 64);
    public final static SpriteSheet shield = loadSheet("images/shield.png", 128, 128);
    public final static SpriteSheet explosion = loadSheet("images/explosion.png", 64, 64);
    
    public static Image load(String url){
        try{
            return new Image(url);
        } catch (SlickException ex){ 
            System.err.println("Problem Loading resources");
            System.exit(0);
        }
        return null;
    }
    
    public static SpriteSheet loadSheet(String url, int w, int h){
        try{
            return new SpriteSheet(url, w, h);
        } catch (SlickException ex){ 
            System.err.println("Problem Loading resources");
            System.exit(0);
        }
        return null;
    }
    
    public static Image cut(Image image, int x, int y, int width, int height)
    {
        return image.getSubImage(x, y, width, height);
    }
}
