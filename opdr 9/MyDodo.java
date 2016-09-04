import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Sjaak Smetsers
 * @version Version 1.2 -- 11-03-2015
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;

    public MyDodo() {
        this ( WEST );
    }

    public MyDodo( int init_direction ) {
        super ( init_direction );
        myNrOfEggsHatched = 0;
    }

    /**
     * Go to the edge of the world and
     * walk along the border
     */
    public void act() {
       turn180();
    }

    /**
     * 
     */    
    public void hatchEgg () {
        if ( foundEgg() ) {
            removeActor( takeEgg() );
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }

    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }

    /**
     * Move one cell forward in the current direction.
     */
    public void move() {
        if ( canMove() ) {
            step();
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Move given number of cells forward in the current direction.
     * @param distance: the number of steps made
     */
    public void jump( int distance ) {
        int steps = 0;
        while ( steps < distance ) {
            move();
            steps++;
        }
    }

    /**
     * Test if we can move forward. Return true if we can, false otherwise.
     */
    public boolean canMove() {
        if ( ! borderAhead() ){
            return true;
        } else {
            return false;
        }
    } 

}
