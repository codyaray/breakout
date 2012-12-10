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
    private static final int ACCELERATION = 5000;
    private static final int DIAMETER = 15;

    // "velocity": change in x and y each tick
    private int dx;
    private int dy;

    // how many simulation ticks have occurred
    private int currentTick = 0;

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
        speedUp();
        bounceOffPaddle();
        breakBrickAndBounce();
        checkBricksLeft();
    }

    /**
     * The ball moves at a constant speed, changing directions when
     * it hits a wall or passes the paddle.
     */
    private void move()
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
        // hit bottom (passes the paddle)
        if (getY() == getWorld().getHeight()-1)
        {
            Jail jail = (Jail) getWorld();
            jail.gameOver();
        }

        setLocation(getX()+dx, getY()+dy);
    }

    private void speedUp()
    {
        if (currentTick % ACCELERATION == 0)
        {
            dx = (int) Math.signum(dx) * (Math.abs(dx) + 1);
            dy = (int) Math.signum(dx) * (Math.abs(dy) + 1);
        }
        currentTick++;
    }

    /**
     * Bounce off the paddle when hit
     */
    private void bounceOffPaddle()
    {
        Paddle paddle = (Paddle) getOneIntersectingObject(Paddle.class);
        if (paddle != null)
        {
            dy = -dy;
        }
    }

    /**
     * Break brick when hit and bounce off.
     */
    private void breakBrickAndBounce()
    {
        Brick brick = (Brick) getOneIntersectingObject(Brick.class);
        if (brick != null)
        {
            brick.hit();
            dy = -dy;
        }
    }

    /**
     * Check whether we have bricks left or end the game.
     */
    private void checkBricksLeft()
    {
        List<Brick> bricks = getWorld().getObjects(Brick.class);
        if (bricks != null && bricks.size() == 0)
        {
            Jail jail = (Jail) getWorld();
            jail.gameOver();
            jail.removeObject(this);
        }
    }
}