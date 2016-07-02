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
import java.awt.*;

public class Ball {
    
    public static int standardBallRadius = 10;
    private game instance;
    private Dimension vector = new Dimension(0,0);
    private Point pos = new Point(0,0);
    private int radius;
    
    public Ball(game inst,int x, int y, int radius) {
        instance = inst;
        pos = new Point (x, y);
        this.radius = radius;   
}
    
    public void setVector(int xMovement, int yMovement)
    {
        vector = new Dimension(xMovement, yMovement);
    }
    
    public Point getPosition()
    {
        return pos;
    }
    
    public void setPosition( int x, int y)
    {
        pos = new Point(x,y);
    }
    
    public void tick()
    {
        if (pos.x - radius <= 0 && vector.width < 0) vector.width = - vector.width;
        if (pos.x + radius >= instance.getGameDimension().width && vector.width > 0) vector.width = - vector.width;
        if (pos.y - radius <= 0 && vector.height < 0) vector.height = - vector.height;
        if (pos.y + radius >= instance.getGameDimension().height && vector.height > 0) instance.loseBall();
        
       pos.move(pos.x+vector.width,pos.y+vector.height);
        
        if (instance.getPlayer() != null)
        {
            if(instance.getPlayer().collides(new Rectangle(pos.x - radius+vector.width, pos.y - radius+vector.height, radius*2, radius*2))) 
                vector.height = - vector.height;
        }
        
  
    
         
     for (Platforms[] pls : instance.getPlatforms())
       {
           for (Platforms p : pls)
           {
               if (p.collides(new Rectangle(pos.x - radius, pos.y - radius, radius*2, radius*2)))
               {
                   p.destroy();
                   vector.height = -vector.height;
                   boolean won = true; 
                  for (Platforms[] pls2 : instance.getPlatforms())
       {
           for (Platforms p2 : pls)
           {
               if (!p2.isDestroyed()) won = false;
          
           
       
                  if (won) instance.playerwon();
           }   
             
       }
               }
           }
           
       }
       
}
    
    public void render(Graphics g)
            
    {
        g.setColor(Color.blue);
        g.fillOval(pos.x - radius, pos.y - radius, radius*2, radius*2);
        
    }
    
}
