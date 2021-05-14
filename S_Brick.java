import java.awt.*;

public class S_Brick extends Brick
{
    // instance variables (or fields)
    private int health; 
    private Color Color1; 
    // constructor
    public S_Brick(int xa, int ya, int w ,int h, Color c, int he)
    {
        super(xa,ya,w,h,c);
        health = he;
    }
    // accessors
    public int getHealth(){return health;}
    // mutators
    public void setX(int he){health = he;}
    // if the ball is hit rather than disappearing it losses health 
    //until destroyed
    public void hit()
    {
        health--;
        if(health == 2)
            setColor(Color.blue);
        else if (health == 1)
            setColor(Color.yellow);
        else if (health == 0)
            setVisible(false);
    }
}