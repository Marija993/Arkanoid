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

import javax.swing.JFrame;

public class Arkanoid11 {
    
    public static JFrame frame;
    public static game gamee;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frame = new JFrame("Arcanoid");
        frame.setSize(900,700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        gamee = new game(frame,10,3);
        gamee.setSize(frame.getSize());
        frame.add(gamee);
        
        
    }
    
}
