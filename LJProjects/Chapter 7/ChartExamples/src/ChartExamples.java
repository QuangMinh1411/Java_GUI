import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class ChartExamples extends JFrame {
    private JPanel myPanel = new JPanel();
    private JButton lineButton = new JButton();
    private JButton barButton = new JButton();
    private JButton spiralButton = new JButton();
    private JButton pieButton = new JButton();
    double[]x = new double[200];
    double[]y = new double[200];
    Color[] plotColor = new Color[10];
    private Random myRandom = new Random();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new ChartExamples().setVisible(true);
        });
    }
    public ChartExamples(){
        plotColor[0] = Color.YELLOW;
        plotColor[1] = Color.BLUE;
        plotColor[2] = Color.GREEN;
        plotColor[3] = Color.CYAN;
        plotColor[4] = Color.RED;
        plotColor[5] = Color.MAGENTA;
        plotColor[6] = Color.ORANGE;
        plotColor[7] = Color.DARK_GRAY;
        plotColor[8] = Color.GRAY;
        plotColor[9] = Color.BLACK;
        setTitle("Chart Examples");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new GridBagLayout());
        myPanel.setPreferredSize(new Dimension(400,300));
        myPanel.setBackground(Color.WHITE);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridwidth = 4;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(myPanel,gridConstraints);
        
        lineButton.setText("Line Chart");
        lineButton.setPreferredSize(new Dimension(100,25));
        gridConstraints= new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(5,5,5,5);
        getContentPane().add(lineButton,gridConstraints);
        lineButton.addActionListener(e->{
            lineButtonActionPerformed(e);
        });
        
        spiralButton.setText("Spiral");
        spiralButton.setPreferredSize(new Dimension(100,25));
        gridConstraints= new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(5,5,5,5);
        getContentPane().add(spiralButton,gridConstraints);
        spiralButton.addActionListener(e->{
            spiralButtonActionPerformed(e);
        });
        
        barButton.setText("Bar Chart");
        barButton.setPreferredSize(new Dimension(100,25));
        gridConstraints= new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(5,5,5,5);
        getContentPane().add(barButton,gridConstraints);
        barButton.addActionListener(e->{
            barButtonActionPerformed(e);
        });
        
        pieButton.setText("Pie Chart");
        pieButton.setPreferredSize(new Dimension(100,25));
        gridConstraints= new GridBagConstraints();
        gridConstraints.gridx = 3;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(5,5,5,5);
        getContentPane().add(pieButton,gridConstraints);
        pieButton.addActionListener(e->{
            pieButtonActionPerformed(e);
        });
        
        pack();
        setLocationRelativeTo(null);
    }

    private void pieButtonActionPerformed(ActionEvent e) {
        // Generate random pie chart data with 5-8 segments
        int n = 5 + myRandom.nextInt(4);
        double[] values = new double[n];
        Color[] colors = new Color[n];
        for (int i = 0; i < n; i++) {
            values[i] = 1 + myRandom.nextDouble() * 9; // positive weights
            colors[i] = plotColor[myRandom.nextInt(plotColor.length)];
        }
        Rectangle2D.Double borderRectangle = new Rectangle2D.Double(60, 30, 280, 240);
        PieChartPanel panel = new PieChartPanel(borderRectangle, n, values, colors);
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setBackground(Color.WHITE);
        showPanel(panel);
    }

    private void barButtonActionPerformed(ActionEvent e) {
        // Create simple bar chart dataset of length 10 around baseline 0
        int n = 10;
        double[] values = new double[n];
        for (int i = 0; i < n; i++) {
            values[i] = -10 + myRandom.nextDouble() * 20; // range [-10,10]
        }
        double baseline = 0.0;
        Rectangle2D.Double borderRectangle = new Rectangle2D.Double(40, 20, 320, 260);
        // Note: BarCharPanel is a JPanel-like painter but currently extends JFrame and uses paintComponent.
        // We'll instantiate it and add as a component; this works because it inherits from Frame but not ideal.
        // Minimal change: keep usage consistent.
        BarCharPanel panel = new BarCharPanel(borderRectangle, n, values, baseline,
                plotColor[myRandom.nextInt(plotColor.length)]);
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setBackground(Color.WHITE);
        showPanel(panel);
    }

    private void spiralButtonActionPerformed(ActionEvent e) {
        // Render a spiral as a line chart dataset
        int n = 200;
        for (int i = 0; i < n; i++) {
            double t = i * 0.15;
            x[i] = t;
            y[i] = t * Math.sin(t); // simple spiral-ish curve in param space
        }
        Rectangle2D.Double borderRectangle = new Rectangle2D.Double(20, 20, 360, 260);
        LineChartPanel panel = new LineChartPanel(borderRectangle, n, x, y,
                plotColor[myRandom.nextInt(plotColor.length)]);
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setBackground(Color.WHITE);
        showPanel(panel);
    }

    private void lineButtonActionPerformed(ActionEvent e) {
        double alpha = 0.1 - myRandom.nextDouble() * 0.2;
        double beta = myRandom.nextDouble() * 10 + 5;
        for (int i = 0; i < 200; i++) {
            x[i] = i;
            y[i] = Math.exp(-alpha * i) * Math.sin(Math.PI * i / beta);
        }
        Rectangle2D.Double borderRectangle = new Rectangle2D.Double(20, 20, 360, 260);
        LineChartPanel myLineChart = new LineChartPanel(borderRectangle, 200,
                x, y, plotColor[myRandom.nextInt(10)]);

        myLineChart.setPreferredSize(new Dimension(400, 300));
        myLineChart.setBackground(Color.WHITE);
        showPanel(myLineChart);
    }

    private void showPanel(JComponent panel) {
        myPanel.removeAll();
        myPanel.add(panel);
        myPanel.revalidate();
        myPanel.repaint();
    }
}
