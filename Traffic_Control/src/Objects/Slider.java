package Objects;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Controllers.TrafficManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * Created by SaiKu on 11/03/2016.
 */
public class Slider extends JPanel{

    private JLabel congestionLabel,emergencyLabel,roadNetworkLabel,timeIntervalLabel,weatherLabel, speedLimitLabel;
    private JSlider congestionSlider,timeIntervalSlider,speedLimitSlider;
    private JComboBox<String> weatherComboBox, emergencyComboBox,congestionComboBox;
    GridLayout gd;
    private TrafficManagement trafficManagement;

    

    public Slider(TrafficManagement trafficManagement) {
    	initComponents();
        this.trafficManagement = trafficManagement;	}


	


	private void initComponents() {

        //Create JLabel
        weatherLabel = new JLabel();
        timeIntervalLabel = new JLabel();
        congestionLabel = new JLabel();
        emergencyLabel = new JLabel();
        roadNetworkLabel = new JLabel();
        speedLimitLabel = new JLabel();
        //Create JSlider
        timeIntervalSlider = new JSlider(0,5000);
        congestionSlider = new JSlider();
        speedLimitSlider = new JSlider();
        //Create JComboBox
        weatherComboBox = new JComboBox<>();
        emergencyComboBox = new JComboBox<>();
        congestionComboBox = new JComboBox<>();

        //slider interface for time interval and congestion rate
        timeIntervalSlider.setMajorTickSpacing(1000);
        timeIntervalSlider.setMinorTickSpacing(500);
        timeIntervalSlider.setPaintLabels(true);
        timeIntervalSlider.setPaintTicks(true);
        timeIntervalSlider.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        timeIntervalSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                timeIntervalStateChanges(evt);
            }
        });
        speedLimitSlider.setMajorTickSpacing(20);
        speedLimitSlider.setMinorTickSpacing(10);
        speedLimitSlider.setPaintLabels(true);
        speedLimitSlider.setPaintTicks(true);
        speedLimitSlider.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        speedLimitSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                speedLimitStateChanges(evt);
            }
        });

        //set labels for interfaces
        emergencyLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        emergencyLabel.setBorder(BorderFactory.createEmptyBorder(20,10,0,0));
        emergencyLabel.setText("Emergency Services:");
        roadNetworkLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        roadNetworkLabel.setBorder(BorderFactory.createEmptyBorder(20,10,0,0));
        roadNetworkLabel.setText("Congestion Rate:");
        timeIntervalLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        timeIntervalLabel.setBorder(BorderFactory.createEmptyBorder(20,10,0,0));
        timeIntervalLabel.setText("Time Interval:");
        congestionLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        congestionLabel.setBorder(BorderFactory.createEmptyBorder(20,10,0,0));
        congestionLabel.setText("Congestion Rate:");
        weatherLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        weatherLabel.setBorder(BorderFactory.createEmptyBorder(20,10,0,0));
        weatherLabel.setText("Weather Option:");
        speedLimitLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        speedLimitLabel.setBorder(BorderFactory.createEmptyBorder(20,10,0,0));
        speedLimitLabel.setText("Speed Limit Option:");

        String[] weatherConditions=new String[] { "   Normal", "   Hazardous", "   Night", "   Day" };
        weatherComboBox=new JComboBox<>(weatherConditions);
        weatherComboBox.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        weatherComboBox.setPreferredSize(new Dimension(200,20));
        weatherComboBox.setPrototypeDisplayValue("Normal");
        weatherComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                weatherComboBoxActionPerformed(evt);
            }
        });

        String[] congestionCondition=new String[] { "   Normal", "   High", "   Low" };
        congestionComboBox=new JComboBox<>(congestionCondition);
        congestionComboBox.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        congestionComboBox.setPreferredSize(new Dimension(200,20));
        congestionComboBox.setPrototypeDisplayValue("Normal");
        congestionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                congestionComboBoxActionPerformed(evt);
            }
        });

        emergencyComboBox.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        emergencyComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "   0", "   1", "   2", "   3" }));
        emergencyComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                emergencyComboBoxActionPerformed(evt);
            }
        });


       gd=new GridLayout(13,0,20,0);

       // gd=new GridLayout(13,0,20,5);
        this.setLayout(gd);
        add(timeIntervalLabel);
        add(timeIntervalSlider);

//        add(congestionLabel);
//        add(congestionSlider);
//        add(congestionValue);

        add(speedLimitLabel);
        add(speedLimitSlider);


        add(congestionLabel);
        add(congestionComboBox);

        add(weatherLabel);
        add(weatherComboBox);

        add(emergencyLabel);
        add(emergencyComboBox);

    }// </editor-fold>

    private void weatherComboBoxActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void emergencyComboBoxActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void congestionComboBoxActionPerformed(ActionEvent evt) {
    	if (congestionComboBox.getSelectedItem()=="Normal"){
    		congestionComboBox.getSelectedIndex();
    		
    	}
    	else if(congestionComboBox.getSelectedItem() == "High"){
    		congestionComboBox.getSelectedIndex();
    		
    	}
    	else if(congestionComboBox.getSelectedItem() =="Low"){
    		congestionComboBox.getSelectedIndex();
    		
    	}
        
    }

    private void timeIntervalStateChanges(ChangeEvent evt) {
     /*if (timeIntervalSlider.getValueIsAdjusting())
       {
         System.out.println("The value is"+timeIntervalSlider.getValue());
       }*/
     //System.out.println(timeIntervalSlider.getValue());
    	trafficManagement.setTimeGranularity(timeIntervalSlider.getValue());
    	
    	
    	
    }
  

    
    private void speedLimitStateChanges(ChangeEvent evt) {
        // TODO add your handling code here:
    }


}
