import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The class Dodo.
 * 
 * @author Sjaak Smetsers
 * @version 02 03 2015
 */
public class Dodo extends Actor
{
    public static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    private int myDirection;

    private GreenfootImage imageRight, imageLeft;
    
    protected Dodo ( int init_direction ) {
        this.myDirection = modulo( init_direction, 4);
        imageRight  = getImage();
        imageLeft = new GreenfootImage( imageRight );
        imageLeft.mirrorHorizontally();
        setImage ();

    }
    
    private void setImage (){
        if ( myDirection == NORTH ) {
            setImage( imageLeft );
            setRotation( 90 );
        } else if ( myDirection == EAST ) {
            setImage( imageRight );
            setRotation( 0 );
        } else if ( myDirection == SOUTH ) {
            setImage( imageRight );
            setRotation( 90 );
        } else if ( myDirection == WEST ) {
            setImage( imageLeft );
            setRotation( 0 );
        } 
    }
    
    public boolean fenceAhead() {
        return getActorAhead( Fence.class ) != null;
    }

    public boolean foundEgg() {
        return getActor( Egg.class ) != null;
    }

    public void layEgg() {
        getWorld().addObject( new BlueEgg (), getX (), getY () );
    }

    public Egg takeEgg() {
        return getActor( Egg.class );
    }

    public void removeActor( Actor actor ) {
        getWorld().removeObject( actor );
    }


    public boolean dodoAhead() {
        return getActorAhead( Dodo.class ) != null;
    }

    private void setDirection( int new_direction ){
        new_direction = modulo( new_direction, 4 );
        if ( this.myDirection != new_direction ) {
            this.myDirection = new_direction;
            setImage();
            if ( Madagaskar.traceIsOn() ) {
                Greenfoot.delay(1);
            }
        }
    }

    private int modulo( int a, int b ) {
		return (a % b + b) % b;
	}

    public int getDirection(){
        return myDirection;
    }

    private <E extends Actor> E getActorAhead(Class<E> cls){
        if ( myDirection == NORTH ) {
            return (E) getOneObjectAtOffset(0,-1, cls);
        } else if ( myDirection == EAST ) {
            return (E) getOneObjectAtOffset(1, 0, cls);
        } else if ( myDirection == SOUTH ) {
            return (E) getOneObjectAtOffset(0, 1, cls);
        } else if ( myDirection == WEST ) {
            return (E) getOneObjectAtOffset(-1,0, cls);
        } else {
            return null;
        }
    }

    private <E extends Actor> E getActor(Class<E> cls){
        return (E) getOneObjectAtOffset(0, 0, cls);
    }


    public void step() {
        if ( myDirection == NORTH ) {
            setLocation( getX(), getY() - 1 );
        } else if ( myDirection == EAST ) {
             setLocation(getX() + 1, getY() );
        } else if ( myDirection == SOUTH ) {
            setLocation( getX(), getY() + 1 );
        } else if ( myDirection == WEST ) {
            setLocation( getX() - 1, getY() );
        }
        if ( Madagaskar.traceIsOn() ) {
            Greenfoot.delay(1);
        }
    }
    
    /**
     * Test is we are facing the border.
     */
    public boolean borderAhead () {
        if ( myDirection == NORTH ) {
            return getY() == 0;
        } else if ( myDirection == EAST ) {
            return getX() == getWorld().getWidth()  - 1;
        } else if ( myDirection == SOUTH ) {
            return getY() == getWorld().getHeight() - 1;
        } else { // if ( myDirection == WEST ) {
            return getX() == 0;
        }
    }

    public boolean facingNorth () {
        return getDirection() == NORTH;
    }
    
    /**
     * Turns towards the left.
     */
    public void turnLeft() {
        setDirection( modulo( myDirection-1, 4 ) );
    }

    /**
     * Turns towards the right.
     */
    public void turnRight() {
        setDirection( modulo( myDirection+1, 4 ) );
    }

    protected void showError ( String err_msg ) {
        if ( ! Message.messageActive( getWorld() ) ) {
            World my_world = getWorld();
            my_world.addObject (new Alert (err_msg),my_world.getWidth()/2, my_world.getHeight()/2);
        }
    }

    protected void showCompliment ( String compl_msg ) {
        if ( ! Message.messageActive( getWorld() ) ) {
            World my_world = getWorld();
            my_world.addObject (new Compliment ( compl_msg ),my_world.getWidth()/2, my_world.getHeight()/2);
        }
    }


}
