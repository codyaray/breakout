import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Color;
import java.util.List;

/**
 * A ball that can break bricks.
 * 
 * @author Cody A. Ray
 * @version December 12, 2012
 */
public class Ball extends SmoothMover
{
    private static final int DIAMETER = 15;

    public Ball(Vector velocity)
    {
        super(velocity);

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
        checkOutOfBounds();
        checkBricksLeft();
    }
    
    private void checkHitPaddle()
    {
        Paddle paddle = (Paddle) getOneIntersectingObject(Paddle.class);
        if (paddle != null)
        {
            bounce(false, true);
        }
    }

    private void bounce(boolean horizontal, boolean vertical)
    {
        Vector force = getMovement().copy();

        if (vertical)
        {
            force.revertVertical();
        }
        if (horizontal)
        {
            force.revertHorizontal();
        }
        setMovement(force);
    }

    public void move()
    {
        double exactX = getExactX() + getMovement().getX();
        double exactY = getExactY() + getMovement().getY();
        if(exactX >= getWorld().getWidth()) {
            bounce(true, false);
        }
        if(exactX <= DIAMETER) {
            bounce(true, false);
        }
        if(exactY >= getWorld().getHeight()) {
            bounce(false, true);
        }
        if(exactY < DIAMETER) {
            // lose!
        }
        setLocation((int) exactX, (int) exactY);
        System.out.println(exactX + " " + exactY);
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
            
            // going down
            if (getObjectsAtOffset(0, -DIAMETER-1, Brick.class) != null)
            {
                bounce(false, true);
            }
            // going left
            else if (getObjectsAtOffset(-DIAMETER-1, 0, Brick.class) != null)
            {
                bounce(true, false);
            }
            // going up
            else if (getObjectsAtOffset(0, DIAMETER+1, Brick.class) != null)
            {
                bounce(false, true);
            }
            // going right
            else if (getObjectsAtOffset(DIAMETER+1, 0, Brick.class) != null)
            {
                bounce(true, false);
            }
            // is this possible?
            else
            {
                throw new RuntimeException("Is this possible?");
            }
        }
    }

    private void checkOutOfBounds()
    {
        Jail jail = (Jail) getWorld();
        if (this.getExactY() >= jail.getHeight())
        {
            jail.gameOver();
            getWorld().removeObject(this);
        }
    }

    private void checkBricksLeft()
    {
        Jail jail = (Jail) getWorld();
        List<Brick> bricks = jail.getObjects(Brick.class);
        if (bricks != null && bricks.size() == 0)
        {
            jail.gameOver();
            getWorld().removeObject(this);
        }
    }
}