import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Egg here.
 * 
 * @author Sjaak Setsers
 * @version 1.0 02-03-2015
 */
public class Egg  extends Actor
{
    private int myValue;
    
    public Egg( int value ) {
        myValue = value;
    }
    
    public int getValue() {
        return myValue;
    }        
   
}
