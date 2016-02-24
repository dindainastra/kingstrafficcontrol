/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package new_project;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author SaiKu
 */
public class New_project extends JPanel {

    private void doDrawing(Graphics g){
        Graphics2D car1=(Graphics2D) g;
        Graphics2D car2=(Graphics2D) g;
        Graphics2D road=(Graphics2D) g;
        Graphics2D road_divider2=(Graphics2D) g;
        Graphics2D road_divider1=(Graphics2D) g;
        Graphics2D main_divider=(Graphics2D) g;
        Graphics2D arrow_right=(Graphics2D)g;
        Graphics2D arrow_left=(Graphics2D)g;
        
        //draw road
        road.setColor(Color.GRAY);
        road.fillRect(15,25,600,100);
        
        //Arrows
       // arrow_right.
        //arrow_right.
        
        //draw car        
        car1.setColor(Color.blue);
        car1.fillRect(20,30,20,15);
        
        car2.setColor(Color.yellow);
        car2.fillRect(590,105,20,15);
        //car2.fillRect(WIDTH, WIDTH, WIDTH, WIDTH);
        
        //draw road divider
        float[] dash3 = {4f, 0f, 2f};
        float[] dash1 = {2f,0f,2f};
        
        main_divider.setColor(Color.white);
        main_divider.drawLine(15,75,612,75);
                
        BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash3, 2f);
        
        road_divider1.setStroke(bs3);
        road_divider1.setColor(Color.white);
        road_divider1.drawLine(15,50,612,50);
        road_divider2.drawLine(15,100,612,100);
        
        //road_divider.drawline            
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
        
    }
    
    
}
