import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Circle extends JFrame {

    private JLabel diameterLabel;
    private JLabel circumferenceLabel;
    private JLabel areaLabel;
    private JTextField diameterTextField;
    private JTextField circumferenceTextField;
    private JTextField areaTextField;
    private JButton computeButton = new JButton();

    public Circle() {
        setTitle("Circle Geometry");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new GridBagLayout());


        // diameter
        GridBagConstraints gridConstraints = new GridBagConstraints();
        diameterLabel = new JLabel("Enter Diameter");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(diameterLabel, gridConstraints);
        //diameterTextField
        diameterTextField = new JTextField();
        diameterTextField.setText("");
        diameterTextField.setColumns(15);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(diameterTextField, gridConstraints);
        diameterTextField.addActionListener(this::diameterTextFieldActionPerformed);
        //compute Button
        computeButton.setText("Compute");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(computeButton, gridConstraints);
        computeButton.addActionListener(this::computeButtonActionPerformed);

        //circumference Label
        circumferenceLabel = new JLabel("Computed Circumference");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(circumferenceLabel, gridConstraints);
        //circumferenceTextField
        circumferenceTextField = new JTextField();
        circumferenceTextField.setText("");
        circumferenceTextField.setColumns(15);
        circumferenceTextField.setEditable(false);
        circumferenceTextField.setBackground(Color.YELLOW);
        circumferenceTextField.setHorizontalAlignment(JTextField.CENTER);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(circumferenceTextField, gridConstraints);

        areaLabel = new JLabel("Computed Area");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(areaLabel, gridConstraints);

        areaTextField = new JTextField();
        areaTextField.setText("");
        areaTextField.setColumns(15);
        areaTextField.setEditable(false);
        areaTextField.setBackground(Color.YELLOW);
        areaTextField.setHorizontalAlignment(JTextField.CENTER);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 3;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(areaTextField, gridConstraints);

        pack();
        setLocationRelativeTo(null);
    }

    private void computeButtonActionPerformed(ActionEvent actionEvent) {
        if (!validateDecimalNumber(diameterTextField)) {
            circumferenceTextField.setText("");
            areaTextField.setText("");
            return;
        }
        String text = diameterTextField.getText().trim();
        double diameter = Double.parseDouble(text);
        double[] results = circleGeometry(diameter);
        // results[0] = circumference, results[1] = area
        circumferenceTextField.setText(String.format("%.3f", results[0]));
        areaTextField.setText(String.format("%.3f", results[1]));
    }
    public boolean validateDecimalNumber(JTextField tf){
        if (tf == null) return false;
        String text = tf.getText();
        if (text == null) text = "";
        text = text.trim();
        try {
            if (text.isEmpty()) {
                tf.setBackground(Color.PINK);
                JOptionPane.showMessageDialog(this, "Please enter a value.", "Input Required", JOptionPane.WARNING_MESSAGE);
                tf.requestFocusInWindow();
                return false;
            }
            double value = Double.parseDouble(text);
            if (Double.isNaN(value) || Double.isInfinite(value) || value <= 0) {
                tf.setBackground(Color.PINK);
                JOptionPane.showMessageDialog(this, "Please enter a positive decimal number.", "Invalid Number", JOptionPane.ERROR_MESSAGE);
                tf.requestFocusInWindow();
                return false;
            }
            // Valid
            tf.setBackground(Color.WHITE);
            return true;
        } catch (NumberFormatException ex) {
            tf.setBackground(Color.PINK);
            JOptionPane.showMessageDialog(this, "Please enter a valid decimal number (e.g., 12.5).", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            tf.requestFocusInWindow();
            return false;
        }
    }
    public double[] circleGeometry(double diameter){
        double[] circleGeometry = new double[2];
        // circumference = pi * d
        circleGeometry[0] = Math.PI * diameter;
        // area = pi * (d^2) / 4
        circleGeometry[1] = Math.PI * diameter * diameter / 4.0;
        return circleGeometry;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new Circle().setVisible(true);
        });
    }

    private void diameterTextFieldActionPerformed(ActionEvent e){
        computeButton.doClick();
    }
}
