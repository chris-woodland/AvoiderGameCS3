import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AvoiderGameIntroScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AvoiderGameIntroScreen extends World
{
    private GreenfootSound introMusic;
    private boolean introPlayed; 
    /**
     * Constructor for objects of class AvoiderGameIntroScreen.
     * 
     */
    public AvoiderGameIntroScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        introMusic = new GreenfootSound("sounds/intro.mp3");
        introPlayed = false;
    }

    public void act() {
        if(!introPlayed) {
            introMusic.play();
            introPlayed = true;
        }
        //Start the game if the user clicks the mouse anywhere
        if(Greenfoot.mouseClicked(this)) {
            introMusic.stop();
            AvoiderWorld world = new AvoiderWorld();
            Greenfoot.setWorld(world);
        }
    }
}
