import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.text.DecimalFormat;

public class Temperature extends JFrame {
    private JLabel degreesFLabel = new JLabel();
    private JTextField degreesFTextField = new JTextField();
    private JLabel degreesCLabel = new JLabel();
    private JTextField degreesCTextField = new JTextField();
    private JPanel colorPanel = new JPanel();
    private  JScrollBar temperatureScrollBar = new JScrollBar();
    private boolean isHot = false;
    public Temperature() {
        setTitle("Temperature Conversion");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                getWidth())), (int) (0.5 * (screenSize.height -
                getHeight())), getWidth(), getHeight());
        GridBagConstraints gridConstraints;

        //Fahrenheit
        degreesFLabel.setText("Fahrenheit");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(0, 10, 0, 10);
        getContentPane().add(degreesFLabel,gridConstraints);
        degreesFTextField.setColumns(10);
        degreesFTextField.setText("32.0");
        degreesFTextField.setHorizontalAlignment(SwingConstants.CENTER);
        degreesFTextField.setEditable(false);
        degreesFTextField.setBackground(Color.WHITE);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        getContentPane().add(degreesFTextField, gridConstraints);

        //Celsius
        degreesCLabel.setText("Celsius");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(0, 10, 0, 10);
        getContentPane().add(degreesCLabel,gridConstraints);
        degreesCTextField.setColumns(10);
        degreesCTextField.setText("0.0");
        degreesCTextField.setHorizontalAlignment(SwingConstants.CENTER);
        degreesCTextField.setEditable(false);
        degreesCTextField.setBackground(Color.WHITE);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        getContentPane().add(degreesCTextField, gridConstraints);

        //Temperature scroll

        colorPanel.setBackground(Color.BLUE);
        colorPanel.setPreferredSize(new Dimension(280,40));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth=2;
        getContentPane().add(colorPanel, gridConstraints);
        temperatureScrollBar.setMinimum(-600);
        temperatureScrollBar.setMaximum(1200+temperatureScrollBar.getVisibleAmount());
        temperatureScrollBar.setBlockIncrement(10);
        temperatureScrollBar.setUnitIncrement(1);
        temperatureScrollBar.setValue(320);
        temperatureScrollBar.setOrientation(SwingConstants.HORIZONTAL);
        temperatureScrollBar.setPreferredSize(new Dimension(200,30));
        colorPanel.add(temperatureScrollBar);
        temperatureScrollBar.addAdjustmentListener(this::temperatureScrollBarAdjustmentValueChanged);


        pack();
    }

    private void temperatureScrollBarAdjustmentValueChanged(AdjustmentEvent e) {
        double tempF,tempC;
        tempF = (double) temperatureScrollBar.getValue()/10.0;
        if(isHot && tempF<70){
            isHot = false;
            colorPanel.setBackground(Color.BLUE);

        }else if(!isHot && tempF>70){
            isHot = true;
            colorPanel.setBackground(Color.RED);
        }
        degreesFTextField.setText(new DecimalFormat("0.0").format(tempF));
        tempC = (tempF-32)*5.0/9.0;
        degreesCTextField.setText(new DecimalFormat("0.0").format(tempC));

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Temperature window = new Temperature();
            window.setVisible(true);
        });
    }


}
