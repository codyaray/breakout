import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Color;

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
        checkHitBrick();
        checkOutOfBounds();
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
        }
    }

    private void checkOutOfBounds()
    {
        Jail jail = (Jail) getWorld();
        if (this.getExactY() >= jail.getPaddle().getExactY()+DIAMETER*3)
        {
            jail.gameOver();
            getWorld().removeObject(this);
        }
    }
}