
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

//vehicles
import java.awt.Graphics;
//music
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.*;

/**
 *
 * @author daniella
 */
public class RoadNetworks extends javax.swing.JFrame {

    /**
     * Creates new form RoadNetworks
     */
     
    public RoadNetworks() {
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     **/
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        replayButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        time = new javax.swing.JLabel();
        timeIntervalValue = new javax.swing.JLabel();
        congestionValue = new javax.swing.JLabel();
        backgroundImage = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        timeIntervalSlider = new javax.swing.JSlider();
        rampsCheckBox = new javax.swing.JCheckBox();
        roundaboutsCheckBox = new javax.swing.JCheckBox();
        crossingCheckBox = new javax.swing.JCheckBox();
        junctionCheckBox = new javax.swing.JCheckBox();
        dCarriagewayCheckBox = new javax.swing.JCheckBox();
        sCarriagewayCheckBox = new javax.swing.JCheckBox();
        timeIntervalLabel = new javax.swing.JLabel();
        congestionLabel = new javax.swing.JLabel();
        bridgeCheckBox = new javax.swing.JCheckBox();
        tollGateCheckBox = new javax.swing.JCheckBox();
        emergencyLabel = new javax.swing.JLabel();
        roadNetworkLabel = new javax.swing.JLabel();
        weatherComboBox = new javax.swing.JComboBox<>();
        weatherLabel = new javax.swing.JLabel();
        congestionSlider = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        replayButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\daniella\\Documents\\Mine\\Term 2\\Group Project\\rewind.PNG")); // NOI18N
        replayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                replayButtonActionPerformed(evt);
            }
        });

        pauseButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\daniella\\Documents\\Mine\\Term 2\\Group Project\\pause.PNG")); // NOI18N
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        playButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\daniella\\Documents\\Mine\\Term 2\\Group Project\\play.PNG")); // NOI18N
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        stopButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\daniella\\Documents\\Mine\\Term 2\\Group Project\\stop.PNG")); // NOI18N
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        time.setText("time");

        backgroundImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\daniella\\Documents\\Mine\\Term 2\\Group Project\\Capture.PNG")); // NOI18N

        timeIntervalSlider.setMajorTickSpacing(20);
        timeIntervalSlider.setMinorTickSpacing(10);
        timeIntervalSlider.setPaintLabels(true);
        timeIntervalSlider.setPaintTicks(true);

        rampsCheckBox.setText("Ramps");

        roundaboutsCheckBox.setText("Roundabouts");

        crossingCheckBox.setText("Crossings");

        junctionCheckBox.setText("Junctions");

        dCarriagewayCheckBox.setText("Dual carriageways");

        sCarriagewayCheckBox.setText("Single carriageways");

        timeIntervalLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        timeIntervalLabel.setText("Time Interval");

        congestionLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        congestionLabel.setText("Congestion Rate");

        bridgeCheckBox.setText("Bridge");
        bridgeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bridgeCheckBoxActionPerformed(evt);
            }
        });

        tollGateCheckBox.setText("Tolls gates");
        tollGateCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tollGateCheckBoxActionPerformed(evt);
            }
        });

        emergencyLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        emergencyLabel.setText("Emergency Services");

        roadNetworkLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        roadNetworkLabel.setText("Road Network");

        weatherComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Hazardous", "Night", "Day" }));
        weatherComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weatherComboBoxActionPerformed(evt);
            }
        });

        weatherLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        weatherLabel.setText("Weather Option:");

        congestionSlider.setMajorTickSpacing(20);
        congestionSlider.setMinorTickSpacing(10);
        congestionSlider.setPaintLabels(true);
        congestionSlider.setPaintTicks(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeIntervalLabel)
                    .addComponent(congestionLabel)
                    .addComponent(timeIntervalSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(weatherLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(weatherComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(congestionSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(timeIntervalLabel)
                .addGap(5, 5, 5)
                .addComponent(timeIntervalSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(congestionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(congestionSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(emergencyLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(weatherLabel)
                    .addComponent(weatherComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(congestionValue)
                    .addComponent(timeIntervalValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backgroundImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(congestionValue)
                    .addComponent(timeIntervalValue)
                    .addComponent(backgroundImage)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(time))
                    .addComponent(replayButton)
                    .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playButton)
                    .addComponent(stopButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
      InputStream in;
      try{
      in = new FileInputStream(new File ("C:\\Users\\daniella\\Documents\\NetBeansProjects\\kingstrafficcontrol\\Traffic_Control\\src\\Objects\\beep09.wav"));
      AudioStream  audios = new AudioStream(in);
      if (evt.getSource() == playButton)
        AudioPlayer.player.start(audios);
      }
      catch(Exception e){
      }
    }//GEN-LAST:event_playButtonActionPerformed

    private void bridgeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bridgeCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bridgeCheckBoxActionPerformed

    private void tollGateCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tollGateCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tollGateCheckBoxActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
      InputStream in;
      try{
      in = new FileInputStream(new File ("C:\\Users\\daniella\\Documents\\NetBeansProjects\\kingstrafficcontrol\\Traffic_Control\\src\\Objects\\beep09.wav"));
      AudioStream  audios = new AudioStream(in);
      if (evt.getSource() == pauseButton)
        AudioPlayer.player.stop(audios);
      }
      catch(Exception e){
      }
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void replayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_replayButtonActionPerformed
        InputStream in;
      try{
      in = new FileInputStream(new File ("C:\\Users\\daniella\\Documents\\NetBeansProjects\\kingstrafficcontrol\\Traffic_Control\\src\\Objects\\beep09.wav"));
      AudioStream  audios = new AudioStream(in);
      if (evt.getSource() == replayButton)
        {
            AudioPlayer.player.stop(audios);
            AudioPlayer.player.start(audios);
        }
      }
      catch(Exception e){
      }
    }//GEN-LAST:event_replayButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
      InputStream in;
      try{
      in = new FileInputStream(new File ("C:\\Users\\daniella\\Documents\\NetBeansProjects\\kingstrafficcontrol\\Traffic_Control\\src\\Objects\\beep09.wav"));
      AudioStream  audios = new AudioStream(in);
      if (evt.getSource() == stopButton)
        AudioPlayer.player.stop(audios);
      }
      catch(Exception e){
      }
    }//GEN-LAST:event_stopButtonActionPerformed

    private void weatherComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weatherComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weatherComboBoxActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RoadNetworks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoadNetworks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoadNetworks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoadNetworks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoadNetworks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundImage;
    private javax.swing.JCheckBox bridgeCheckBox;
    private javax.swing.JLabel congestionLabel;
    private javax.swing.JSlider congestionSlider;
    private javax.swing.JLabel congestionValue;
    private javax.swing.JCheckBox crossingCheckBox;
    private javax.swing.JCheckBox dCarriagewayCheckBox;
    private javax.swing.JLabel emergencyLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox junctionCheckBox;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton playButton;
    private javax.swing.JCheckBox rampsCheckBox;
    private javax.swing.JButton replayButton;
    private javax.swing.JLabel roadNetworkLabel;
    private javax.swing.JCheckBox roundaboutsCheckBox;
    private javax.swing.JCheckBox sCarriagewayCheckBox;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel time;
    private javax.swing.JLabel timeIntervalLabel;
    private javax.swing.JSlider timeIntervalSlider;
    private javax.swing.JLabel timeIntervalValue;
    private javax.swing.JCheckBox tollGateCheckBox;
    private javax.swing.JComboBox<String> weatherComboBox;
    private javax.swing.JLabel weatherLabel;
    // End of variables declaration//GEN-END:variables

}