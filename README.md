# Breakout - the classic arcade game

Breakout is a clone of the classic arcade game of the same name. It was hacked together for the [Java weekend course](http://www.ctd.northwestern.edu/sep/program/awe/) I helped teach this past weekend. The goal was to show the kids (5th-6th graders) the type of games that could easily be built on [Greenfoot](http://greenfoot.org), a framework and IDE which allowed the students to begin learning Java through simultaneous experimentation and lecture on theory. 

## Install

You first need to [download Greenfoot](http://www.greenfoot.org/download). The install process is pretty straightforward, assuming you already have a JDK installed. 

Now follow the normal git process:

    git clone git@github.com:codyaray/breakout.git

## Running 

Once you have Greenfoot installed and the source code for Breakout downloaded, you should be able to double-click `project.greenfoot` to launch Breakout in development mode.

If you want a single package that you can easily share (no source code, etc.), click "Share" in the top right corner, click the "Application" tab, choose a location to save to, check "Lock scenario", and click "Export". This will produce a jar file (`Breakout.jar`) that can be easily shared; just double-click Breakout.jar to start the Breakout game.

## Gameplay

This clone follows the original rules, scoring system, and color scheme fairly closely. The left and right arrow keys control the paddle.

There are 8 rows of 12 columns each (originally 14). There are 2 rows of each color: yellow, green, orange, red. Blocks of each color are 1, 3, 5, and 7 points, respectively. The bounce strategy is naive: if we're going down and need to bounce up, the ball negates `dy` (speed in `y` direction). This means `dy = -dy`. Similar goes for bouncing horizontally (off the walls): `dx = -dx`. Otherwise, the movement of the paddle doesn't affect the way the ball bounces, nor can it bounce off the edge of the paddle or bricks, nor is there any notion of the rotation of the ball. 

## Credits

The Counter and ScoreBoard classes were shamelessly copied from the `asteroids` scenario that is included with the Greenfoot book (and available online).

