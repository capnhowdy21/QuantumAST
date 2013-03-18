
package com.ninjacannon.quantum.level;

import com.ninjacannon.quantum.entity.Entity;
import com.ninjacannon.quantum.entity.EntityManager;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.xml.SlickXMLException;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLElementList;
import org.newdawn.slick.util.xml.XMLParser;

/**
 * @author Dan Cannon
 */
public class Wave 
{
    private static XMLParser parser = new XMLParser();
        
    private static XMLElement loadFile(String ref)
    {
        try {
            return parser.parse(ref);
        } catch (SlickException ex) {
            System.out.println("Error reading file:" + ref);
            System.exit(-1);
        }
        return null;
    }
    
    public static int createWave(String ref)
    {
        XMLElement root = loadFile(ref);
        int delay = 0;
        try {
            delay = root.getIntAttribute("delay");          
            XMLElementList list = root.getChildren();
            for(int i = 0; i < list.size(); i++)
            {
                EntityManager.manager.addEntity(createEntity(list.get(i)));
            }
        } catch (SlickXMLException ex) {
            System.out.println("Error reading file:" + ex);
            System.exit(-1);
        }
        return delay;
    }
    
    private static Entity createEntity(XMLElement element)
    {
        Entity e = null;
        Enemy type = getType(element.getName());
        try{
            switch(type){
                case swirve: e=EntityFactory.createSwirve(
                        element.getIntAttribute("x"), 
                        element.getIntAttribute("y"), 
                        (float)element.getDoubleAttribute("dx"), 
                        (float)element.getDoubleAttribute("midY"), 
                        (float)element.getDoubleAttribute("peak"), 
                        (float)element.getDoubleAttribute("interval"));
                    break;
                case suicide: e=EntityFactory.createSuicide(
                        element.getIntAttribute("x"), 
                        element.getIntAttribute("y"), 
                        (float)element.getDoubleAttribute("dx"),
                        (float)element.getDoubleAttribute("dy"));
                    break;
                case titan: e=EntityFactory.createTitan(
                        element.getIntAttribute("x"), 
                        element.getIntAttribute("y"), 
                        (float)element.getDoubleAttribute("dx"),
                        (float)element.getDoubleAttribute("dy"));
                    break;
                case shooter: e=EntityFactory.createShooter(
                        element.getIntAttribute("x"), 
                        element.getIntAttribute("y"), 
                        (float)element.getDoubleAttribute("dx"),
                        (float)element.getDoubleAttribute("dy"));
                    break;
            }
        }catch (SlickXMLException ex){
            System.out.println("Error reading file:" + ex);
            System.exit(-1);
        }
        return e;
    }
    
    
    private static enum Enemy {swirve, suicide, titan, shooter}
    
    private static Enemy getType(String name){
        Enemy type = Enemy.swirve;
        if(name.equalsIgnoreCase("swirve")){
            type = Enemy.swirve;
        } else if(name.equalsIgnoreCase("suicide")){
            type = Enemy.suicide;
        } else if(name.equalsIgnoreCase("titan")){
            type = Enemy.titan;
        } else if(name.equalsIgnoreCase("shooter")){
            type =Enemy.shooter;
        }
        return type;
    }
}
