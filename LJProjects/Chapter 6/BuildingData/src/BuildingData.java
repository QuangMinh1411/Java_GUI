import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class BuildingData extends JFrame {
    private JLabel minLabel = new JLabel();
    private JTextField minTextField = new JTextField();
    private JLabel maxLabel = new JLabel();
    private JTextField maxTextField = new JTextField();
    private JButton computeButton = new JButton();
    private JScrollPane resultsScrollPane = new JScrollPane();
    private JTextArea resultsTextArea = new JTextArea();
    public BuildingData() {
        setTitle("Circle Geometries");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();

        minLabel.setText("Minimum Diameter");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(minLabel, gridConstraints);

        minTextField.setText("");
        minTextField.setColumns(15);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(minTextField, gridConstraints);

        maxLabel.setText("Maximum Diameter");

        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(maxLabel, gridConstraints);

        maxTextField.setText("");
        maxTextField.setColumns(15);

        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(maxTextField, gridConstraints);

        computeButton.setText("Compute Geometries");

        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.gridwidth = 2;
        getContentPane().add(computeButton, gridConstraints);
        computeButton.addActionListener(this::computeButtonActionPerformed);

        resultsTextArea.setColumns(45);
        resultsTextArea.setRows(10);
        resultsTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        resultsScrollPane.setViewportView(resultsTextArea);

        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(resultsScrollPane, gridConstraints);

        pack();
        setLocationRelativeTo(null);
    }

    private void computeButtonActionPerformed(ActionEvent actionEvent) {
        final int numberValues = 20;
        double dMin, dMax;
        try {
            dMin = Double.parseDouble(minTextField.getText().trim());
            dMax = Double.parseDouble(maxTextField.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for both diameters.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (dMin >= dMax) {
            JOptionPane.showMessageDialog(this, "Maximum must be greater than the minimum.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Circle Geometries").append("\n");
        sb.append(String.format("%10s %12s %12s%n", "Diameter", "Perimeter", "Area"));
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i <= numberValues; i++) {
            double d = dMin + (dMax - dMin) * i / numberValues;
            double[] values = circleGeometries(d);
            sb.append(String.format("%10s %12s %12s%n",
                    df.format(d), df.format(values[0]), df.format(values[1])));
        }
        resultsTextArea.setText(sb.toString());
        minTextField.requestFocus();
    }

    private double[] circleGeometries(double diameter) {
        double[] geometry = new double[2];
        geometry[0] = Math.PI * diameter;
        geometry[1] = Math.PI*diameter*diameter/4;
        return geometry;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new BuildingData().setVisible(true);
        });
    }
}
