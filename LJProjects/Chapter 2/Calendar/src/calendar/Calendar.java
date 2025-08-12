/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calendar;

import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author minhc
 */
public class Calendar extends JFrame{
    private JLabel dateLabel;
    private JLabel timeLabel;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    /**
     * @param args the command line arguments
     */
    public Calendar(){
        setTitle("Calendar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 100);

        dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        timeFormat = new SimpleDateFormat("HH:mm:ss");

        dateLabel = new JLabel();
        timeLabel = new JLabel();

        dateLabel.setFont(new Font("Serif", Font.BOLD, 24));
        timeLabel.setFont(new Font("Serif", Font.PLAIN, 18));

        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        var panel = new JPanel(new GridLayout(1, 2));
        panel.add(dateLabel);
        panel.add(timeLabel);

        add(panel);

        Timer timer = new Timer(1000,e->updateTime());
        updateTime(); // Set initial values
        timer.start();
    }
    private void updateTime() {
        Date now = new Date();
        dateLabel.setText(dateFormat.format(now));
        timeLabel.setText(timeFormat.format(now));
    }
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            Calendar window = new Calendar();
            window.setVisible(true);
        });
    }
    
}
