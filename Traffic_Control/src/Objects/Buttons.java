package Objects;

import javax.swing.*;
import java.awt.*;
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


    }


}
