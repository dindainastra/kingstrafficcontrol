package Objects;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by SaiKu on 11/03/2016.
 */
public class Buttons extends JPanel {



    private JButton pauseButton, playButton, replayButton, stopButton;
    private JLabel teamName;
    public Buttons () {
        initComponents();
    }

    private void initComponents() {
        //this.setLayout();

        //Create JButton
        replayButton = new JButton();
        pauseButton = new JButton();
        playButton = new JButton();
        stopButton = new JButton();
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
        add(playButton);
        add(pauseButton);
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
        // TODO add your handling code here:
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
