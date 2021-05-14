import java.awt.*;
import javax. swing.*;
public class Ball
{
    // instance variables (or fields)
    private int x; // top left x value
    private int y; // top left y value
    private int size; // diameter
    private int dx; // movement in the x direction
    private int dy; // movement in the y direction
    private Color color; // Color
    private boolean check = true; // checks if the ball's dx and dy are 0

    // encapsulation

    // constructor
    public Ball(int xa, int ya, int s, int dxa, int dya, Color c)
    {
        x = xa;
        y = ya;
        size = s;
        dx = dxa;
        dy = dya;
        color = c;
    }
    // default constructer 
    public Ball()
    {
        x = 0;
        y = 0;
        size = 0;
        dx = 0;
        dy = 0;
        color = Color.black;
    }

    // accessors
    public int getX(){return x;}

    public int getY(){return y;}

    public int getSize(){return size;}

    public int getDx(){return dx;}

    public int getDy(){return dy;}
    
    public boolean getCheck() {return check;}

    // mutators
    public void setX(int xa){ x = xa;}

    public void setY(int ya){y = ya;}

    public void setSize(int s){size = s;}

    public void setDx(int dxa){dx = dxa;}

    public void setDy(int dya){dy = dya;}

    public void setColor(Color c){color = c;}

    public void move(Graphics g)
    {
        //ball's movement
        x += dx;
        y += dy;

        // check collision with left or right walls

        if (x < 5)// left wall
        {
            x = 5;
            dx = -dx;
        }
        else if (x + size > 495)
        {
            x = 495 - size;
            dx = -dx;
        }

        // check collision with top wall

        if (y < 5)// left wall
        {
            y = 5;
            dy = -dy;
        }

        //check if game is over
        if (y + size > 500)// left wall
        {
            dx = 0;
            dy = 0;
            check = false;
        }

        draw(g);
    }
    // draws the ball
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }

    public void collidesWith(Brick b)
    {
        // checks if the ball collides with the brick
        Rectangle ball = new Rectangle(x,y,size,size);
        Rectangle brick = new Rectangle(b.getX(),b.getY(),b.getWidth(),b.getHeight());
        if(ball.intersects(brick))
        {
            b.hit();
            if(x < b.getX() || x + size > b.getX() + b.getWidth())
                dx = -dx;
            else
                dy = - dy;
        }
    }
}