
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package new_project;

//Imports for graphics
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
//Imports for music
import java.io.*;
import java.net.URL;
//Imports
import java.util.logging.Level;
import static java.util.logging.Logger.getLogger;
import sun.audio.*;

/**
 *
 * @author daniella
 */
public class DisplayGUI {
    
    // Variables declaration                     
    private JCheckBox bridgeCheckBox, tollGateCheckBox, crossingCheckBox,dCarriagewayCheckBox, junctionCheckBox,rampsCheckBox,roundaboutsCheckBox,sCarriagewayCheckBox ;
    private JLabel congestionLabel, congestionValue,emergencyLabel,roadNetworkLabel,time,timeIntervalLabel,weatherLabel,timeIntervalValue;
    private JButton pauseButton, playButton, replayButton, stopButton;
    private JSlider congestionSlider,timeIntervalSlider;
    private JComboBox<String> weatherComboBox;
    private JLabel backgroundImage;
    private JPanel jPanel;
    // End of variables declaration    
    
    JFrame frame = new JFrame();
    
    /**
     * Creates new form DisplayGUI
     */
    public DisplayGUI() {
        frame.add(new Draw());
        initComponents();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(700, 700);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     **/
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        //create button, label, checkbox and slider interfaces
        replayButton = new JButton();
        pauseButton = new JButton();
        playButton = new JButton();
        stopButton = new JButton();
        time = new JLabel();
        timeIntervalValue = new JLabel();
        congestionValue = new JLabel();
        backgroundImage = new JLabel();
        jPanel = new JPanel();
        timeIntervalSlider = new JSlider();
        rampsCheckBox = new JCheckBox();
        roundaboutsCheckBox = new JCheckBox();
        crossingCheckBox = new JCheckBox();
        junctionCheckBox = new JCheckBox();
        dCarriagewayCheckBox = new JCheckBox();
        sCarriagewayCheckBox = new JCheckBox();
        timeIntervalLabel = new JLabel();
        congestionLabel = new JLabel();
        bridgeCheckBox = new JCheckBox();
        tollGateCheckBox = new JCheckBox();
        emergencyLabel = new JLabel();
        roadNetworkLabel = new JLabel();
        weatherComboBox = new JComboBox<>();
        weatherLabel = new JLabel();
        congestionSlider = new JSlider();
        

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        URL stopURL = getClass().getResource("../Resources/stop.PNG");
        URL rewindURL = getClass().getResource("../Resources/rewind.PNG");
        URL playURL = getClass().getResource("../Resources/play.PNG");
        URL pauseURL = getClass().getResource("../Resources/pause.PNG");
        URL captureURL = getClass().getResource("../Resources/capture.PNG");

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
        
       // sec = Display.main();
        //create temp time text for later use (real time)
        time.setText("sec");
        
        //temporary road map
        backgroundImage.setIcon(new ImageIcon(captureURL)); // NOI18N
        
        //slider interface for time interval and congestion rate
        timeIntervalSlider.setMajorTickSpacing(20);
        timeIntervalSlider.setMinorTickSpacing(10);
        timeIntervalSlider.setPaintLabels(true);
        timeIntervalSlider.setPaintTicks(true);
        timeIntervalSlider.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                timeIntervalSliderMousePressed(evt);
            }
        });
        congestionSlider.setMajorTickSpacing(20);
        congestionSlider.setMinorTickSpacing(10);
        congestionSlider.setPaintLabels(true);
        congestionSlider.setPaintTicks(true);
        congestionSlider.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                congestionSliderMousePressed(evt);
            }
        });
        
        //checkbox interfaces for different road maps
        rampsCheckBox.setText("Ramps");
        rampsCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rampsCheckBoxActionPerformed(evt);
            }
        });
        roundaboutsCheckBox.setText("Roundabouts");
        roundaboutsCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                roundaboutsCheckBoxActionPerformed(evt);
            }
        });
        crossingCheckBox.setText("Crossings");
        crossingCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                crossingCheckBoxActionPerformed(evt);
            }
        });
        junctionCheckBox.setText("Junctions");
        junctionCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                junctionCheckBoxActionPerformed(evt);
            }
        });
        dCarriagewayCheckBox.setText("Dual carriageways");
        dCarriagewayCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dCarriagewayCheckBoxActionPerformed(evt);
            }
        });
        sCarriagewayCheckBox.setText("Single carriageways");
        sCarriagewayCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sCarriagewayCheckBoxActionPerformed(evt);
            }
        });
        bridgeCheckBox.setText("Bridge");
        bridgeCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bridgeCheckBoxActionPerformed(evt);
            }
        });
        tollGateCheckBox.setText("Tolls gates");
        tollGateCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tollGateCheckBoxActionPerformed(evt);
            }
        });
        bridgeCheckBox.setText("Bridge");
        bridgeCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bridgeCheckBoxActionPerformed(evt);
            }
        });
        tollGateCheckBox.setText("Tolls gates");
        tollGateCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tollGateCheckBoxActionPerformed(evt);
            }
        });
        weatherComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Normal", "Hazardous", "Night", "Day" }));
        weatherComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                weatherComboBoxActionPerformed(evt);
            }
        });
        
        //set labels for interfaces
        emergencyLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        emergencyLabel.setText("Emergency Services");
        roadNetworkLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        roadNetworkLabel.setText("Road Network");
        timeIntervalLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        timeIntervalLabel.setText("Time Interval");
        congestionLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        congestionLabel.setText("Congestion Rate");
        weatherLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        weatherLabel.setText("Weather Option:");

        //set layout of the interfaces
        GroupLayout jPanelLayout = new GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(timeIntervalLabel)
                    .addComponent(congestionLabel)
                    .addComponent(timeIntervalSlider, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
                    .addComponent(emergencyLabel)
                    .addComponent(roadNetworkLabel)
                    .addComponent(roundaboutsCheckBox)
                    .addComponent(crossingCheckBox)
                    .addComponent(sCarriagewayCheckBox)
                    .addComponent(dCarriagewayCheckBox)
                    .addComponent(junctionCheckBox)
                    .addComponent(bridgeCheckBox)
                    .addComponent(tollGateCheckBox)
                    .addComponent(rampsCheckBox)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(weatherLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(weatherComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(congestionSlider, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(timeIntervalLabel)
                .addGap(5, 5, 5)
                .addComponent(timeIntervalSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(congestionLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(congestionSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(emergencyLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(weatherLabel)
                    .addComponent(weatherComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(roadNetworkLabel)
                .addGap(8, 8, 8)
                .addComponent(roundaboutsCheckBox)
                .addGap(0, 0, 0)
                .addComponent(crossingCheckBox)
                .addGap(0, 0, 0)
                .addComponent(sCarriagewayCheckBox)
                .addGap(0, 0, 0)
                .addComponent(dCarriagewayCheckBox)
                .addGap(0, 0, 0)
                .addComponent(junctionCheckBox)
                .addGap(0, 0, 0)
                .addComponent(bridgeCheckBox)
                .addGap(0, 0, 0)
                .addComponent(tollGateCheckBox)
                .addGap(0, 0, 0)
                .addComponent(rampsCheckBox)
                .addGap(36, 36, 36))
        );
        
        
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(time)
                .addGap(8, 8, 8)
                .addComponent(replayButton)
                .addGap(6, 6, 6)
                .addComponent(pauseButton)
                .addGap(6, 6, 6)
                .addComponent(playButton)
                .addGap(6, 6, 6)
                .addComponent(stopButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(congestionValue)
                    .addComponent(timeIntervalValue))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
   //             .addComponent(backgroundImage)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(congestionValue)
                    .addComponent(timeIntervalValue)
 //                   .addComponent(backgroundImage)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(time))
                    .addComponent(replayButton)
                    .addComponent(pauseButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addComponent(playButton)
                    .addComponent(stopButton)))
        );
        frame.pack();
    }// </editor-fold>                        

    private void replayButtonActionPerformed(ActionEvent evt) {                                             
      InputStream music;
      //replay music when replay JButton is pressed
      try{
        music = new FileInputStream(new File ("beep09.wav"));
        AudioStream  audios = new AudioStream(music);
        if (evt.getSource() == replayButton)
          {
              AudioPlayer.player.stop(audios);
              AudioPlayer.player.start(audios);
          }
      }
      catch(Exception e){
      }
    }   
    /**
   public void paint(Graphics car) {
          int pos_x = 75, pos_y = 10;
           int carW = 20, carH = 20;
            car.fillRect (pos_x, pos_y, carW,carH );
     }**/
    
    private void playButtonActionPerformed(ActionEvent evt) {                                           
      InputStream music;
      
      //start music when play JButton is pressed
      try{
        music = new FileInputStream(new File ("beep09.wav"));
        AudioStream  audios = new AudioStream(music);
        if (evt.getSource() == playButton){
              AudioPlayer.player.start(audios);
          }
      }
      catch(Exception e){
      }
    }                                                                                    

    private void pauseButtonActionPerformed(ActionEvent evt) {                                            
      InputStream music;
      try{
      music = new FileInputStream(new File ("beep09.wav"));
      AudioStream  audios = new AudioStream(music);
      if (evt.getSource() == pauseButton)
        AudioPlayer.player.stop(audios);
      }
      catch(Exception e){
      }
    }                                           

    private void stopButtonActionPerformed(ActionEvent evt) {                                           
      InputStream music;
      try{
      music = new FileInputStream(new File ("beep09.wav"));
      AudioStream  audios = new AudioStream(music);
      if (evt.getSource() == stopButton)
        AudioPlayer.player.stop(audios);
      }
      catch(Exception e){
      }
    }                                          

    private void weatherComboBoxActionPerformed(ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void roundaboutsCheckBoxActionPerformed(ActionEvent evt) {                                                    
        // TODO add your handling code here:
    }                                                   

    private void crossingCheckBoxActionPerformed(ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void sCarriagewayCheckBoxActionPerformed(ActionEvent evt) {                                                     
        // TODO add your handling code here:
    }                                                    

    private void dCarriagewayCheckBoxActionPerformed(ActionEvent evt) {                                                     
        // TODO add your handling code here:
    }                                                    

    private void junctionCheckBoxActionPerformed(ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void rampsCheckBoxActionPerformed(ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             
    
    private void bridgeCheckBoxActionPerformed(ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void tollGateCheckBoxActionPerformed(ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }    
    
    private void timeIntervalSliderMousePressed(MouseEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void congestionSliderMousePressed(MouseEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            getLogger(DisplayGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            getLogger(DisplayGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            getLogger(DisplayGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            getLogger(DisplayGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DisplayGUI();
            }
        });
    }               
}