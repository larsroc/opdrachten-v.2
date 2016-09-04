import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.List;

import java.io.IOException;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Write a description of class ChickenWorld here.
 * 
 * @author Sjaak Smetsers
 * @version 1.0 -- 20-01-2015
 */
public class Madagaskar  extends World
{
    private static String WORLD_FILE = "world_empty.txt";

    private static final int MAXWIDTH = 12, MAXHEIGHT = 12, CELLSIZE = 60;

    private static boolean traceOn = true;

    private static final char
        FENCE      = '#'            ,
        EGG_YELLOW = '$'            ,
        EGG_BLUE   = '.'            ,
        DODO_N     = 'N'            ,
        DODO_S     = 'S'            ,
        DODO_E     = 'E'            ,
        DODO_W     = 'W'            ;

    /**
     * Constructor for objects of class ChickenWorld.
     * 
     */
    public Madagaskar() {    
        super(MAXWIDTH, MAXHEIGHT, CELLSIZE); 
        populate();
    }

    public static void traceOn() {
        traceOn = true;
    }

    public static void traceOff() {
        traceOn = false;
    }

    public static boolean traceIsOn() {
        return traceOn;
    }

    private Actor charToActor( char c ) {
        switch ( c ) {
            case EGG_YELLOW:
                return new GoldenEgg();
            case FENCE:
                return new Fence();
            case EGG_BLUE:
                return new BlueEgg();
            case DODO_N:
                return  new MyDodo( Dodo.NORTH );
            case DODO_S:
                return  new MyDodo( Dodo.SOUTH );
            case DODO_E:
                return new MyDodo( Dodo.EAST );
            case DODO_W:
                return new MyDodo( Dodo.WEST );
            default:
                return null;
        }
    }

    private void populate () {
        if ( ! WORLD_FILE.isEmpty() ) {
            WorldReader reader = new WorldReader ( WORLD_FILE );

            try {
                while (reader.hasNext()) {
                    WorldReader.Cell next_cell = reader.next();
                    Actor actor = charToActor( next_cell.getChar() );
                    if ( actor != null ) {
                        addObject(actor, next_cell.getX(), next_cell.getY());
                    }
                }
                reader.close();
            } catch ( IOException ioe ) {
            }
        }            
    }
    
    private void removeAllActors() {
        removeObjects( getObjects( null ) );
    }
    
    private char getActorAt( int x, int y ){
        List<Actor> actors = getObjectsAt(x, y, null);
        if ( actors.size() > 0 ) {
            Actor actor = actors.get( 0 );
            if ( actor instanceof MyDodo ) {
                MyDodo dodo = (MyDodo) actor;
                switch ( dodo.getDirection() ) {
                    case Dodo.NORTH: return DODO_N;
                    case Dodo.SOUTH: return DODO_S;
                    case Dodo.EAST:  return DODO_E;
                    default:    return DODO_W;
                }
            } else if ( actor instanceof Fence ) {
                return FENCE;
            } else if ( actor instanceof GoldenEgg ) {
                return EGG_YELLOW;
            } else if ( actor instanceof BlueEgg ) {
                return EGG_BLUE;
            } else {
                return ' ';
            }
        } else {
            return ' ';
        }
    }

    public void saveToFile() {
        WorldWriter writer = new WorldWriter ( "saved.txt" );
        try {
            writer.write( String.format("%d %d\n", MAXWIDTH, MAXHEIGHT) );
            for ( int y = 0; y < MAXHEIGHT; y++ ) {
                for ( int x = 0; x < MAXWIDTH; x++ ) {
                    writer.write( getActorAt( x, y ) );
                }
                writer.write( '\n' );
            }
            writer.close();
        } catch ( IOException ioe ) {
        }
    }
    
    public void populateFromFile() {
        File world_files = new File ( WorldWriter.WORLD_DIR );
        JFileChooser chooser = new JFileChooser( world_files );
        FileNameExtensionFilter filter = new FileNameExtensionFilter( "Plain text files", "txt" );
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog( null );
        if ( returnVal == JFileChooser.APPROVE_OPTION ) {
            WORLD_FILE = chooser.getSelectedFile().getName();
            removeAllActors();
            populate();
        }
    }

        
}
