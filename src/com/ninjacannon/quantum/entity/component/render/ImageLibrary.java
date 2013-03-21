
package com.ninjacannon.quantum.entity.component.render;

import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * @author Dan Cannon
 */
public final class ImageLibrary{
    
    private static ImageLibrary _ImageLibrary;
    private HashMap images;
    
    private ImageLibrary(){
        images = new HashMap();
    }
    
    public static ImageLibrary getInstance(){
        if(_ImageLibrary == null){
            synchronized (ImageLibrary.class) {
                if (_ImageLibrary == null) {
                  _ImageLibrary = new ImageLibrary();
                }
            }
	}
        return _ImageLibrary;
    }
    
    public void loadImage(String key, String ref)
    {
        images.put(key, load(ref));
    }
    
    public void loadSubImage(String key, int x, int y, int width, int height, String subId)
    {
        Image img = (Image)images.get(key);
        images.put(subId,img.getSubImage(x, y, width, height));
    }
    
    public void loadSpriteSheet(String id, String ref, int w, int h){
        images.put(id, loadSheet(ref, w, h));
    }
    
    private Image load(String ref){
        try{
            return new Image(ref);
        } catch (SlickException ex){ 
            System.err.println("Problem Loading resources");
            System.exit(0);
        }
        return null;
    }
    
    private SpriteSheet loadSheet(String key, int w, int h){
        try{
            return new SpriteSheet(key, w, h);
        } catch (SlickException ex){ 
            System.err.println("Problem Loading resources");
            System.exit(0);
        }
        return null;
    }
    
    public Image getImage(String key){
        return (Image)images.get(key);
    }
    
    public SpriteSheet getSheet(String key){
        return (SpriteSheet)images.get(key);
    }
    
    public void close(){
        images.clear();
        images = null;
    }
    
    public void init(){
        loadImage("energy", "images/energy.png");
        loadImage("hud", "images/ui.png");
        loadImage("powered", "images/powered.png");
        loadImage("ship", "images/player.png");
        loadImage("green bullet", "images/greenbullet.png");
        loadImage("red bullet", "images/redbullet.png");
        loadImage("exit", "images/exit.png");
        loadImage("start", "images/start.png");
        loadImage("menu", "images/menu.png");
        loadImage("background", "images/background.png");
        loadImage("enemies", "images/enemy.gif");
        loadSubImage("enemies", 65, 124, 29, 26, "suicide");
        loadSubImage("enemies", 67, 160, 23, 26, "swirve");
        loadSubImage("enemies", 129, 5, 57, 56, "titan");
        loadSubImage("enemies", 58, 39, 46, 20, "shooter");
        loadSubImage("enemies", 67, 160, 23, 26, "turret");
        loadSpriteSheet("shield", "images/shield.png", 64, 64);
        loadSpriteSheet("explosion", "images/explosion.png", 64, 64);
        loadSpriteSheet("mine", "images/mine.png", 16, 16);
        loadSpriteSheet("font", "images/font.png", 8, 8);
        loadSpriteSheet("cloak", "images/cloak.png", 32, 32);
    }
}
