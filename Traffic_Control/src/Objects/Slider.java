package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * Created by SaiKu on 11/03/2016.
 */
public class Slider extends JPanel {

    private JLabel congestionLabel, congestionValue,emergencyLabel,roadNetworkLabel,time,timeIntervalLabel,weatherLabel,timeIntervalValue, speedLimitLabel;
    private JSlider congestionSlider,timeIntervalSlider,speedLimitSlider;
    private JComboBox<String> weatherComboBox, emergencyComboBox;
    GridLayout gd;


    public Slider(){
        initComponents();

    }


    private void initComponents() {

        //Create JLabel
        time = new JLabel();
        timeIntervalValue = new JLabel();
        congestionValue = new JLabel();
        weatherLabel = new JLabel();
        timeIntervalLabel = new JLabel();
        congestionLabel = new JLabel();
        emergencyLabel = new JLabel();
        roadNetworkLabel = new JLabel();
        speedLimitLabel = new JLabel();
        //Create JSlider
        timeIntervalSlider = new JSlider();
        congestionSlider = new JSlider();
        speedLimitSlider = new JSlider();
        //Create JComboBox
        weatherComboBox = new JComboBox<>();
        emergencyComboBox = new JComboBox<>();

        //create temp time text for later use (real time)
        time.setText("time");

        //slider interface for time interval and congestion rate
        timeIntervalSlider.setMajorTickSpacing(20);
        timeIntervalSlider.setMinorTickSpacing(10);
        timeIntervalSlider.setPaintLabels(true);
        timeIntervalSlider.setPaintTicks(true);
        timeIntervalSlider.addMouseListener(new MouseAdapter() {
           /* public void mousePressed(MouseEvent evt) {
                timeIntervalSliderMousePressed(evt);
            }*/
        });
        speedLimitSlider.setMajorTickSpacing(20);
        speedLimitSlider.setMinorTickSpacing(10);
        speedLimitSlider.setPaintLabels(true);
        speedLimitSlider.setPaintTicks(true);
        speedLimitSlider.addMouseListener(new MouseAdapter() {
           /* public void mousePressed(MouseEvent evt) {
                speedLimitSliderMousePressed(evt);
            }*/
        });
        congestionSlider.setMajorTickSpacing(20);
        congestionSlider.setMinorTickSpacing(10);
        congestionSlider.setPaintLabels(true);
        congestionSlider.setPaintTicks(true);
        congestionSlider.addMouseListener(new MouseAdapter() {
            /*public void mousePressed(MouseEvent evt) {
                congestionSliderMousePressed(evt);
            }*/
        });

        //set labels for interfaces
        emergencyLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        emergencyLabel.setText("Emergency Services");
        roadNetworkLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        roadNetworkLabel.setText("Road Network");
        timeIntervalLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        timeIntervalLabel.setText("Time Interval:");
        congestionLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        congestionLabel.setText("Congestion Rate:");
        weatherLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        weatherLabel.setText("Weather Option:");
        speedLimitLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        speedLimitLabel.setText("Speed Limit Option:");

        //add(congestionLabel);
        weatherComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Normal", "Hazardous", "Night", "Day" }));
        emergencyComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));

        gd=new GridLayout(12,0);
        this.setLayout(gd);
        gd.setVgap(15);

        add(timeIntervalLabel);

        add(timeIntervalSlider);
        add(timeIntervalValue);
        add(congestionLabel);
        add(congestionSlider);
        add(congestionValue);
        add(speedLimitLabel);
        add(speedLimitSlider);

        add(weatherLabel);
        add(weatherComboBox);
        add(emergencyLabel);
        add(emergencyComboBox);
        //add();
       // );
        //pack();
    }// </editor-fold>




}
