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
        checkHitPaddle();
        checkHitBrick();
        checkOutOfBounds();
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
            
        force.scale(0.75);
        addForce(force.copy());
        addForce(force.copy());
    }

    public void move()
    {
        double exactX = getExactX() + getMovement().getX();
        double exactY = getExactY() + getMovement().getY();
        if(exactX >= getWorld().getWidth()) {
            bounce(true, false);
        }
        if(exactX < 0) {
            bounce(true, false);
        }
        if(exactY >= getWorld().getHeight()) {
            bounce(false, true);
        }
        if(exactY < 0) {
            // lose!
        }
        setLocation((int) exactX, (int) exactY);
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
            bounce(false, true);
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