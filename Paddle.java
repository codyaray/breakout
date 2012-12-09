import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * What you control to hit the ball with.
 * 
 * @author Cody A. Ray
 * @version December 8, 2012
 */
public class Paddle extends Actor
{
    private static final int LENGTH = 100;
    private static final int HEIGHT = 5;

    private static final int DISTANCE = 5;

    /**
     * Create a new paddle.
     */
    public Paddle()
    {
        GreenfootImage image = getImage();
        image.scale(LENGTH, HEIGHT);
        image.setColor(Color.RED);
        image.fill();
    }

    /**
     * Move left and right when the keys are pressed
     */
    public void act() 
    {
        checkKeys();
    }

    private void checkKeys()
    {
        if (Greenfoot.isKeyDown("left"))
        {
            setLocation(getX()-DISTANCE, getY());
        }
        
        if (Greenfoot.isKeyDown("right"))
        {
            setLocation(getX()+DISTANCE, getY());
        }
    }
}
