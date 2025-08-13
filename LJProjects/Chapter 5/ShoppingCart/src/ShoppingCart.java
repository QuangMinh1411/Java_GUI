import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class ShoppingCart extends JFrame {
    private JTabbedPane shoppingPane = new JTabbedPane();
    private JPanel orderPanel = new JPanel();
    private JLabel orderLabel = new JLabel();
    private JTextArea orderTextArea = new JTextArea();
    private JSpinner orderSpinner = new JSpinner();
    private JButton addButton = new JButton();
    private JTextField numberTextField = new JTextField();
    private JButton newButton = new JButton();
    private JButton exitButton = new JButton();
    final int numberProducts = 10;
    private String[] product = new String[numberProducts];
    private double[] cost= new double[numberProducts];
    private int[] ordered = new int[numberProducts];
    private int itemsOrdered;

    private JPanel cartPanel = new JPanel();
    private JScrollPane cartPane = new JScrollPane(cartPanel);
    private JTextArea cartTextArea = new JTextArea();
    private JTextField costTextField = new JTextField();

    private JPanel addressPanel = new JPanel();
    private JScrollPane addressPane = new JScrollPane();
    private JTextArea addressTextArea = new JTextArea();

    public ShoppingCart() {
        product[0] = "Tricycle" ; cost[0] = 50;
        product[1] = "Skateboard" ; cost[1] = 60;
        product[2] = "In-Line Skates";cost[2] = 100;
        product[3] = "Magic Set" ; cost[3] = 15;
        product[4] = "Video Game" ; cost[4] = 45;
        product[5] = "Helmet" ; cost[5] = 25;
        product[6] = "Building Kit" ; cost[6] = 35;
        product[7] = "Artist Set" ;cost[7] = 40;
        product[8] = "Doll Baby" ; cost[8] = 25;
        product[9] = "Bicycle" ; cost[9] = 150;
        setTitle("Shopping Cart");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                getWidth())), (int) (0.5 * (screenSize.height -
                getHeight())), getWidth(), getHeight());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(shoppingPane, gridConstraints);
        shoppingPane.addChangeListener(this::shoppingPaneStateChanged);

        //Order panel
        orderPanel.setLayout(new GridBagLayout());
        gridConstraints = new GridBagConstraints();
        orderLabel.setText("Order Address");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 0, 0);
        gridConstraints.anchor = GridBagConstraints.WEST;
        orderPanel.add(orderLabel, gridConstraints);
        gridConstraints = new GridBagConstraints();
        orderTextArea.setColumns(30);
        orderTextArea.setRows(6);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        orderPanel.add(orderTextArea, gridConstraints);
        gridConstraints = new GridBagConstraints();
        orderSpinner.setModel(new SpinnerListModel(product));
        orderSpinner.setPreferredSize(new Dimension(150, 25));
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        orderPanel.add(orderSpinner, gridConstraints);
        gridConstraints = new GridBagConstraints();
        addButton.setText("Add to Order");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        gridConstraints.insets = new Insets(5, 0, 5, 0);
        orderPanel.add(addButton, gridConstraints);
        addButton.addActionListener(this::addButtonActionPerformed);
        gridConstraints = new GridBagConstraints();
        numberTextField.setColumns(20);
        numberTextField.setEditable(false);
        numberTextField.setBackground(Color.white);
        numberTextField.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        numberTextField.setText("Items Ordered: 0");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 4;
        gridConstraints.insets = new Insets(5, 0, 5, 0);
        orderPanel.add(numberTextField, gridConstraints);
        gridConstraints = new GridBagConstraints();
        newButton.setText("New Order");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 5;
        gridConstraints.insets = new Insets(5, 0, 5, 0);
        orderPanel.add(newButton, gridConstraints);
        newButton.addActionListener(this::newButtonActionPerformed);
        gridConstraints = new GridBagConstraints();
        exitButton.setText("Exit");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 6;
        gridConstraints.insets = new Insets(5, 0, 5, 0);
        orderPanel.add(exitButton, gridConstraints);
        exitButton.addActionListener(e->System.exit(0));

        shoppingPane.addTab("Order Form", orderPanel);

        //cart panel
        cartPanel.setLayout(new GridBagLayout());
        cartPane.setPreferredSize(new Dimension(250, 150));
        cartPane.setViewportView(cartTextArea);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        cartPanel.add(cartPane, gridConstraints);
        gridConstraints = new GridBagConstraints();
        costTextField.setColumns(20);
        costTextField.setEditable(false);
        costTextField.setBackground(Color.white);
        costTextField.setText("Total Cost:");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(5, 0, 5, 0);
        cartPanel.add(costTextField, gridConstraints);
        shoppingPane.addTab("Shopping Cart", cartPanel);

        //address panel
        addressPanel.setLayout(new GridBagLayout());
        addressPane.setPreferredSize(new Dimension(250, 150));
        addressPane.setViewportView(addressTextArea);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        addressPanel.add(addressPane, gridConstraints);
        shoppingPane.addTab("Mailing Label", addressPanel);

        pack();
    }

    private void newButtonActionPerformed(ActionEvent actionEvent) {
        //clear form
        orderTextArea.setText("");
        itemsOrdered = 0;
        numberTextField.setText("Items Ordered: 0");
        for(int i=0;i<numberProducts;i++){
            ordered[i]=0;
            orderSpinner.setValue(product[0]);
        }
    }



    private void addButtonActionPerformed(ActionEvent e) {
        int selectedProduct;
        for(selectedProduct=0;selectedProduct<numberProducts;selectedProduct++){
            if(product[selectedProduct].equals(orderSpinner.getValue())){
                break;
            }

        }
        ordered[selectedProduct]++;
        itemsOrdered++;
        numberTextField.setText("Items Ordered: "+itemsOrdered);
    }

    private void shoppingPaneStateChanged(ChangeEvent e) {
        switch (shoppingPane.getSelectedIndex()){
            case 0:
                break;
                case 1:
                    if(itemsOrdered==0){
                        JOptionPane.showConfirmDialog(null,"No items have been ordered","Error",
                                JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
                        shoppingPane.setSelectedIndex(0);
                    }else {
                        double totalCost = 0.0;
                        String order ="";
                        for(int i=0;i<numberProducts;i++){
                            if(ordered[i]!=0){
                                order+=ordered[i]+" "+product[i]+"\n";
                                totalCost+=ordered[i]*cost[i];
                            }
                        }
                        cartTextArea.setText(order);
                        costTextField.setText("Total Cost: $"+new DecimalFormat("0.00").format(totalCost));
                    }
                    break;
                    case 2:
                        if(orderTextArea.getText().equals("")){
                            JOptionPane.showConfirmDialog(null,"Address is blank",
                                    "Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
                            shoppingPane.setSelectedIndex(0);
                        }else{
                            addressTextArea.setText("My Comapany\n + My Address\n + My City,State,Zip\n\n\n"
                            + orderTextArea.getText());
                            break;
                        }

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
           new ShoppingCart().setVisible(true);
        });
    }
}
