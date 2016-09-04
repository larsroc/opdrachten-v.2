import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Alert can be used to display an alert message.
 * 
 * @author Sjaak Smetsers
 * @version 1.0, 11--03--2015
 */
public class Alert extends Message
{
   static private Color alertColor = new Color (246,141,130);

    public Alert( String message_text ) {
        super ( message_text, alertColor, "sad2.png" );
    }
        
}
