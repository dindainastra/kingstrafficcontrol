
package Objects;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Display {

    //Display GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new Draw());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setSize(700, 700);
            }
        });
    }
}

