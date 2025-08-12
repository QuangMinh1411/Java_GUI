/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package password;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author minhc
 */
public class Password extends JFrame{
    
    private final JLabel passwordLabel = new JLabel();
    private final JPasswordField inputPasswordField = new JPasswordField();
    private final JButton validButton = new JButton();
    private final JButton exitButton = new JButton();
    private int tryCt = 0;
    
    public Password(){
        setTitle("Password Validation");
        getContentPane().setBackground(Color.YELLOW);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new GridBagLayout());
        
        //position controls
        GridBagConstraints gridConstraints;
        passwordLabel.setText("Please enter your password:");
        passwordLabel.setOpaque(true);
        passwordLabel.setBackground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial",Font.BOLD,14));
        passwordLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gridConstraints= new GridBagConstraints();
        gridConstraints.ipadx = 30;
        gridConstraints.ipady = 20;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(5,20,5,20);
        getContentPane().add(passwordLabel,gridConstraints);
        
        inputPasswordField.setText("");
        inputPasswordField.setFont(new Font("Arial",Font.PLAIN,14));
        inputPasswordField.setColumns(15);
        gridConstraints= new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        getContentPane().add(inputPasswordField,gridConstraints);
        inputPasswordField.addActionListener(e->{
        
            inputPasswordFieldActionPerformed(e);
        });
        
        
        validButton.setText("Validate");
        gridConstraints= new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        getContentPane().add(validButton,gridConstraints);
        validButton.addActionListener(e->{
        
            validButtonActionPerformed(e);
        
        
        });
        
        exitButton.setText("Exit");
        gridConstraints= new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        getContentPane().add(exitButton,gridConstraints);
        exitButton.addActionListener(e->System.exit(0));
        
        
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -getWidth())), (int) (0.5 * (screenSize.height -
                    getHeight())), getWidth(), getHeight());
        
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            Password window = new Password();
            window.setVisible(true);
        });

        
    }

    private void inputPasswordFieldActionPerformed(ActionEvent e) {
           validButton.doClick();
    }
    
    

    private void validButtonActionPerformed(ActionEvent e) {
        final String THEPASSWORD = "WELCOME";
        final int tryMax = 3;
        int response;
        if(String.valueOf(inputPasswordField.getPassword()).equals(THEPASSWORD)){
            JOptionPane.showConfirmDialog(null,"You've passed security","Access Granted"
            ,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE);
            tryCt=0;
            
        } else{
            tryCt++;
            if(tryCt>tryMax){
                 JOptionPane.showConfirmDialog(null,"You try too many","Logging out",
                            JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
                 exitButton.doClick();
            }
            response = JOptionPane.showConfirmDialog(null,"Incorect password-Try again ?"
            ,"Access denied",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
            if(response == JOptionPane.YES_OPTION){
                inputPasswordField.setText("");
                inputPasswordField.requestFocus();
                
            }else{
                exitButton.doClick();
            }
        } 

    }
    
    
    
}
