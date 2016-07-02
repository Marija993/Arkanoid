/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid11;

/**
 *
 * @author user
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class game extends JPanel {
    private Dimension gameField = new Dimension(400,300);
    private boolean isRunning = false;
    private boolean isPaused = false;
    private boolean won = false; 
    private boolean lost = false; 
    private int ballCount; 
    
    private Platforms[][] platforms;
    private Player player;
    private Ball ball;
   
    
    public game(Frame container, int platformsOnX, int platformsOnY) {
        
        container.addKeyListener(new KeyAdapter() {
        
            public void keyPressed(KeyEvent e)
            {
                if( !isRunning && !lost && !won || isPaused && !lost && !won) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) start();
                    
                }
               
                //else if (!isRunning || isPaused) {
                   // if (e.getKeyCode() == KeyEvent.VK_ENTER) start();
                //}
                else {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.moveOnXAxis(20);
               if(e.getKeyCode() == KeyEvent.VK_LEFT) player.moveOnXAxis(-20);
                }
             
            }       
        
        }); 
        
        
         platforms = new Platforms[platformsOnX][platformsOnY];
         for (int x = 0; x != platforms.length; x++)
         {
             for( int y = 0; y != platforms[0].length; y++)
             {
                 int pwidth = gameField.width/platformsOnX;
                 int pheight = (gameField.height/4)/platformsOnY;
                 
                 platforms[x][y] = new Platforms(x*pwidth, y*pheight, pwidth, pheight);
             }
             
         }
        player = new Player(this,(int)((gameField.getWidth()-Player.standardPlayerWidth)/2), gameField.height-Player.standardPlayerHeight, Player.standardPlayerWidth, Player.standardPlayerHeight);
        ball = new Ball(this,gameField.width/2,gameField.height/2,Ball.standardBallRadius); 
        ballCount = 3;
    }
    
    public void loseBall()
    {
        pause();
        ballCount -=1;
        if (ballCount <= 0) lost = true;
        ball.setVector(10,10);
        ball.setPosition(gameField.width/2, gameField.height/2);
        player.setX((int)((gameField.getWidth()-Player.standardPlayerWidth)/2));
        player.setY(gameField.height-Player.standardPlayerHeight);
        repaint();
        
    }
    
    public void playerwon()

    {
       won = true;   
        
    }
    public void start()
    {
        isPaused = false;
        if (!isRunning)
        gameThread.start();
    }
    
    public void pause()
    {
        isPaused = true;
        
        
    }
    
    public void stop()
    {
        isRunning = false;
        
    }
    
   public Dimension getGameDimension()
   {
       
      return gameField;
   }    
    public void setPlayer(Player player)
    {  
        this.player = player;
    
    }
    
    public Player getPlayer()
    {
        return this.player;
    }
    
    public Platforms[][] getPlatforms()
    {
        return this.platforms;
    }
    
   public void setSize(Dimension size)
   {
       super.setSize(size);
       if (!isRunning)
       {
       gameField = new Dimension(size.width-200,size.height-200);
       for (int x = 0; x != platforms.length; x++)
         {
             for( int y = 0; y != platforms[0].length; y++)
             {
                 int pwidth = gameField.width/platforms.length;
                 int pheight = (gameField.height/4)/platforms[0].length;
                 
                 platforms[x][y] = new Platforms(x*pwidth, y*pheight, pwidth, pheight);
             }
             
         }
       ball.setPosition(gameField.width/2,gameField.height/2);
       player.setX((int)((gameField.getWidth()-Player.standardPlayerWidth)/2));
       player.setY(gameField.height-Player.standardPlayerHeight);
       }
   }
   
   
  private Thread gameThread = new Thread(new Runnable() {
  
      public void run() {
          isRunning = true;
          {
              ball.setVector(7,7);
              
              while(isRunning)
              {
                  if(!isPaused){
                  ball.tick();
                  
                  won = true; 
                  for (Platforms[] pls : platforms)
       {
           for (Platforms p : pls)
           {
               if (!p.isDestroyed()) won = false;
           }
           
      }
                  repaint();
                  try
                  {
                      Thread.sleep(40);
                      
                  } catch (Exception e) 
                  {}
                  
                  }  
              }   
          }          
      } 
      
  });
          
          
          
  
  
    
    public void paint(Graphics g)
    {
       super.paint(g);
       
      g.translate((getWidth()-gameField.width)/2,(getHeight()-gameField.height)/2);
      g.setColor(Color.BLACK);
      int radius = 4; 
      for(int i = 0; i != ballCount; i++)
      {
          g.fillOval(i*radius*2, -(radius*2+3), radius*2, radius*2);
      }
      g.setColor(new Color(255,255,255));
      g.fillRect(0, 0, gameField.width, gameField.height);
      
       ball.render(g);
       player.render(g);
       
       for (Platforms[] pls : platforms)
       {
           for (Platforms p : pls)
           {
               p.render(g);
           }
           
       }
       
       g.setColor(new Color(0,0,0));
      g.drawRect(0,0, gameField.width,gameField.height);
      
      
      if (won)
      {
         g.drawString("You won!!!", 100, 100);
         stop();
      }
      
      if (lost)
      {
          g.drawString("Game is over!", 100, 100);
          
      }
    
}
    
}
