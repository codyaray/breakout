import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

/**
 * The ScoreBoard is used to display results on the screen. It can display some
 * text and several numbers.
 * 
 * @author M Kolling
 * @version 1.0
 */
public class ScoreBoard extends Actor
{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final int BORDER_WIDTH = 5;
    public static final float FONT_SIZE = 48.0f;

    /**
     * Create a score board for the final result.
     */
    public ScoreBoard(int score)
    {
        makeImage("Game Over", "Score: ", score);
    }

    /**
     * Make the score board image.
     */
    private void makeImage(String title, String prefix, int score)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        // setup border
        image.setColor(new Color(255, 255, 255, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);

        // setup panel
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(BORDER_WIDTH, BORDER_WIDTH, WIDTH-2*BORDER_WIDTH, HEIGHT-2*BORDER_WIDTH);

        // setup font
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);

        // write text
        image.setColor(Color.WHITE);
        image.drawString(title, 60, 100);
        image.drawString(prefix + score, 60, 200);

        setImage(image);
    }
}
