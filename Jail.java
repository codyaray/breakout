import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;

/**
 * Write a description of class PongWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Jail extends World
{
    private static final int BRICK_ROWS = 8;
    private static final int BRICK_COLS = 14;
    private static final Brick[] BRICK_COLUMN = new Brick[] {
        new Brick(Color.RED, 7), new Brick(Color.RED, 7),
        new Brick(Color.ORANGE, 5), new Brick(Color.ORANGE, 5),
        new Brick(Color.GREEN, 3), new Brick(Color.GREEN, 3),
        new Brick(Color.YELLOW, 1), new Brick(Color.YELLOW, 1),
    };

    private final Counter scoreCounter;
    private final Paddle paddle;

    /**
     * Create a new Jail to Breakout.
     */
    public Jail()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();

        Paddle paddle = new Paddle();
        this.paddle = paddle;
        addObject(paddle, getWidth()/2, getHeight()*7/8);

        addBricks(BRICK_ROWS, BRICK_COLS);

        int min = 30, max = 150;
        int direction = min + Greenfoot.getRandomNumber(max - min);
        Ball ball = new Ball(new Vector(direction, 2));
        addObject(ball, getWidth()/2, getHeight()/2);

        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 60, 380);
        
        // Paddle should act first to hit ball before going out of bounds
        setActOrder(Paddle.class);
    }

    private void addBricks(int rows, int cols)
    {
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                addObject(new Brick(BRICK_COLUMN[row]), col*Brick.LENGTH, row*Brick.HEIGHT);
            }
        }
    }
    
    public void addScore(int value)
    {
        scoreCounter.add(value);
    }

    public Paddle getPaddle()
    {
        return paddle;
    }
    
    public void gameOver()
    {
        addObject(new ScoreBoard(scoreCounter.getValue()), getWidth()/2, getHeight()/2);
    }
}
