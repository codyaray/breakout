import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * A brick sits there looking colorful until the ball breaks it,
 * adding the value of the brick to the user's score.
 * 
 * @author Cody A. Ray 
 * @version December 8, 2012
 */
public class Brick extends Actor
{
    static final int LENGTH = 50;
    static final int HEIGHT = 20;

    private final Color color;
    private final int value;

    /**
     * Create a new brick with the given color and value
     * (points added to the user's score when broken).
     * 
     * @param color the color of the brick
     * @param value the value to the user's score
     */
    public Brick(Color color, int value)
    {
        this.color = color;
        this.value = value;

        // create brick image
        GreenfootImage image = new GreenfootImage(LENGTH, HEIGHT);
        image.setColor(color);
        image.fill();

        // add borders
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, LENGTH, HEIGHT);
        setImage(image);
    }
    
    /**
     * Copy constructor creates a new brick with the
     * same color and value as the given one.
     */
    public Brick(Brick brick)
    {
        this(brick.color, brick.value);
    }

    /**
     * Bricks don't do nothin'
     */
    public void act() 
    {
        // Nothing to do
    }

    /**
     * A hit breaks the brick, removing it from the jail
     * and adding its value to the user's score.
     */
    public void hit()
    {
        Jail jail = (Jail) getWorld();
        jail.addScore(value);
        jail.removeObject(this);
    }

    /**
     * Returns the value to be added to the user's score when broken.
     * @return the brick's value 
     */
    public int getValue()
    {
        return value;
    }
}
