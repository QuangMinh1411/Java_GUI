/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pizza;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author minhc
 */
public class Pizza extends JFrame{
    String pizzaSize;
    String pizzaCrust;
    String pizzaWhere;
    JCheckBox[] topping = new JCheckBox[6];
    
    
    private JRadioButton eatInRadioButton = new JRadioButton();
    private JRadioButton takeOutRadioButton = new JRadioButton();
    private JButton buildButton = new JButton();
    private JButton exitButton = new JButton();
    private ButtonGroup whereButtonGroup = new ButtonGroup();
    
    public Pizza(){
        //Frame
        setTitle("Pizza Order");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                    getWidth())), (int) (0.5 * (screenSize.height -
                    getHeight())), getWidth(), getHeight());
        GridBagConstraints gridConstraints;
        //Size panel
        JPanel sizePanel = new JPanel();
        ButtonGroup sizeButtonGroup = new ButtonGroup();
        JRadioButton smallRadioButton = new JRadioButton();
        JRadioButton mediumRadioButton = new JRadioButton();
        JRadioButton largeRadioButton = new JRadioButton();
        
        sizePanel.setLayout(new GridBagLayout());
        sizePanel.setBorder(BorderFactory.createTitledBorder("Size"));
        //smallRadioButton
        smallRadioButton.setText("Small");
        smallRadioButton.setSelected(true);
        sizeButtonGroup.add(smallRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy =0;
        gridConstraints.anchor = GridBagConstraints.WEST;
        sizePanel.add(smallRadioButton,gridConstraints);
        smallRadioButton.addActionListener(e->{
            sizeRadioButtonActionPerformed(e);
        });
        //mediumRadioButton
        mediumRadioButton.setText("Medium");
        sizeButtonGroup.add(mediumRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy =1;
        gridConstraints.anchor = GridBagConstraints.WEST;
        sizePanel.add(mediumRadioButton,gridConstraints);
        mediumRadioButton.addActionListener(e->{
             sizeRadioButtonActionPerformed(e);
        });
        //largeRadioButton
        largeRadioButton.setText("Large");
        sizeButtonGroup.add(largeRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy =2;
        gridConstraints.anchor = GridBagConstraints.WEST;
        sizePanel.add(largeRadioButton,gridConstraints);
        largeRadioButton.addActionListener(e->{
             sizeRadioButtonActionPerformed(e);
        });
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(sizePanel,gridConstraints);
        
        //Crust panel
        JPanel crustPanel = new JPanel();
        ButtonGroup crustButtonGroup = new ButtonGroup();
        JRadioButton thinRadioButton = new JRadioButton();
        JRadioButton thickRadioButton = new JRadioButton();
        
        crustPanel.setLayout(new GridBagLayout());
        crustPanel.setBorder(BorderFactory.createTitledBorder("Crust"));
        
        //thinRadioButton
        thinRadioButton.setText("Thin Crust");
        thinRadioButton.setSelected(true);
        crustButtonGroup.add(thinRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx=0;
        gridConstraints.gridy=0;
        gridConstraints.anchor = GridBagConstraints.WEST;
        crustPanel.add(thinRadioButton,gridConstraints);
        thinRadioButton.addActionListener(e->{
            crustRadioButtonActionPerformed(e);
        });
        
        //thickRadioButton
        thickRadioButton.setText("Thick Crust");
        
        crustButtonGroup.add(thickRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx=0;
        gridConstraints.gridy=1;
        gridConstraints.anchor = GridBagConstraints.WEST;
        crustPanel.add(thickRadioButton,gridConstraints);
        thickRadioButton.addActionListener(e->{
            crustRadioButtonActionPerformed(e);
        });
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        getContentPane().add(crustPanel,gridConstraints);
        
        //Topping panel
        
        JPanel toppingPanel = new JPanel();
        JCheckBox cheeseCheckBox = new JCheckBox();
        JCheckBox onionsCheckBox = new JCheckBox();
        JCheckBox mushroomsCheckBox = new JCheckBox();
        JCheckBox peppersCheckBox = new JCheckBox();
        JCheckBox olivesCheckBox = new JCheckBox();
        JCheckBox tomatoesCheckBox = new JCheckBox();
        
        toppingPanel.setLayout(new GridBagLayout());
        toppingPanel.setBorder(BorderFactory.createTitledBorder("Topping"));
        //cheese
        cheeseCheckBox.setText("Extra Cheese");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.WEST;
        toppingPanel.add(cheeseCheckBox,gridConstraints);
        //mushroom
        mushroomsCheckBox.setText("Mushrooms");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.anchor = GridBagConstraints.WEST;
        toppingPanel.add(mushroomsCheckBox,gridConstraints);
        //olives
        olivesCheckBox.setText("Olives");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.anchor = GridBagConstraints.WEST;
        toppingPanel.add(olivesCheckBox,gridConstraints);
        //onions
        onionsCheckBox.setText("Onions");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.WEST;
        toppingPanel.add(onionsCheckBox,gridConstraints);
        //peppers
        peppersCheckBox.setText("Green Peppers");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.anchor = GridBagConstraints.WEST;
        toppingPanel.add(peppersCheckBox,gridConstraints);
        //tomatoes
        tomatoesCheckBox.setText("Tomatoes");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        gridConstraints.anchor = GridBagConstraints.WEST;
        toppingPanel.add(tomatoesCheckBox,gridConstraints);
        
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy =0;
        gridConstraints.gridwidth = 2;
        getContentPane().add(toppingPanel,gridConstraints);
        
        //Control panel
        eatInRadioButton.setText("Eat In");
        eatInRadioButton.setSelected(true);
        whereButtonGroup.add(eatInRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(eatInRadioButton,gridConstraints);
        eatInRadioButton.addActionListener(e->{
            whereRadioButtonActionPerformed(e);
        });
        takeOutRadioButton.setText("Take Out");
        whereButtonGroup.add(takeOutRadioButton);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 1;
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(takeOutRadioButton,gridConstraints);
        takeOutRadioButton.addActionListener(e->{
            whereRadioButtonActionPerformed(e);
        });
        
        buildButton.setText("Build Pizza");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        getContentPane().add(buildButton,gridConstraints);
        buildButton.addActionListener(e->{
            buildButtonActionPerformed(e);
        });
        exitButton.setText("Exit");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 2;
        getContentPane().add(exitButton,gridConstraints);
        exitButton.addActionListener(e->{
            System.exit(0);
        });
        
        
        //Initialize parameters
        pizzaSize = smallRadioButton.getText();
        pizzaCrust = thinRadioButton.getText();
        pizzaWhere = eatInRadioButton.getText();
        
        //Define an array of topping check boxes
        topping[0] = cheeseCheckBox;
        topping[1] = mushroomsCheckBox;
        topping[2] = olivesCheckBox;
        topping[3] = onionsCheckBox;
        topping[4] = peppersCheckBox;
        topping[5] = tomatoesCheckBox;
        
        
        
        
        
        pack();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            Pizza window = new Pizza();
            window.setVisible(true);
        });
    }

    private void sizeRadioButtonActionPerformed(ActionEvent e) {
        pizzaSize =e.getActionCommand();
    }

   

    private void whereRadioButtonActionPerformed(ActionEvent e) {
        pizzaWhere = e.getActionCommand();
    }

    private void buildButtonActionPerformed(ActionEvent e) {
        String message;
        message = pizzaWhere + "\n";
        message += pizzaSize + " Pizza" + "\n";
        message += pizzaCrust + "\n";
        for(var top:topping){
            if(top.isSelected()){
                message += top.getText()+"\n";
            }
        }
        JOptionPane.showConfirmDialog(null, message, "Your Pizza",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
    }

    private void crustRadioButtonActionPerformed(ActionEvent e) {
        pizzaCrust = e.getActionCommand();
    }
    
}
