/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package codeproblem;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author minhc
 */
public class CodeProblem extends JFrame{
    private JTextField word = new JTextField();
    private JButton encodeBtn= new JButton();
    private JButton decodeBtn= new JButton();

    public CodeProblem(){
        setTitle("RandomNumber Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        word.setText("Enter a word");
        word.setColumns(10);
        getContentPane().add(word,gridConstraints);
        
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        encodeBtn.setText("Encode");
        encodeBtn.addActionListener(e->{
            String result = encode(word.getText());
            word.setText(result);
        });
        getContentPane().add(encodeBtn,gridConstraints);
        
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 2;
        decodeBtn.setText("Decode");
        decodeBtn.addActionListener(e->{
            String result = decode(word.getText());
            word.setText(result);
        });
        getContentPane().add(decodeBtn,gridConstraints);
        
        pack();
        
    }

    private String encode(String word){
        StringBuilder result = new StringBuilder();
        for (char c : word.toCharArray()) {
            result.append((char) (c - 1));
        }
        return result.toString();
    }
    
    private String decode(String word){
         StringBuilder result = new StringBuilder();
        for (char c : word.toCharArray()) {
            result.append((char) (c + 1));
        }
        return result.toString();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            CodeProblem window = new CodeProblem();
            window.setVisible(true);
        });
    }
    
}
