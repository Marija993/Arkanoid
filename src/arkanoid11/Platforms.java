



package arkanoid11;

/**
 *
 * @author user
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Platforms {
    
    private boolean isDestroyed = false;
    private Rectangle hitBox;
    
    public Platforms(int x, int y, int width, int height)
    {
        hitBox = new Rectangle(x,y,width,height);
    }
    public boolean collides (Rectangle object)
    {
        return (isDestroyed)? false : hitBox.intersects(object);
        
    }
    
    public boolean isDestroyed(){
        
        return isDestroyed;
    }
    
    public void destroy() {
        
        isDestroyed = true;
    }
    
    
    public void render(Graphics g)
    {
        
        if (!isDestroyed)
        {
        g.setColor(Color.ORANGE);
        g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
        g.setColor(new Color(0,0,0));
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
       }
    }
    
}
