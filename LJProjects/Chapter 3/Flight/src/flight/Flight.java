/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package flight;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author minhc
 */
public class Flight extends JFrame{

    /**
     * @param args the command line arguments
     */
    private JLabel citiesLabel = new JLabel();
    private JList citiesList = new JList();
    private JScrollPane citiesScrollPane = new JScrollPane();
    private JComboBox seatComboBox = new JComboBox();
    private JComboBox mealComboBox = new JComboBox();
    private String[] citiesName = {"San Diego","Los Angeles","Orange County","Ontario",
                                    "Bakersfield","Oakland","Sacramento","San Joe",
                                    "San Francisco","Eureka","Eugene","Portland"
                                    ,"Sponkane","Seattle"};
    private String[] seatItems = {"Aisle","Middle","Window"};
    private String[] meals = {"Chicken","Mystery Meat","Kosher","Vegetaria",
                                "Fruit Plate","No Preference"};
    
    public Flight(){
        
        //Frame
        setTitle("Flight Planner");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                    getWidth())), (int) (0.5 * (screenSize.height -
                    getHeight())), getWidth(), getHeight());
         GridBagConstraints gridConstraints;
         
         
         //Cities panel
         citiesLabel.setText("Destination City");
         gridConstraints = new GridBagConstraints();
         gridConstraints.gridx = 0;
         gridConstraints.gridy = 0;
         gridConstraints.insets = new Insets(10,0,0,0);
         getContentPane().add(citiesLabel,gridConstraints);
         citiesScrollPane.setPreferredSize(new Dimension(150,100));
         citiesScrollPane.setViewportView(citiesList);
         gridConstraints = new GridBagConstraints();
         gridConstraints.gridx = 0;
         gridConstraints.gridy = 1;
         gridConstraints.insets = new Insets(10,10,10,10);
         getContentPane().add(citiesScrollPane,gridConstraints);
         DefaultListModel citiesListModel = new DefaultListModel();
         for(String city:citiesName){
             citiesListModel.addElement(city);
         }
         citiesList.setModel(citiesListModel);
         citiesList.setSelectedIndex(0);
      
         //Seat Location Panel
         JLabel seatLabel = new JLabel();
         
         seatLabel.setText("Seat Location");
         gridConstraints = new GridBagConstraints();
         gridConstraints.gridx = 1;
         gridConstraints.gridy = 0;
         gridConstraints.insets = new Insets(10,0,0,0);
         getContentPane().add(seatLabel,gridConstraints);
         seatComboBox.setBackground(Color.white);
         gridConstraints = new GridBagConstraints();
         gridConstraints.gridx = 1;
         gridConstraints.gridy = 1;
         gridConstraints.insets = new Insets(10,10,0,10);
         gridConstraints.anchor = GridBagConstraints.NORTH;
         seatComboBox.setPreferredSize(new Dimension(100,25));
         getContentPane().add(seatComboBox,gridConstraints);
         for(String item:seatItems){
             seatComboBox.addItem(item);
         }
         seatComboBox.setSelectedIndex(0);
         
         //Meal Panel
         JLabel mealLabel = new JLabel();
         
         JButton assignButton = new JButton();
         JButton exitButton = new JButton();
         mealLabel.setText("Meal Preferences");
         gridConstraints = new GridBagConstraints();
         gridConstraints.gridx = 2;
         gridConstraints.gridy = 0;
         gridConstraints.insets = new Insets(10,0,0,0);
         getContentPane().add(mealLabel,gridConstraints);
         mealComboBox.setEditable(true);
         gridConstraints = new GridBagConstraints();
         gridConstraints.gridx = 2;
         gridConstraints.gridy = 1;
         gridConstraints.insets = new Insets(10,10,0,10);
         gridConstraints.anchor = GridBagConstraints.NORTH;
         mealComboBox.setPreferredSize(new Dimension(100,25));
         getContentPane().add(mealComboBox,gridConstraints);
         for(String meal:meals){
             mealComboBox.addItem(meal);
         }
         mealComboBox.setSelectedIndex(0);
         
         //Button
         assignButton.setText("Assign");
         gridConstraints = new GridBagConstraints();
         gridConstraints.gridx = 1;
         gridConstraints.gridy = 2;
         gridConstraints.insets = new Insets(0,0,10,0);
         getContentPane().add(assignButton,gridConstraints);
         assignButton.addActionListener(e->{
             assignButtonActionPerformed(e);
         });
         
         exitButton.setText("Exit");
          gridConstraints = new GridBagConstraints();
         gridConstraints.gridx = 2;
         gridConstraints.gridy = 2;
         gridConstraints.insets = new Insets(0,0,10,0);
         getContentPane().add(exitButton,gridConstraints);
         exitButton.addActionListener(e->System.exit(0));
         pack();
    }
    public static void main(String[] args) {
        // TODO code application logic here
         SwingUtilities.invokeLater(() -> {
            Flight window = new Flight();
            window.setVisible(true);
        });
    }

    private void assignButtonActionPerformed(ActionEvent e) {
        String message;
        message = "Destination: " + citiesList.getSelectedValue()+"\n";
        message+="Seat Location: " + seatComboBox.getSelectedItem()+"\n";
        message+="Meal: "+mealComboBox.getSelectedItem()+"\n";
        JOptionPane.showConfirmDialog(null, message,"Your Assignment",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
    }
    
}
