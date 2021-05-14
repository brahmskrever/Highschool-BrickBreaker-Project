import java.awt.*;
public class Paddle extends Brick
{
    // instance variables (or fields)
    private int speed; // speed of the paddle
    
    // constructor
    public Paddle (int xa , int ya, int w, int h, Color c, int s)
    {
        super(xa,ya,w,h,c);
        speed = s;
    }
    // accessors
    public int getSpeed()
    {
        return speed;
    }
    // mutators
    public void setSpeed (int s)
    {
        speed = s;
    }

    // moves the paddle right
    public void moveRight()
    {
        setX(getX() + speed);
    }

    // moves the paddle left
    public void moveLeft()
    {
        setX(getX() - speed);
    }

    // if the ball hits the paddle
    // the paddle will always be true
    public void hit()
    {
        setVisible(true);
    }
}