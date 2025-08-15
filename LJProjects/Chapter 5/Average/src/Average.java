import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Average extends JFrame {
    private JLabel enterLabel = new JLabel();
    private JTextField enterTextField = new JTextField();
    private JButton addButton = new JButton();
    private JScrollPane listPane = new JScrollPane();
    private JList<Double> valuesList = new JList<>();
    private DefaultListModel<Double> valueListModel = new DefaultListModel<>();
    private JButton clearButton = new JButton();
    private JTextField averageTextField = new JTextField();
    private JButton computeButton = new JButton();
    

    public Average() {
        setTitle("Average Value");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();

        //enterLabel
        enterLabel.setText("Enter numbers:");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(enterLabel, gridConstraints);
        //enterTextField
        enterTextField.setText("");
        enterTextField.setColumns(15);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(0, 10, 0, 10);
        getContentPane().add(enterTextField, gridConstraints);
        enterTextField.addActionListener(this::enterTextFieldActionPerformed);
        //addButton
        addButton.setText("Add to List");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(10, 0, 0, 0);
        getContentPane().add(addButton, gridConstraints);
        addButton.addActionListener(this::addButtonActionPerformed);
        //listPane
        valuesList.setModel(valueListModel);
        listPane.setPreferredSize(new Dimension(150, 150));
        listPane.setViewportView(valuesList);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(listPane, gridConstraints);
        //clearButton
        clearButton.setText("Clear List");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 4;
        getContentPane().add(clearButton, gridConstraints);
        clearButton.addActionListener(this::clearButtonActionPerformed);
        //averageTextField
        averageTextField.setText("");
        averageTextField.setEditable(false);
        averageTextField.setColumns(15);
        averageTextField.setBackground(Color.white);
        averageTextField.setHorizontalAlignment(JTextField.CENTER);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 5;
        gridConstraints.insets = new Insets(10, 0, 0, 0);
        getContentPane().add(averageTextField, gridConstraints);
        //computeButton
        computeButton.setText("Compute Average");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 6;
        gridConstraints.insets = new Insets(10, 0, 10, 0);
        getContentPane().add(computeButton, gridConstraints);
        computeButton.addActionListener(this::computeButtonActionPerformed);
        pack();
        setLocationRelativeTo(null);
    }

    private void enterTextFieldActionPerformed(ActionEvent actionEvent) {
        addButton.doClick();
    }

    private void computeButtonActionPerformed(ActionEvent actionEvent) {
        int count = valueListModel.getSize();
        if (count != 0) {
            double sum = 0.0;
            for (int i = 0; i < count; i++) {
                sum += valueListModel.getElementAt(i);
            }
            double myAverage = sum / count;
            averageTextField.setText(String.format("%.2f", myAverage));
            enterTextField.requestFocus();
        }
    }

    private void clearButtonActionPerformed(ActionEvent actionEvent) {
        valueListModel.removeAllElements();
        averageTextField.setText("");
        enterTextField.setText("");
        enterTextField.requestFocus();
    }

    private void addButtonActionPerformed(ActionEvent actionEvent) {
        if(!validateDecimalNumber(enterTextField)){
            return;
        }
        valueListModel.addElement(Double.parseDouble(enterTextField.getText().trim()));
        enterTextField.setText("");
        enterTextField.requestFocus();
    }
    public double average(int numberValues,double[] values){
        double sum = 0.0;
        for(int i = 0; i < numberValues; i++){
            sum += values[i];
        }
        return sum/numberValues;
    }
    public boolean validateDecimalNumber(JTextField tf){
        String s = tf.getText();
        if (s != null) {
            s = s.trim();
        }
        boolean valid = true;
        if (s == null || s.isEmpty()) {
            valid = false;
        } else {
            try {
                Double.parseDouble(s);
            } catch (NumberFormatException e) {
                valid = false;
            }
        }
        if (valid) {
            tf.setText(s);
        } else {
            tf.setText("");
            tf.requestFocus();
            Toolkit.getDefaultToolkit().beep();
        }
        return valid;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new Average().setVisible(true);
        });
    }
}
