import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
public class BrickBreakerGame extends JPanel implements KeyListener, ActionListener
{
    private Ball[] ball = new Ball[2];
    private Timer timer;
    private boolean play;
    private Brick[] brick = new Brick[20];
    private Paddle paddle;
    private Image bh; 
    private Image title; 
    private int screen = 0;
    private int lives = 2;
    public BrickBreakerGame()
    {
        int x;
        int y;
        int z;
        z =(int)(Math.random()*7 + 1);
        y =(int)(Math.random()*4 + 1);
        ball[0] = new Ball(250, 250, 20, z, y,Color.white);
        ball[1] = new Ball(400, 300, 20, z, y,Color.white);
        int r = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);
        int b = (int)(Math.random()*256);
        Color color = new Color(r, g, b);
        //This initializes every first brick in a row 
        brick[0] = new Brick(5,5,97,20,color); 
        brick[5] = new Brick(5,47,97,20,color); 
        brick[10] = new Brick(5,89,97,20,color); 
        brick[15] = new Brick(5,131,97,20,color); 
        // this creates the bricks in a row aswell as determines if the brick
        // is a special brick or not(20% chance)
        for(int i = 1; i < 5; i++)
        {
            x =(int)(Math.random()*100);
            if (x>=80)
                brick[i] = new S_Brick(brick[i-1].getX()+98,5,97,20,color,3); 
            else
                brick[i] = new Brick(brick[i-1].getX()+98,5,97,20,color);
        }
        for(int i = 6; i <10; i++)
        {
            x =(int)(Math.random()*100);
            if (x>=80)
                brick[i] = new S_Brick(brick[i-1].getX()+98,47,97,20,color,3); 
            else 
                brick[i] = new Brick(brick[i-1].getX()+98,47,97,20,color);
        }
        for(int i = 11; i < 15; i++)
        {
            x =(int)(Math.random()*100);
            if (x>=80)
                brick[i] = new S_Brick(brick[i-1].getX()+98,89,97,20,color,3); 
            else
                brick[i] = new Brick(brick[i-1].getX()+98,89,97,20,color);
        }
        for(int i = 16; i <20; i++)
        {
            x =(int)(Math.random()*100);
            if (x>=80)
                brick[i] = new S_Brick(brick[i-1].getX()+98,131,97,20,color,3); 
            else 
                brick[i] = new Brick(brick[i-1].getX()+98,131,97,20,color);
        }
        paddle = new Paddle(225,470,120,10,Color.white,30);
        addKeyListener(this);
        timer = new Timer(20,this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer.start();
        play = false;
        //creates the background for the game
        try
        {
            bh = ImageIO.read(new File("Brideshead.jpg"));
        }
        catch (IOException e)
        {
        }
    }

    public void paint(Graphics g)
    {
        //draws the title screen aka before game starts
        if(screen == 0)
            drawTitleScreen(g);

        if (screen ==1)
        {
            play = true;
            // draw the walls and the background
            g.setColor(Color.green);
            g.fillRect(0,0,500,500);
            g.drawImage(bh,5,5,null);

            // draws ball
            for(int i = 0; i < ball.length;i++)
                ball[i].move(g);
            //draws the brick and check for collision
            for(int i = 0; i < brick.length; i++)
            {
                if (brick[i].getVisible())
                {
                    brick[i].draw(g);
                    for(int j = 0; j<ball.length;j++)
                        ball[j].collidesWith(brick[i]);
                }
            }
            // draws the paddle and checks if it colides with the paddle
            paddle.draw(g);
            for(int j = 0; j<ball.length;j++)
                ball[j].collidesWith(paddle);

        }
        //draws the end screen
        else if (screen == 2)
            drawEndScreen(g);

    }

    public void drawTitleScreen(Graphics g)
    {
        // creates the image for the Title screen
        try
        {
            title = ImageIO.read(new File("title.jpg"));
        }
        catch (IOException e)
        {
        }
        g.drawImage(title,5,5,null);
        Color myWhite = new Color(255, 255, 255);
        g.setColor(myWhite);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
        g.drawString("Press Enter To Start",70,250);
        g.drawString(" You Have 2 Lives" , 55 , 300) ;
        g.drawString("To Replay Press Enter" , 70 ,350);
    }

    public void drawEndScreen(Graphics g)
    {
        // creates the image for the endscreen
        try
        {
            title = ImageIO.read(new File("title.jpg"));
        }
        catch (IOException e)
        {
        }
        g.drawImage(title,5,5,null);
        Color myWhite = new Color(255, 255, 255);
        g.setColor(myWhite);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
        g.drawString("Please Play Again",70,250);

    }

    //constatly repaints the screen if the ball is in play
    public void actionPerformed(ActionEvent e)
    { 
        if(play)
            repaint();
    }

    public void keyPressed(KeyEvent e)
    { 
        play = true;
        //moves paddle right
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            paddle.moveRight();
            screen = 1;
            if(paddle.getX() + paddle.getWidth()>=495)
                paddle.setX(494-paddle.getWidth());
        }
        // moves paddleleft
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            paddle.moveLeft();
            screen = 1;
            if(paddle.getX() <= 5)
                paddle.setX(6);
        }
        // players choice to restart the game
        if (e.getKeyCode() == KeyEvent.VK_ENTER && lives>=0)
        {
            ball[0].setX((int)(Math.random()*490 + 1));
            ball[0].setY((int)(Math.random()*200 + 200));
            ball[0].setDx((int)(Math.random()*7 + 1));
            ball[0].setDy((int)(Math.random()*4 + 1));
            ball[1].setX((int)(Math.random()*490 + 1));
            ball[1].setY((int)(Math.random()*200 + 200));
            ball[1].setDx((int)(Math.random()*7 + 1));
            ball[1].setDy((int)(Math.random()*4 + 1));
            // all bricks that were destroyed during the first game are 
            // recreated
            for(int i = 0; i<brick.length; i++)
            {
                if(brick[i].getVisible()==false)
                    brick[i].setVisible(true);
            }
            repaint();
            lives--;
        }
        // checks if the ball dx and dy varibles are 0 b/c that means the 
        // game has ended
        if(ball[0].getCheck()==false&& ball[1].getCheck()==false && lives<=0)
        {
            screen = 2;
        }

        // EASTER EGG this sets the speed of the ball x 2
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            for(int i = 0 ; i<2; i++)
            {
                ball[i].setDx(ball[i].getDx() * -2);
                ball[i].setDy(ball[i].getDx() * -2);
            }
        }
    }

    public void keyReleased(KeyEvent e)
    { 

    }

    public void keyTyped(KeyEvent e)
    { 

    }
}