package Objects;

import Controllers.TrafficManagement;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Slider extends JPanel {

    private JLabel congestionLabel, emergencyLabel, roadNetworkLabel, timeIntervalLabel, weatherLabel, speedLimitLabel;
    private JSlider timeIntervalSlider, speedLimitSlider;
    private JButton increaseButton, decreaseButton;
    private JComboBox<String> weatherComboBox, emergencyComboBox, congestionComboBox;
    private TrafficManagement trafficManagement;

    public Slider(TrafficManagement trafficManagement) {
        initComponents();
        this.trafficManagement = trafficManagement;
    }

    public void initComponents() {

        //Create JLabel
        weatherLabel = new JLabel();
        timeIntervalLabel = new JLabel();
        congestionLabel = new JLabel();
        emergencyLabel = new JLabel();
        roadNetworkLabel = new JLabel();
        speedLimitLabel = new JLabel();

        //Create JSlider
        timeIntervalSlider = new JSlider(0, 100);
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
        timeIntervalSlider.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        timeIntervalSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                timeIntervalStateChanges(evt);
            }
        });
        speedLimitSlider.setMajorTickSpacing(20);
        speedLimitSlider.setMinorTickSpacing(10);
        speedLimitSlider.setPaintLabels(true);
        speedLimitSlider.setPaintTicks(true);
        speedLimitSlider.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        speedLimitSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                speedLimitStateChanges(evt);
            }
        });

        //set labels for interfaces
        emergencyLabel.setFont(new Font("Verdana", Font.BOLD, 12)); // NOI18N
        emergencyLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 0));
        emergencyLabel.setText("Emergency Services:");
        roadNetworkLabel.setFont(new Font("Verdana", Font.BOLD, 12)); // NOI18N
        roadNetworkLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 0));
        roadNetworkLabel.setText("Congestion Rate:");
        timeIntervalLabel.setFont(new Font("Verdana", Font.BOLD, 12)); // NOI18N
        timeIntervalLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 0));
        timeIntervalLabel.setText("Time Interval:");
        congestionLabel.setFont(new Font("Verdana", Font.BOLD, 12)); // NOI18N
        congestionLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 0));
        congestionLabel.setText("Congestion Rate:");
        weatherLabel.setFont(new Font("Verdana", Font.BOLD, 12)); // NOI18N
        weatherLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 0));
        weatherLabel.setText("Weather Option:");
        speedLimitLabel.setFont(new Font("Verdana", Font.BOLD, 12)); // NOI18N
        speedLimitLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 0));
        speedLimitLabel.setText("Speed Limit Option:");

        String[] weatherConditions = new String[]{"   Normal", "   Hazardous", "   Night", "   Day"};
        weatherComboBox = new JComboBox<>(weatherConditions);
        weatherComboBox.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        weatherComboBox.setPreferredSize(new Dimension(200, 20));
        weatherComboBox.setPrototypeDisplayValue("Normal");
        weatherComboBox.setFont(new Font("Verdana", 0, 12));
        weatherComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                weatherComboBoxActionPerformed(evt);
            }
        });

        String[] congestionCondition = new String[]{"Please add cars", "Add 5 cars", "Add 10 cars", "Add 20 cars"};
        congestionComboBox = new JComboBox<>(congestionCondition);
        congestionComboBox.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        congestionComboBox.setPreferredSize(new Dimension(200, 20));
        congestionComboBox.setPrototypeDisplayValue("Please add cars");
        congestionComboBox.setFont(new Font("Verdana", 0, 12));
        congestionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                congestionComboBoxActionPerformed(evt);
            }
        });

        String[] emergencyCondition = new String[]{"No emergency services", "1", "2", "3"};
        emergencyComboBox = new JComboBox<>(emergencyCondition);
        emergencyComboBox.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        emergencyComboBox.setPreferredSize(new Dimension(200, 20));
        emergencyComboBox.setPrototypeDisplayValue("0");
        emergencyComboBox.setFont(new Font("Verdana", 0, 12));
        emergencyComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                emergencyComboBoxActionPerformed(evt);
            }
        });

        increaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                increaseButtonActionPerformed(evt);
            }
        });

        decreaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                decreaseButtonActionPerformed(evt);
            }
        });

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;

        c.ipady = 0;
        c.ipadx = 0;
        c.weightx = 0;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(timeIntervalLabel, c);

        c.weightx = 0.5;
        c.weighty = 0;
        c.ipady = 0;
        c.ipadx = 200;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(timeIntervalSlider, c);

        c.ipady = 0;
        c.ipadx = 0;
        c.weightx = 0;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(speedLimitLabel, c);

        c.ipady = 0;
        c.ipadx = 200;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(speedLimitSlider, c);

        c.ipady = 0;
        c.ipadx = 0;
        c.weightx = 0;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(congestionLabel, c);

        c.weightx = 0.5;
        c.weighty = 0;
        c.ipady = 0;
        c.ipadx = 200;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(congestionComboBox, c);

        c.weightx = 10;
        c.weighty = 0;
        c.ipady = 0;
        c.ipadx = 0;
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.insets = new Insets(10, 50, 0, 10);
        c.anchor = GridBagConstraints.CENTER;
        this.add(increaseButton, c);

        c.weightx = 10;
        c.weighty = 0;
        c.ipady = 0;
        c.ipadx = 0;
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = 1;
        //c.
        c.insets = new Insets(10, 10, 0, 50);
        c.anchor = GridBagConstraints.CENTER;
        this.add(decreaseButton, c);

        c.ipady = 0;
        c.ipadx = 0;
        c.weightx = 0;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 0, 0);
        this.add(weatherLabel, c);

        c.ipady = 0;
        c.ipadx = 200;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 8;
        this.add(weatherComboBox, c);

        c.ipady = 0;
        c.ipadx = 0;
        c.weightx = 0;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 9;
        this.add(emergencyLabel, c);

        c.ipady = 0;
        c.ipadx = 200;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 10;
        c.insets = new Insets(0, 0, 30, 0);
        this.add(emergencyComboBox, c);

    }

    public void increaseButtonActionPerformed(ActionEvent evt) {
        int click = 1;
        trafficManagement.factoryVehicle(click);
    }

    public void decreaseButtonActionPerformed(ActionEvent evt) {
        int click = 1;
        trafficManagement.deleteVehicle(click);
    }

    public void weatherComboBoxActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    /*
    This combo box is used to generate emergency cars.
    Emergency cars have red colour.
    This combo box provides 4 stated.
    The default state 0 indicates that no emergency cars exist in the system.
    The indicators 1,2 and 3 indicate the number of emergency cars in the system.
     */
    public void emergencyComboBoxActionPerformed(ActionEvent evt) {
        if (evt.getSource() == emergencyComboBox) {
            JComboBox emergencyComboBox = (JComboBox) evt.getSource();
            String msg = (String) emergencyComboBox.getSelectedItem();

            trafficManagement.factoryVehicle(Integer.parseInt(msg), 1);
        }
    }

    /*
    This combo box is used to set the level of congestion in the system.
    The default state is the "normal", where 10 cars are introduced in the system.
    The state "high" indicates rush hour, where 30 cars are introduced into te system.
    The state "low indicates" that a low amount of cars are to be included in the system, cars might be removed.
     */
    public void congestionComboBoxActionPerformed(ActionEvent evt) {
        if (evt.getSource() == congestionComboBox) {
            JComboBox congestionCombobox = (JComboBox) evt.getSource();
            String msg = (String) congestionCombobox.getSelectedItem();
            switch (msg) {
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
    public void timeIntervalStateChanges(ChangeEvent evt) {

        JSlider timeIntervalSlider = (JSlider) evt.getSource();
        if (!timeIntervalSlider.getValueIsAdjusting()) {
            trafficManagement.setTimeGranularity(timeIntervalSlider.getValue() / 5);
            trafficManagement.setTlDelay(timeIntervalSlider.getValue());
        }

    }

    public void speedLimitStateChanges(ChangeEvent evt) {
        // TODO add your handling code here:
    }
}
