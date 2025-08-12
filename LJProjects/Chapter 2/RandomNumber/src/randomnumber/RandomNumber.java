/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package randomnumber;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 *
 * @author minhc
 */
public class RandomNumber extends JFrame{
    private JLabel numberLb = new JLabel();
    private JButton btn = new JButton();
    /**
     * @param args the command line arguments
     */
    public RandomNumber(){
        setTitle("RandomNumber Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 100);
        btn.setText("Click to generate");
        numberLb.setText("Random number 1 to 100");
        btn.addActionListener(e->{
            int number = (int) Math.floor(Math.random()*100);
            numberLb.setText(String.valueOf(number));
        
        });
        
        numberLb.setHorizontalAlignment(JLabel.CENTER);
        btn.setHorizontalAlignment(JButton.CENTER);

        var panel = new JPanel(new GridLayout(1, 2));
        panel.add(numberLb);
        panel.add(btn);
         add(panel);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            RandomNumber window = new RandomNumber();
            window.setVisible(true);
        });
    }
    
}
