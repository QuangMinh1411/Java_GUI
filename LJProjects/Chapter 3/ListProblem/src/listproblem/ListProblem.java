/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package listproblem;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author minhc
 */
public class ListProblem extends JFrame{
    private JLabel leftLabel = new JLabel();
    private JLabel rightLabel = new JLabel();
    private JScrollPane leftScrollPane = new JScrollPane(); 
    private JList leftList = new JList();
    private JScrollPane rightSrollPane = new JScrollPane();
    private JList rightList = new JList();
    private JButton addBtn = new JButton();
    private JButton removeBtn = new JButton();
    
    private String[] fruits = {"Lemon","Banana","Coconut",
                                "Orange","Melon","Apple","Pineapple","Grape"};
    /**
     * @param args the command line arguments
     */
    
    public ListProblem(){
        setTitle("List Problem");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                    getWidth())), (int) (0.5 * (screenSize.height -
                    getHeight())), getWidth(), getHeight());
        GridBagConstraints gridConstraints;
        // Left panel
        leftLabel.setText("Fruits item");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,0,0,0);
        getContentPane().add(leftLabel,gridConstraints);
        leftScrollPane.setPreferredSize(new Dimension(150,100));
        leftScrollPane.setViewportView(leftList);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(leftScrollPane,gridConstraints);
        DefaultListModel leftListModel = new DefaultListModel();
        for(String fruit:fruits){
            leftListModel.addElement(fruit);
        }
        leftList.setModel(leftListModel);
        addBtn.setText(("Fruit you choose"));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(0,0,10,0);
        getContentPane().add(addBtn,gridConstraints);
        
        
        
        //Right panel
        rightLabel.setText("Fruits you choose");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,0,0,0);
        getContentPane().add(rightLabel,gridConstraints);
        rightSrollPane.setPreferredSize(new Dimension(150,100));
        rightSrollPane.setViewportView(rightList);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(rightSrollPane,gridConstraints);
        DefaultListModel rightListModel = new DefaultListModel();
        
        rightList.setModel(rightListModel);
        removeBtn.setText(("Fruit you give up"));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(0,0,10,0);
        getContentPane().add(removeBtn,gridConstraints);
        
        addBtn.addActionListener(e->{
            moveSelectedItems(leftList,leftListModel,rightListModel);
            sortLeftList(leftListModel);
        });
        
        removeBtn.addActionListener(e->{
            moveSelectedItems(rightList,rightListModel,leftListModel);
            sortLeftList(leftListModel);
        });
        
        pack();
    }
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            ListProblem window = new ListProblem();
            window.setVisible(true);
        });
    }
    
    private void moveSelectedItems(JList<String> sourceList, 
                                  DefaultListModel<String> sourceModel, 
                                  DefaultListModel<String> targetModel) {
        List<String> selectedItems = sourceList.getSelectedValuesList();
        
        // Remove from source (starting from end to avoid index issues)
        int[] selectedIndices = sourceList.getSelectedIndices();
        for (int i = selectedIndices.length - 1; i >= 0; i--) {
            sourceModel.remove(selectedIndices[i]);
        }
        
        // Add to target
        for (String item : selectedItems) {
            targetModel.addElement(item);
        }
    }
   
    private void sortLeftList(DefaultListModel<String>leftListModel){
        List<String> items = new ArrayList<>();
        for (int i = 0; i < leftListModel.getSize(); i++) {
            items.add(leftListModel.getElementAt(i));
        }
        
        // Sort alphabetically
        Collections.sort(items);
        
        // Update the model
        leftListModel.clear();
        for (String item : items) {
            leftListModel.addElement(item);
        }
    }
}
