
package com.ninjacannon.quantum.entity.component.render;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author Dan Cannon
 */
public class ImageLibrary{
    
    public static Image ship = load("images/player.png");
    public static Image bullet = load("images/bullet.png");
    public static Image exit = load("images/exit.png");
    public static Image start = load("images/start.png");
    public static Image menu = load("images/menu.png");
    public static Image background = load("images/background.png");
    public static Image enemies = load("images/enemy.gif");
    public static Image enemy = cut(enemies, 0, 0, 64, 64);
    
    public static Image load(String url){
        try{
            return new Image(url);
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
