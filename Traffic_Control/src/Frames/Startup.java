package Frames;

import Controllers.TrafficManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Startup extends JPanel {

    private GridLayout group;
    private JButton crossRoadMapButton, townMapButton, roundaboutMapButton;
    private TrafficManagement trafficManagement = new TrafficManagement();
    public Startup() {


        initComponents();
    }

    public void initComponents() {
        //Create JButton
        crossRoadMapButton = new JButton("Cross Road");
        crossRoadMapButton.setFont(new Font("Verdana", Font.BOLD, 12));
        townMapButton = new JButton("Town      ");
        townMapButton.setFont(new Font("Verdana", Font.BOLD, 12));
        roundaboutMapButton = new JButton("Roundabout");
        roundaboutMapButton.setFont(new Font("Verdana", Font.BOLD, 12));

        group = new GridLayout(3, 0, 20, 0);
        this.setLayout(group);
        add(crossRoadMapButton);
        add(townMapButton);
        add(roundaboutMapButton);

        //set play, pause, stop and replay button icon and ActionListener
        crossRoadMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                crossRoadMapButtonActionPerformed(evt);
            }
        });
        townMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                townMapButtonActionPerformed(evt);
            }
        });
        roundaboutMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                roundaboutMapButtonActionPerformed(evt);
            }

        });
    }

    private void townMapButtonActionPerformed(ActionEvent evt) { //town
        trafficManagement.setOption(1);
        trafficManagement.run();

        ((JFrame) this.getTopLevelAncestor()).dispose();
    }

    private void crossRoadMapButtonActionPerformed(ActionEvent evt) { //cross road
        trafficManagement.setOption(0);
        trafficManagement.run();

         ((JFrame) this.getTopLevelAncestor()).dispose();
    }

    private void roundaboutMapButtonActionPerformed(ActionEvent evt) { //round about
        trafficManagement.setOption(2);
        trafficManagement.run();
          ((JFrame) this.getTopLevelAncestor()).dispose();
    }

}
