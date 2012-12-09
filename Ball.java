import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Color;
import java.util.List;

/**
 * A ball that can break bricks.
 * 
 * @author Cody A. Ray
 * @version December 12, 2012
 */
public class Ball extends Actor
{
    private static final int DIAMETER = 15;

    // "velocity": change in x and y each tick
    private int dx;
    private int dy;

    public Ball(int dx, int dy)
    {
        this.dx = dx;
        this.dy = dy;

        GreenfootImage image = new GreenfootImage(DIAMETER,DIAMETER);
        image.setColor(Color.RED);
        image.fillOval(0, 0, DIAMETER, DIAMETER);
        setImage(image);
    }

    /**
     * The ball will break bricks if it hits them.
     */
    public void act()
    {
        move();
        checkHitPaddle();
        checkHitBrick();
    }
    
    public void move()
    {
        // hit top
        if (getY() == 0)
        {
            dy = -dy;
        }
        // hit left
        if (getX() == 0)
        {
            dx = -dx;
        }
        // hit right
        if (getX() == getWorld().getWidth()-1)
        {
            dx = -dx;
        }
        // hit bottom
        if (getY() == getWorld().getHeight()-1)
        {
            Jail jail = (Jail) getWorld();
            jail.gameOver();
        }

        setLocation(getX()+dx, getY()+dy);
    }

    private void checkHitPaddle()
    {
        Paddle paddle = (Paddle) getOneIntersectingObject(Paddle.class);
        if (paddle != null)
        {
            dy = -dy;
        }
    }

    /**
     * Check whether we have hit a brick.
     */
    private void checkHitBrick()
    {
        Brick brick = (Brick) getOneIntersectingObject(Brick.class);
        if (brick != null)
        {
            brick.hit();
            dy = -dy;
        }
    }

}