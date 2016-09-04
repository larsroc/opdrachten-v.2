import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Write a description of class WorldWriter here.
 * 
 * @author Sjaak Smetsers
 * @version 1.0 -- 15-01-2015
 */
public class WorldWriter  
{
    public static final String WORLD_DIR = "worlds/";
    
    private BufferedWriter worldWriter;
    public WorldWriter( String file_name ) {
        try {
            FileWriter fstream = new FileWriter( WORLD_DIR + file_name );
            worldWriter = new BufferedWriter( fstream );
        } catch (IOException ioe) {
            System.out.println("Oops!!! file could not be opened.");
        }
    }
    
    public void write ( char c ) throws IOException {
        worldWriter.write ( c );
    }

    public void write ( String s ) throws IOException {
        worldWriter.write ( s, 0, s.length() );
    }

    public void close ()  throws IOException {
        worldWriter.close();
    }
}