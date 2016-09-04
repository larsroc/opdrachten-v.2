import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Compliment can be used to congratulate sb.
 * 
 * @author Sjaak Smetsers
 * @version 1.0, 11--03--2015
 */
public class Compliment extends Message
{
   static private Color happyColor = new Color ( 77, 215, 77 );

    public Compliment( String message_text ) {
        super ( message_text, happyColor, "winking.png" );
    }
        
}
