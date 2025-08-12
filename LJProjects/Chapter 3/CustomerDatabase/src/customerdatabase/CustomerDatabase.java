/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package customerdatabase;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author minhc
 */
public class CustomerDatabase extends JFrame{

    /**
     * @param args the command line arguments
     */
    private String[] citiList = {
      "New York City","Los Angeles","Chicago",  
      "Houston","Phoenix","Philadelphia",
      "San Antonio","San Diego","Dallas",
      "Jacksonville","Fort Worth","San Jose",
      "Austin","Charlotte","Columbus" 
    };
    private String[] activities = {
      "Running","Walking","Biking",
      "Swimming","Skiing","Skating"
    };
    private String[] levels = {
      "Beginner","Intermediate",
      "Advanced","Extreme"
    };
    JTextField nameField = new JTextField();
    JTextField ageField = new JTextField();
    private List<JCheckBox> actCheckBoxes = new ArrayList<>();
    private List<JRadioButton> levelRadioButton = new ArrayList<>();
    private String name;
    private int age;
    private String sex;
    private String city;
    private String level;
    private JButton displayBtn = new JButton();
    public CustomerDatabase(){
        //Frame
        setTitle("Customer Information");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                    getWidth())), (int) (0.5 * (screenSize.height -
                    getHeight())), getWidth(), getHeight());
        GridBagConstraints gridConstraints;
        //Customer basic panel
        JPanel basicPanel = new JPanel();
        JLabel nameLabel = new JLabel();
        JLabel ageLabel = new JLabel();
        JLabel cityLabel = new JLabel();
        JLabel sexLabel = new JLabel();
        
        JComboBox cityBox = new JComboBox();
        ButtonGroup sexButtonGroup = new ButtonGroup();
        JRadioButton maleRadioButton = new JRadioButton();
        JRadioButton femaleRadioButton = new JRadioButton();
        
        basicPanel.setLayout(new GridBagLayout());
        basicPanel.setBorder(BorderFactory.createTitledBorder("Basic Infor"));
        //Name, age
        nameLabel.setText("Customer name");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,0,0,0);
        basicPanel.add(nameLabel,gridConstraints);
        nameField.setColumns(10);
        
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,10,0,0);
        basicPanel.add(nameField,gridConstraints);
        ageLabel.setText("Age");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10,0,0,0);
        gridConstraints.anchor = GridBagConstraints.WEST;
        basicPanel.add(ageLabel,gridConstraints);
//        ageField.setText("");
        
        ageField.setColumns(10);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10,10,0,0);
        basicPanel.add(ageField,gridConstraints);
        //City and sex
        cityLabel.setText("City List");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,10,0,0);
        gridConstraints.anchor = GridBagConstraints.WEST;
        basicPanel.add(cityLabel,gridConstraints);
        cityBox.setEditable(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 3;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,10,0,10);
        gridConstraints.anchor = GridBagConstraints.NORTH;
        cityBox.setPreferredSize(new Dimension(120,20));
        basicPanel.add(cityBox,gridConstraints);
        for(String city:citiList){
            cityBox.addItem(city);
        }
        cityBox.setSelectedIndex(0);
        
        //Male radio button
        sexLabel.setText("Sex");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy =1;
        gridConstraints.insets = new Insets(10,10,0,0);
        gridConstraints.anchor = GridBagConstraints.WEST;
        basicPanel.add(sexLabel,gridConstraints);
        maleRadioButton.setText("Male");
        maleRadioButton.setSelected(true);
        sexButtonGroup.add(maleRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 3;
        gridConstraints.gridy =1;
        gridConstraints.insets = new Insets(10,10,0,10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        basicPanel.add(maleRadioButton,gridConstraints);
        maleRadioButton.addActionListener(e->{
            sexRadioButtonActionPerformed(e);
        });
        
        femaleRadioButton.setText("Female");
        sexButtonGroup.add(femaleRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 4;
        gridConstraints.gridy =1;
        gridConstraints.insets = new Insets(10,10,0,10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        basicPanel.add(femaleRadioButton,gridConstraints);
        femaleRadioButton.addActionListener(e->{
            sexRadioButtonActionPerformed(e);
        });
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(basicPanel,gridConstraints);
        
        //Addition panel
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridBagLayout());
        addPanel.setBorder(BorderFactory.createTitledBorder("Activity Infor"));
        
        //Activities
        for(int i=0;i<activities.length;i++){
            JCheckBox checkBox = new JCheckBox();
            actCheckBoxes.add(checkBox);
            checkBox.setText(activities[i]);
            gridConstraints.gridx = i/3;
            gridConstraints.gridy = i%3;
            gridConstraints.anchor = GridBagConstraints.WEST;
            addPanel.add(checkBox,gridConstraints);
        }
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        getContentPane().add(addPanel,gridConstraints);
        
        //Level panel
        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new GridBagLayout());
        levelPanel.setBorder(BorderFactory.createTitledBorder("Level Infor"));
        ButtonGroup levelButtonGroup = new ButtonGroup();
        for(int i=0;i<levels.length;i++){
            JRadioButton rdi = new JRadioButton();
            rdi.setText(levels[i]);
            levelButtonGroup.add(rdi);
            levelRadioButton.add(rdi);
            if(i==0)
                rdi.setSelected(rootPaneCheckingEnabled);
            gridConstraints.gridx = i/2;
            gridConstraints.gridy = i%2;
            gridConstraints.anchor = GridBagConstraints.WEST;
            levelPanel.add(rdi,gridConstraints);
            rdi.addActionListener(e->{
                  levelActivities(e);
            });
        }
        
        
        
        
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        getContentPane().add(levelPanel,gridConstraints);
        
        displayBtn.setText("Display");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        getContentPane().add(displayBtn,gridConstraints);
        displayBtn.addActionListener(e->{
            displayPersonInfor();
        
        });
        
        //Intialize parameters
        name = nameField.getText();
        if(isInteger(ageField.getText()))
            age = Integer.parseInt(ageField.getText());
        city = cityBox.getSelectedItem().toString();
        sex = maleRadioButton.getText();
        level = levelRadioButton.get(0).getText();
        pack();
    }
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            CustomerDatabase window = new CustomerDatabase();
            window.setVisible(true);
        });
    }
    
    public static boolean isInteger(String str) {
        if (str == null) return false;
            str = str.trim();
        if (str.isEmpty()) return false;
    
        // Regex: Optional sign, then digits (no leading zeros unless single zero)
        if (!str.matches("[-+]?\\d+")) return false;
    
         // Handle integer range manually
         try {
            Long.valueOf(str); // Use long to detect int overflow
            return true;
        } catch (NumberFormatException e) {
        return false;
    }
}

    private void sexRadioButtonActionPerformed(ActionEvent e) {
        sex = e.getActionCommand();
    }

    private void displayPersonInfor() {
        name = nameField.getText();
        age = Integer.parseInt(ageField.getText());
        String message = "";
        String s = sex.equalsIgnoreCase("Male")?"He":"She";
        message +=name + " is " + age + " years old\n" ;
        message +=s+ " lives in "+ city+"\n";
        message += s+ " is an " + level + " athlete.\n";
        message += "Activities include: \n";
        for(var act:actCheckBoxes){
            if(act.isSelected()){
                message += " "+ act.getText()+"\n";
            }
        }
        JOptionPane.showConfirmDialog(null, message, "Customer Profile",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
    }

    private void levelActivities(ActionEvent e) {
        level = e.getActionCommand();
    }

    
    
}
