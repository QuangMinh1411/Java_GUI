/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package savings;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author minhc
 */
public class Savings extends JFrame{

    /**
     * @param args the command line arguments
     */
    private JLabel depositLabel = new JLabel();
    private JLabel interestLabel = new JLabel();
    private JLabel monthsLabel = new JLabel();
    private JLabel finalLabel = new JLabel();
    private JButton calculateButton = new JButton();
    private JButton exitButton = new JButton();
    private JButton clearButton = new JButton();
    private JTextField depositTextField = new JTextField();
    private JTextField interestTextField = new JTextField();
    private JTextField monthsTextField = new JTextField() ;
    private JTextField finalTextField = new JTextField();
    
    public Savings(){
        setTitle("Savings Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        getContentPane().setBackground(Color.GRAY);
        
        depositLabel.setText("Monthly Deposit");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(depositLabel,gridConstraints);
        
        interestLabel.setText("Yearly Interest");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        getContentPane().add(interestLabel,gridConstraints);
        
        monthsLabel.setText("Number of Months");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        getContentPane().add(monthsLabel,gridConstraints);
        
        finalLabel.setText("Final Balance");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        getContentPane().add(finalLabel,gridConstraints);
        
        depositTextField.setText("");
        depositTextField.setColumns(10);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        getContentPane().add(depositTextField,gridConstraints);
        depositTextField.addActionListener(e->depositTextField.transferFocus());
        
        interestTextField.setText("");
        interestTextField.setColumns(10);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 1;
        getContentPane().add(interestTextField,gridConstraints);
        interestTextField.addActionListener(e->interestTextField.transferFocus());

        
        monthsTextField.setText("");
        monthsTextField.setColumns(10);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 2;
        getContentPane().add(monthsTextField,gridConstraints);
        monthsTextField.addActionListener(e->monthsTextField.transferFocus());

        
        finalTextField.setText("");
        finalTextField.setFocusable(true);
        finalTextField.setColumns(10);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 3;
        getContentPane().add(finalTextField,gridConstraints);
        finalTextField.addActionListener(e->finalTextField.transferFocus());
        
        calculateButton.setText("Calculate");
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 4;
        getContentPane().add(calculateButton,gridConstraints);
        calculateButton.addActionListener(e->calculateButtonActionPerformed());
        
        exitButton.setText("Exit");
        exitButton.setFocusable(false);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 5;
        getContentPane().add(exitButton,gridConstraints);
        exitButton.addActionListener(e->System.exit(0));
        
        
        clearButton.setText("Clear");
        clearButton.setFocusable(false);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 4;
        getContentPane().add(clearButton,gridConstraints);
        clearButton.addActionListener(e->{
            depositTextField.setText("");
            interestTextField.setText("");
            monthsTextField.setText("");
            finalTextField.setText("");
            depositTextField.requestFocus();
            
        });
        
        pack();
    }
    
    
    private void calculateButtonActionPerformed(){
        double deposit;
        double interest;
        double months;
        double finalBalance;
        double monthlyInterest;
        double finalCompute,intChange;
        int intDirection;
        
        if(!validateDecimalNumber(monthsTextField)||
                !validateDecimalNumber(interestTextField)||
                !validateDecimalNumber(depositTextField)){
            return;
        
        
        }
            deposit = Double.parseDouble(depositTextField.getText());
            interest = Double.parseDouble(interestTextField.getText());
            months = Double.parseDouble(monthsTextField.getText());
            monthlyInterest = interest/1200;
            if(interest==0)
                finalBalance = deposit * months;
            else {
                        finalBalance = deposit* (Math.pow((1+monthlyInterest), months)-1)/monthlyInterest;

            }
                    finalTextField.setText(new DecimalFormat("0.00").format(finalBalance));

        
    
    }   
    
    public boolean validateDecimalNumber(JTextField tf){
        String s = tf.getText().trim();
        boolean hasDecimal = false;
        boolean valid = true;
        if(s.length()==0){
            valid = false;
        }else {
            for(int i=0;i<s.length();i++){
                char c = s.charAt(i);
                if(c>='0' && c<='9'){
                    continue;
                }
                else if (c=='.' && !hasDecimal){
                    hasDecimal = true;
                }else {
                    valid = false;
                }
            }
        }
        if(valid){
            tf.setText(s);
        }else {
            tf.setText("");
            tf.requestFocus();
        }
        return valid;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            Savings save = new Savings();
            save.setVisible(true);
        });
    }
    
}
