
package com.ninjacannon.quantum.entity;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * @author Dan Cannon
 */
public class SoundLibrary 
{
    //public final static Music MUSIC_THEME = loadMusic("sounds/theme.ogg");
    public final static Music MUSIC_1 = loadMusic("sounds/music1.ogg");
    public final static Sound FX_LASER = loadSound("sounds/gun.wav"); 

    public static Music loadMusic(String url)
    {
        try{
            return new Music(url);
        } catch (SlickException ex) {
            System.err.println("Problem Loading resources");
            System.exit(0);
        }
        return null;
    }
    
    public static Sound loadSound(String url)
    {
        try{
            return new Sound(url);
        } catch (SlickException ex) {
            System.err.println("Problem Loading resources");
            System.exit(0);
        }
        return null;
    }
}
