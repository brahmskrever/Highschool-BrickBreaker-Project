import java.awt.*;

public class Brick
{
    // instance variables (or fields)
    private int x; 
    private int y; 
    private int width; 
    private int height; 
    private Color color;
    private boolean visible;
    private int total = 0;
    // constructor
    public Brick(int xa, int ya, int w ,int h, Color c)
    {
        x = xa;
        y = ya;
        width = w;
        height = h;
        color = c;
        visible = true;
    }

    // accessors
    public int getX(){return x;}

    public int getY(){return y;}

    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public boolean getVisible(){return visible;}

    // mutators
    public void setX(int xa){x = xa;}

    public void setY(int ya){y = ya;}

    public void setWidth(int w){width = w;}

    public void setHeight(int h){height = h;}

    public void setColor(Color c){color = c;}

    public void setVisible(boolean v){visible = v;}

    //draws the brick
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    // the brick is destroyed
    public void hit()
    {
        visible = false;
    }
}