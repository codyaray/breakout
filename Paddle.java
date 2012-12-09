import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Paddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paddle extends SmoothMover
{
    private static final int LENGTH = 100;
    private static final int HEIGHT = 5;

    private static final int LEFT = 180;
    private static final int RIGHT = 0;

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
     * Move left and right when the keys are pressed and block the ball from passing
     */
    public void act() 
    {
        checkKeys();
        move();
        checkCollision();
    }

    private void checkKeys()
    {
        if (Greenfoot.isKeyDown("left"))
        {
            setRotation(LEFT);
            addForce();
        }
        
        if (Greenfoot.isKeyDown("right"))
        {
            setRotation(RIGHT);
            addForce();
        }
    }

    private void addForce()
    {
        Vector drift = new Vector(getRotation(), 0.3);
        addForce(drift);
    }

    private void checkCollision()
    {
        Ball ball = (Ball) getOneIntersectingObject(Ball.class);
        if (ball != null)
        {
            Vector force = ball.getMovement().copy();
            force.revertVertical(); // why does this work vertical instead of horizontal?
            ball.addForce(force.copy());
            ball.addForce(force.copy());
        }
    }
}
