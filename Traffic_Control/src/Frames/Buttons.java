package Frames;


import Controllers.TrafficManagement;
import Controllers.TrafficSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Buttons extends JPanel {


    private JButton pauseButton, playButton, replayButton, stopButton;
    private JLabel teamName;
    private TrafficSimulator traffic;
    private int selectMap2;
    private TrafficManagement trafficManagement = new TrafficManagement();

    public Buttons(int selectMap) {
        initComponents();
        selectMap2 = selectMap;


    }

    private void initComponents() {
        //this.setLayout();

        //Create JButton
        replayButton = new JButton("Replay");
        pauseButton = new JButton("Pause");
        playButton = new JButton("Play");
        stopButton = new JButton("Stop");
        teamName = new JLabel();
        //Create JLabel
        URL stopURL = getClass().getResource("../Resources/stop.PNG");
        URL rewindURL = getClass().getResource("../Resources/rewind.PNG");
        URL playURL = getClass().getResource("../Resources/play.PNG");
        URL pauseURL = getClass().getResource("../Resources/pause.PNG");

        //set play, pause, stop and replay button icon and ActionListener
        replayButton.setIcon(new ImageIcon(rewindURL));
        playButton.setIcon(new ImageIcon(playURL));
        pauseButton.setIcon(new ImageIcon(pauseURL));
        stopButton.setIcon(new ImageIcon(stopURL));

        teamName.setFont(new Font("WINGS", 0, 16)); // NOI18N
        teamName.setText("King's Traffic Control");

        //add(teamName);
//        add(playButton);
//        add(pauseButton);
        add(replayButton);
        add(stopButton);

        //set play, pause, stop and replay button icon and ActionListener
        replayButton.setIcon(new ImageIcon(rewindURL)); // NOI18N
        replayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                replayButtonActionPerformed(evt);
            }
        });
        playButton.setIcon(new ImageIcon(playURL)); // NOI18N
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        pauseButton.setIcon(new ImageIcon(pauseURL)); // NOI18N
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        stopButton.setIcon(new ImageIcon(stopURL)); // NOI18N
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
    }

    private void replayButtonActionPerformed(ActionEvent evt) {
      TrafficSimulator trafficSimulator = new TrafficSimulator();
        Startup startup = new Startup();
        trafficSimulator.add(startup);
        trafficSimulator.setVisible(true);
        setSize(500,500);
        ((JFrame) this.getTopLevelAncestor()).dispose();
    }

    private void playButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void pauseButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void stopButtonActionPerformed(ActionEvent evt) {
        System.exit(0);
    }
}
