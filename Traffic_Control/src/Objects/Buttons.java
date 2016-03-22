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

        //setLayout(new FlowLayout());
        playButton.setBounds(200,200,50,40);
        replayButton.setBounds(300,200,50,40);
        pauseButton.setBounds(400,200,50,40);
        stopButton.setBounds(500,200,50,40);

        add(playButton);
        add(pauseButton);
        add(replayButton);
        add(stopButton, BorderLayout.SOUTH);

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
        // TODO add your handling code here:
    }
}
