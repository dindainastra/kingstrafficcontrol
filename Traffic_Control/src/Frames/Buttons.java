package Frames;


import Controllers.TrafficManagement;
import Controllers.TrafficSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Buttons extends JPanel {


    private TrafficSimulator traffic;
    private TrafficManagement trafficManagement = new TrafficManagement();

    public Buttons(int selectMap) {
        initComponents();
        int selectMap2 = selectMap;


    }

    public void initComponents() {
        //this.setLayout();

        //Create JButton
        JButton replayButton = new JButton("Replay");
        JButton pauseButton = new JButton("Pause");
        JButton playButton = new JButton("Play");
        JButton stopButton = new JButton("Stop");
        JLabel teamName = new JLabel();
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

    public void replayButtonActionPerformed(ActionEvent evt) {
        TrafficSimulator trafficSimulator = new TrafficSimulator();
        Startup startup = new Startup();
        trafficSimulator.add(startup);
        trafficSimulator.setVisible(true);
        setSize(500, 500);
        ((JFrame) this.getTopLevelAncestor()).dispose();
    }

    public void playButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void pauseButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void stopButtonActionPerformed(ActionEvent evt) {
        System.exit(0);
    }
}
