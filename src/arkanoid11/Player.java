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
import javax.swing.*;

public class Player { 
    
    public static int standardPlayerWidth = 100;
    public static int standardPlayerHeight = 20;
    private Rectangle hitBox;
    private game instance;
    
 
    public Player(game inst, int x, int y, int width, int height) {
        
        instance = inst;
        
        hitBox = new Rectangle (x, y, width, height);
        
    
}

    public void setX(int x)
            
    {
        hitBox.x = x;    
    }
    
    
    
    public void setY(int y)
            
    {
        hitBox.y = y;    
    }
    
    public void moveOnXAxis(int speed)
    {
        
        hitBox.x += speed;
        if(hitBox.x < 0) hitBox.x = 0;
        if(hitBox.x > instance.getGameDimension().width - instance.getPlayer().hitBox.width) hitBox.x = instance.getGameDimension().width - instance.getPlayer().hitBox.width;
        
        //hitBox.x = ((hitBox.x < 0)? 0:((hitBox.x > instance.getGameDimension().width-instance.getPlayer().hitBox.width)? instance.getGameDimension().width - instance.getPlayer().hitBox.width:(hitBox.x += speed)));
    }
    
    public boolean collides (Rectangle object)
    {
        return hitBox.intersects(object);
        
    }
     public int getWidth()
     {
         return this.hitBox.width;
     }
     
     public int getHeight()
     {
         return this.hitBox.height;
     }
    
    
    public void render(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    
    }
}
    

