/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stopwatch;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author minhc
 */
public class StopWatch extends JFrame{

    private JButton startButton = new JButton();
    private JButton stopButton = new JButton();
    private JButton exitButton = new JButton();
    private JLabel startLabel = new JLabel();
    private JLabel stopLabel = new JLabel();
    private JLabel elapsedLabel = new JLabel();
    private Timer timer;
    JTextField startTextField = new JTextField();
    JTextField stopTextField = new JTextField();
    JTextField elapsedTextField = new JTextField();
    private SimpleDateFormat timeFormat;
    long startTime;
    long stopTime;
    double elapsedTime;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new StopWatch().show();
    }
    
    public StopWatch(){
        setTitle("StopWatch Application");
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                exitForm(e);
            }
        });
        
        
        
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        getContentPane().setBackground(Color.GRAY);
        
        startButton.setText("Start Timing");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(startButton,gridConstraints);
        startButton.addActionListener((ActionEvent e) -> {
            startButtonActionPerformed(e); 
        });
        
        stopButton.setText("Stop Timing");
        stopButton.setEnabled(false);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        getContentPane().add(stopButton,gridConstraints);
        stopButton.addActionListener((ActionEvent e) -> {
            stopButtonActionPerformed(e);
        });
        
        exitButton.setText("Exit");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        getContentPane().add(exitButton,gridConstraints);
        exitButton.addActionListener((ActionEvent e) -> {
            exitButtonActionPerformed(e);
        });
        
        startLabel.setText("Start Time");
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        getContentPane().add(startLabel,gridConstraints);
        
        stopLabel.setText("Stop Time");
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        getContentPane().add(stopLabel,gridConstraints);
        
        elapsedLabel.setText("Elapsed Time");
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        getContentPane().add(elapsedLabel,gridConstraints);
        
        startTextField.setText("");
        startTextField.setColumns(15);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        getContentPane().add(startTextField,gridConstraints);
        
        stopTextField.setText("");
        stopTextField.setColumns(15);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 1;
        getContentPane().add(stopTextField,gridConstraints);
        
        elapsedTextField.setText("");
        elapsedTextField.setColumns(15);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 2;
        getContentPane().add(elapsedTextField,gridConstraints);
        pack();
    }
    private void startButtonActionPerformed(ActionEvent e){
        startButton.setEnabled(false);
        startTime = System.currentTimeMillis();
        Date now = new Date();
        startTextField.setText(timeFormat.format(now));
        stopButton.setEnabled(true);
        timer = new Timer(1000,ev->updateTime());
        timer.start();
        elapsedTextField.setText("");
    }
    
    private void stopButtonActionPerformed(ActionEvent e){
        stopButton.setEnabled(false);
        stopTime = System.currentTimeMillis();
        startButton.setEnabled(false);
        timer.stop();
        elapsedTime = (stopTime-startTime)/1000.0;
        elapsedTextField.setText(String.valueOf(elapsedTime));
        
    }
    
    private void updateTime() {
        Date now = new Date();
        stopTextField.setText(timeFormat.format(now));
    }
    
   
    private void exitButtonActionPerformed(ActionEvent e){
        System.exit(0);
    }
    private void exitForm(WindowEvent e){
        System.exit(0);
    }
}
