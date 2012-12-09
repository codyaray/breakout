import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.awt.Color;
import java.awt.Graphics;

/**
 * Counter that displays a text and number.
 * 
 * @author Michael Kolling
 * @version 1.0.1
 */
public class Counter extends Actor
{
    private final String prefix;
    private final Color textColor;

    // the counter seeks to keep value in sync with target
    private int value = 0;
    private int target = 0;

    public Counter(String prefix)
    {
        this(new Color(255, 180, 150), prefix);
    }

    public Counter(Color textColor, String prefix)
    {
        this.textColor = textColor;
        this.prefix = prefix;

        int prefixLength = (prefix.length() + 2) * 10;
        GreenfootImage image = new GreenfootImage(prefixLength, 16);
        image.setColor(textColor);
        setImage(image);

        updateImage();
    }

    public void act() {
        if (value < target)
        {
            value++;
            updateImage();
        }
        else if (value > target)
        {
            value--;
            updateImage();
        }
    }

    public void add(int score)
    {
        target += score;
    }

    public int getValue()
    {
        return value;
    }

    /**
     * Update the counter image
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        image.drawString(prefix + value, 1, 12);
    }
}
