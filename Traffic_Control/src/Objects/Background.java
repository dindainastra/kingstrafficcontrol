/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author daniella
 */
public class Background {
    
    // Variables declaration                     
    private JCheckBox bridgeCheckBox, tollGateCheckBox, crossingCheckBox,dCarriagewayCheckBox, junctionCheckBox,rampsCheckBox,roundaboutsCheckBox,sCarriagewayCheckBox ;
    private JLabel congestionLabel, congestionValue,emergencyLabel,roadNetworkLabel,time,timeIntervalLabel,weatherLabel,timeIntervalValue;
    private JButton pauseButton, playButton, replayButton, stopButton;
    private JSlider congestionSlider,timeIntervalSlider;
    private JComboBox<String> weatherComboBox;
    private JLabel backgroundImage;
    private JPanel jPanel;
    // End of variables declaration
    
    Background(){
        
    }
    protected void doDrawing(Graphics g){
        //draw background 
        g.setColor(new Color (50,150,50));
        g.fillRect(0, 0, 700, 700);   
    }
}
