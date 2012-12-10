import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;

/**
 * Breakout of the Jail.
 * 
 * @author Cody A. Ray
 * @version December 8, 2012
 */
public class Jail extends World
{
    private static final Brick[] BRICK_COLUMN = new Brick[] {
        new Brick(Color.RED, 7), new Brick(Color.RED, 7),
        new Brick(Color.ORANGE, 5), new Brick(Color.ORANGE, 5),
        new Brick(Color.GREEN, 3), new Brick(Color.GREEN, 3),
        new Brick(Color.YELLOW, 1), new Brick(Color.YELLOW, 1)
    };
    private static final int BRICK_ROWS = BRICK_COLUMN.length;
    private static final int BRICK_COLS = 12;

    private static final int BALL_MAX_SPEED = 2;
    
    private final Counter scoreCounter;

    /**
     * Create a new Jail to Breakout.
     */
    public Jail()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        // create a black background
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();

        Paddle paddle = new Paddle();
        addObject(paddle, getWidth()/2, getHeight()*7/8);

        addBricks(BRICK_ROWS, BRICK_COLS);

        Ball ball = new Ball(1, 2);
        addObject(ball, getWidth()/2, getHeight()/2);

        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 60, 380);
        
        // Paddle should act first to hit ball before going out of bounds
        setActOrder(Paddle.class);
    }

    private void addBricks(int rows, int cols)
    {
        int rowHeight = Brick.HEIGHT;
        int colWidth = Brick.LENGTH;
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                // shift down and right 1/2 brick, so we don't cut off half the top row and left col
                int width = (int) ((0.5 + col) * colWidth);
                int height = (int) ((0.5 + row) * rowHeight);
                addObject(new Brick(BRICK_COLUMN[row]), width, height);
            }
        }
    }

    public void addScore(int value)
    {
        scoreCounter.add(value);
    }

    public void gameOver()
    {
        addObject(new ScoreBoard(scoreCounter.getValue()), getWidth()/2, getHeight()/2);
        Greenfoot.stop();
    }
}
