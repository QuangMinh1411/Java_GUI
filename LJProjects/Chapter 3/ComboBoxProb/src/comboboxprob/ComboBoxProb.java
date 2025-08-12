/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package comboboxprob;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author minhc
 */
public class ComboBoxProb extends JFrame{
    
    private JLabel addLabel = new JLabel();
    private JLabel fruitsList = new JLabel();
    private JTextField fruitName = new JTextField();
    private JComboBox fruitsBox = new JComboBox();
    private JButton addBtn = new JButton();
    private String[] fruits = {"Lemon","Melon","Coconut",
                                "Apple","Orange","Pineapple"};
    
    public ComboBoxProb(){
        //Frame
        setTitle("Fruits you buy");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                    getWidth())), (int) (0.5 * (screenSize.height -
                    getHeight())), getWidth(), getHeight());
        GridBagConstraints gridConstraints;
        
        //Fruit combo
        fruitsList.setText("Fruits list");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,0,0,0);
        getContentPane().add(fruitsList,gridConstraints);
        fruitsBox.setEditable(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10,10,0,10);
        gridConstraints.anchor = GridBagConstraints.NORTH;
        fruitsBox.setPreferredSize(new Dimension(100,25));
        getContentPane().add(fruitsBox,gridConstraints);
        for(String fruit:fruits){
             fruitsBox.addItem(fruit);
        }
        
        //Fruit to add
        addLabel.setText("Enter your fruit: ");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,0,0,0);
        getContentPane().add(addLabel,gridConstraints);
        fruitName.setText("");
        fruitName.setColumns(10);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,0,0,0);
        getContentPane().add(fruitName,gridConstraints);
        addBtn.setText("Add");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10,0,0,0);
        gridConstraints.anchor = GridBagConstraints.EAST;
        gridConstraints.gridwidth = 2;
        getContentPane().add(addBtn,gridConstraints);
        
        
        addBtn.addActionListener(e->{
            addFruit(fruitName.getText(),fruits);
        
        });
        
        
        pack();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         SwingUtilities.invokeLater(() -> {
            ComboBoxProb window = new ComboBoxProb();
            window.setVisible(true);
        });
    }

    private void addFruit(String text,String[] list) {
        boolean isExist = false;
        for (String list1 : list) {
            if (text.equalsIgnoreCase(list1)) {
                isExist = true;
                break;
            }
        }
        if(isExist || text.isEmpty())
            JOptionPane.showConfirmDialog(null, text+" is existed","Your Fruits",
                JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
        else{
            fruitsBox.addItem(text);
            fruitsBox.setSelectedItem(text);
        }
            
            
    }
    
}
