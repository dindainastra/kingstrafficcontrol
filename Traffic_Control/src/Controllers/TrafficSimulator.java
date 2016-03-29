package Controllers;

import Objects.Startup;

import javax.swing.*;
import java.awt.*;

public class TrafficSimulator extends JFrame {
    private static JFrame frame;

    public static void main(String[] args) {
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        frame = new JFrame("King's Traffic Control Simulation");
        frame.setLayout(new BorderLayout());
        Startup buttons = new Startup();
        buttons.setPreferredSize(new Dimension(50, 50));
        panel1.setPreferredSize(new Dimension(150, 100));
        panel2.setPreferredSize(new Dimension(150, 100));
        panel3.setPreferredSize(new Dimension(100, 150));
        panel4.setPreferredSize(new Dimension(100, 150));
        panel1.setBackground(new Color(230, 230, 250));
        panel2.setBackground(new Color(230, 230, 250));
        panel3.setBackground(new Color(230, 230, 250));
        panel4.setBackground(new Color(230, 230, 250));
        buttons.setBackground(new Color(230, 230, 250));
        frame.add(buttons, BorderLayout.CENTER);
        frame.add(panel1, BorderLayout.EAST);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.NORTH);
        frame.add(panel4, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

    }


}