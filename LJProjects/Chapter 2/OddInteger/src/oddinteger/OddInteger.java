/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oddinteger;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author minhc
 */
public class OddInteger extends JFrame{
    
    private JLabel sumLb = new JLabel();
    private JLabel countLb = new JLabel();
    private JTextField targetTxt = new JTextField();

    /**
     * @param args the command line arguments
     */
    
    public OddInteger(){
        setTitle("RandomNumber Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        getContentPane().setBackground(Color.GRAY);
        
        sumLb.setText("Cal...");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(sumLb,gridConstraints);
        
        countLb.setText("Cal...");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        getContentPane().add(countLb,gridConstraints);
        
        targetTxt.setText("");
        targetTxt.setColumns(10);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        getContentPane().add(targetTxt,gridConstraints);
        targetTxt.addActionListener(e->{
            int sum = 0;
            int ct = 0;
            int target = Integer.parseInt(targetTxt.getText());
            for(int i=1;sum<target;i=i+2){
                sum+=i;
                ct++;
            }
            sumLb.setText(String.valueOf(sum));
            countLb.setText(String.valueOf(ct));
        
        });
        
        pack();
        
        
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            OddInteger window = new OddInteger();
            window.setVisible(true);
        });
    }
    
}
