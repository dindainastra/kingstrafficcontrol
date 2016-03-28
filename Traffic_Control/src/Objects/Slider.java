package Objects;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.bind.SchemaOutputResolver;

import Controllers.TrafficManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class Slider  extends JPanel {

    private JLabel congestionLabel,emergencyLabel,roadNetworkLabel,timeIntervalLabel,weatherLabel, speedLimitLabel;
    private JSlider congestionSlider,timeIntervalSlider,speedLimitSlider;
    private JButton increaseButton, decreaseButton;
    private JComboBox<String> weatherComboBox, emergencyComboBox,congestionComboBox;
    GridLayout gd;
    private TrafficManagement trafficManagement;
    private Car car;
    //private Terrain terrain;



    public Slider(TrafficManagement trafficManagement) {
    	initComponents();
        this.trafficManagement = trafficManagement;
        //this.trafficLights = trafficLights;
    }





	private void initComponents() {

        //Create JLabel
        weatherLabel = new JLabel();
        timeIntervalLabel = new JLabel();
        congestionLabel = new JLabel();
        emergencyLabel = new JLabel();
        roadNetworkLabel = new JLabel();
        speedLimitLabel = new JLabel();
        //Create JSlider
        timeIntervalSlider = new JSlider(0, 100);
        congestionSlider = new JSlider();
        speedLimitSlider = new JSlider();
        //Create JComboBox
        weatherComboBox = new JComboBox<>();
        emergencyComboBox = new JComboBox<>();
        congestionComboBox = new JComboBox<>();
        //Create JButton
        increaseButton = new JButton("+");
        decreaseButton = new JButton("-");

        //slider interface for time interval and congestion rate
        timeIntervalSlider.setMajorTickSpacing(10);
        timeIntervalSlider.setMinorTickSpacing(10);
        timeIntervalSlider.setPaintLabels(true);
        timeIntervalSlider.setPaintTicks(true);
        timeIntervalSlider.setValue(50);
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

        String[] congestionCondition=new String[] {"Add cars to the system", "Add 5 cars", "Add 10 cars", "Add 20 cars" };
        congestionComboBox=new JComboBox<>(congestionCondition);
        congestionComboBox.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        congestionComboBox.setPreferredSize(new Dimension(200,20));
        congestionComboBox.setPrototypeDisplayValue("Add cars to the system");
        congestionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                congestionComboBoxActionPerformed(evt);
            }
        });

        String[] emergencyCondition=new String[] { "No emergency services", "1", "2", "3"  };
        emergencyComboBox=new JComboBox<>(emergencyCondition);
        emergencyComboBox.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        emergencyComboBox.setPreferredSize(new Dimension(200,20));
        emergencyComboBox.setPrototypeDisplayValue("0");
        emergencyComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                emergencyComboBoxActionPerformed(evt);
            }
        });

//        increaseButton.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
//        increaseButton.setPreferredSize(new Dimension(200,20));
        increaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                increaseButtonActionPerformed(evt);
            }
        });

//        decreaseButton.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
//        decreaseButton.setPreferredSize(new Dimension(200,20));
        decreaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                decreaseButtonActionPerformed(evt);
            }
        });



        gd=new GridLayout(13,0,20,0);
       /// / gd=new GridLayout(13,0,20,5);
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

        //this.setLayout(gp);
       // gp.setColumns(2);
        add(increaseButton);
        add(decreaseButton);


        add(weatherLabel);
        add(weatherComboBox);

        add(emergencyLabel);
        add(emergencyComboBox);

    }// </editor-fold>

    private void increaseButtonActionPerformed(ActionEvent evt) {
        trafficManagement.factoryVehicle(1);
    }

    private void decreaseButtonActionPerformed(ActionEvent evt) {
        trafficManagement.deleteVehicle(1);
    }

    private void weatherComboBoxActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
    This combo box is used to generate emergency cars.
    Emergency cars have red colour.
    This combo box provides 4 stated.
    The default state 0 indicates that no emergency cars exist in the system.
    The indicators 1,2 and 3 indicate the number of emergency cars in the system.
     */
    private void emergencyComboBoxActionPerformed(ActionEvent evt) {
        if(evt.getSource()== emergencyComboBox){
            JComboBox emergencyComboBox = (JComboBox)evt.getSource();
            String msg = (String)emergencyComboBox.getSelectedItem();

            trafficManagement.factoryVehicle(Integer.parseInt(msg),1);
        }
    }

    /*
    This combo box is used to set the level of congestion in the system.
    The default state is the "normal", where 10 cars are introduced in the system.
    The state "high" indicates rush hour, where 30 cars are introduced into te system.
    The state "low indicates" that a low amount of cars are to be included in the system, cars might be removed.
     */
    private void congestionComboBoxActionPerformed(ActionEvent evt) {
    	if(evt.getSource()== congestionComboBox){
            JComboBox congestionCombobox = (JComboBox)evt.getSource();
            String msg = (String)congestionCombobox.getSelectedItem();
            switch (msg){
                case "Add 10 cars":
                    int normal = 10;
                    trafficManagement.factoryVehicle(normal);
                    break;
                case "Add 20 cars":
                    int high = 20;
                    trafficManagement.factoryVehicle(high);
                    break;
                case "Add 5 cars":
                    int low = 5;
                    trafficManagement.factoryVehicle(low);

                    break;
            }
        }

    }

    /*This slider is used for setting the time granularity of the system.
    The bigger the value of the slider the bigger the delay, hence the slower the system is going to be.
    */
    private void timeIntervalStateChanges(ChangeEvent evt) {

        JSlider timeIntervalSlider =(JSlider)evt.getSource();
        if (!timeIntervalSlider.getValueIsAdjusting()) {
            trafficManagement.setTimeGranularity(timeIntervalSlider.getValue()/5);
            trafficManagement.setTlDelay(timeIntervalSlider.getValue());



        }

    }






    private void speedLimitStateChanges(ChangeEvent evt) {
        // TODO add your handling code here:
    }


}
