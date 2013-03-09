
package com.ninjacannon.quantum.level;

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
    private XMLParser parser;
    
    public Wave(){
        parser = new XMLParser();
    }
    
    public XMLElement parseFile(String ref)
    {
        try {
            return parser.parse(ref);
        } catch (SlickException ex) {
            System.out.println("Error reading file:" + ref);
            System.exit(-1);
        }
        return null;
    }
   
}
