import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int speed;
    
    public Enemy(){

    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setLocation(getX(), getY() + speed);
        turn(5);
        checkRemove();
    }    
    
    public void setSpeed(int s) {
        speed = s;
    }
    
    private void checkRemove() {
        World w = getWorld();
        if(getY() > w.getHeight() + 30) {
            w.removeObject(this);
        }
    }
}
