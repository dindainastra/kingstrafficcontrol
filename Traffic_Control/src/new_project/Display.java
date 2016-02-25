/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package new_project;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author daniella
 */
public class Display {

    //Display GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new Draw());
                //frame.add(new transform());
               //frame.add(new New_project());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//
                frame.pack();//
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setSize(700, 700);
            }
        });
    }
}
