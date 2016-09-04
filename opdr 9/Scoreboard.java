import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.FontMetrics;

/**
 * Write a description of class Scoreboard here.
 * 
 * @author Sjaak Smetsers
 * @version 25-02-2011
 */

public class Scoreboard  extends Actor
{
    private static final int SB_WIDTH = 100, SB_HEIGHT = 30, BORDER_WIDTH = 3;
    GreenfootImage boardImage;
    
    private String myLabel;
    
    private final Color boardColor    = new Color ( 117, 185, 231 ),
                        textColor     = Color.BLACK;
    private final Font textFont       = new Font("Calibri", Font.BOLD, 18);


    public Scoreboard ( String label, int init_score ) {
        boardImage = new GreenfootImage ( SB_WIDTH , SB_HEIGHT );
        myLabel = label;        
        drawScore (init_score);
        setImage ( boardImage ); 
        
    }

    public void updateScore( int new_score ) {
        drawScore( new_score );
    }
    
    private void drawScore( int score ) {
        boardImage.setColor ( boardColor.darker() );
        boardImage.fillRect( 0, 0, SB_WIDTH, SB_HEIGHT );
        boardImage.setColor ( boardColor );
        boardImage.fillRect( BORDER_WIDTH, BORDER_WIDTH, SB_WIDTH-2*BORDER_WIDTH,SB_HEIGHT-2*BORDER_WIDTH );
        boardImage.setColor ( textColor );
        boardImage.setFont( textFont );
        boardImage.drawString ( myLabel + score, BORDER_WIDTH+2, SB_HEIGHT - 10 );
    }
            
}

