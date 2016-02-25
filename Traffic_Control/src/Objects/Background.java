/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author daniella
 */
public class Background {
    
    Background(){
        
    }
    protected void doDrawing(Graphics g){
        //draw background 
        g.setColor(new Color (50,150,50));
        g.fillRect(0, 0, 700, 700);   
    }
}
