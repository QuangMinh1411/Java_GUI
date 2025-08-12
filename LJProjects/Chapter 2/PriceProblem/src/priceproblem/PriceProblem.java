/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package priceproblem;

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
public class PriceProblem extends JFrame{
    private JLabel msg = new JLabel();
    private JTextField temp = new JTextField();
    
    
    public PriceProblem(){
        setTitle("RandomNumber Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 100);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        getContentPane().setBackground(Color.GRAY);
        
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        temp.setColumns(10);
        temp.addActionListener(e->temperatureChange(temp));
        getContentPane().add(temp,gridConstraints);
        
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        msg.setText("Waiting temp ...");
        getContentPane().add(msg,gridConstraints);
        
        pack();
    }
    
    private void temperatureChange(JTextField text){
        double t = Double.parseDouble(text.getText());
        int price = 0;
        if(t>100){
            price = 75;
        }else if(t>95)
            price = 65;
        else if (t>90)
            price = 55;
        else if(t>85)
            price = 50;
        else if(t>80)
            price = 40;
        else if (t>70)
            price = 39;
        else if(t>60)
            price = 25;
        else if(t>=50)
            price = 20;
        else price = 0;
        if(price==0)
            msg.setText("Don't bother");
        else
            msg.setText(price + " Cents");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            PriceProblem window = new PriceProblem();
            window.setVisible(true);
        });
    }
    
}
