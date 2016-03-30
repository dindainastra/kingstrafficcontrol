package Frames;

import Controllers.TrafficManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Startup extends JPanel {

    private GridLayout group;
    private JButton randomMapButton, configurableMapButton, setMapButton;

    public Startup() {
        initComponents();
    }

    private void initComponents() {
        //Create JButton
        randomMapButton = new JButton("Random Map");
        randomMapButton.setFont(new Font("Verdana", Font.BOLD, 12));
        configurableMapButton = new JButton("Configurable Map");
        configurableMapButton.setFont(new Font("Verdana", Font.BOLD, 12));
        setMapButton = new JButton("Map");
        setMapButton.setFont(new Font("Verdana", Font.BOLD, 12));

        group = new GridLayout(3, 0, 20, 0);
        this.setLayout(group);
        add(randomMapButton);
        add(configurableMapButton);
        add(setMapButton);

        //set play, pause, stop and replay button icon and ActionListener
        randomMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RandomMapButtonActionPerformed(evt);
            }
        });
        configurableMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                configurableMapButtonActionPerformed(evt);
            }
        });
        setMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setMapButtonActionPerformed(evt);
            }
        });
    }

    private void configurableMapButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        System.exit(0);
    }

    private void RandomMapButtonActionPerformed(ActionEvent evt) {
        // new TrafficManagement().run(100);
        System.exit(0);
    }

    private void setMapButtonActionPerformed(ActionEvent evt) {
        new TrafficManagement().run();
//        System.exit(0);
        ((JFrame) this.getTopLevelAncestor()).dispose();
    }

}