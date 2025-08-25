import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class DrawingEllipses extends JFrame {
    private GraphicsPanel drawingPanel = new GraphicsPanel();
    private JButton drawButton = new JButton();
    private JButton fillButton = new JButton();
    private JButton clearButton = new JButton();
    private static Ellipse2D.Double myEllipse;
    static boolean isDrawn = false;
    static boolean isFilled = false;
    static int fillRed,fillGreen,fillBlue;
    Random myRandom = new Random();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new DrawingEllipses().setVisible(true);
        });
    }
    public DrawingEllipses(){
        setTitle("Drawing Ellipses");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        drawingPanel.setPreferredSize(new Dimension(300, 200));
        drawingPanel.setBackground(Color.WHITE);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(drawingPanel, gridBagConstraints);
        drawButton.setText("Draw Rectangle");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(drawButton, gridBagConstraints);
        drawButton.addActionListener(e->{
            drawButtonActionPerformed(e);
        });

        fillButton.setText("Fill Rectangle");
        fillButton.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        getContentPane().add(fillButton, gridBagConstraints);
        fillButton.addActionListener(e->{
            fillButtonActionPerformed(e);
        });

        clearButton.setText("Clear Rectangle");
        clearButton.setEnabled(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        getContentPane().add(clearButton, gridBagConstraints);
        clearButton.addActionListener(e->{
            clearButtonActionPerformed(e);
        });
        pack();
        setLocationRelativeTo(null);
    }

    private void clearButtonActionPerformed(ActionEvent e) {
        isDrawn = false;
        isFilled = false;
        drawButton.setEnabled(true);
        fillButton.setEnabled(false);
        clearButton.setEnabled(false);
        drawingPanel.repaint();
    }

    private void fillButtonActionPerformed(ActionEvent e) {
        isFilled = true;
        drawButton.setEnabled(false);
        fillRed = myRandom.nextInt(256);
        fillGreen = myRandom.nextInt(256);
        fillBlue = myRandom.nextInt(256);
        drawingPanel.repaint();
    }

    private void drawButtonActionPerformed(ActionEvent e) {
        int w = (myRandom.nextInt(71) + 20) * drawingPanel.getWidth() / 100;
        int h = (myRandom.nextInt(71) + 20) * drawingPanel.getHeight() / 100;
        int x = (int) (0.5 * (drawingPanel.getWidth() - w));
        int y = (int) (0.5 * (drawingPanel.getHeight() - h));
        myEllipse = new Ellipse2D.Double(x, y, w, h);
        isDrawn = true;
        isFilled = false;
        drawButton.setEnabled(false);
        fillButton.setEnabled(true);
        clearButton.setEnabled(true);
        repaint();
    }


    private final class GraphicsPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            if(DrawingEllipses.isFilled){
                g2.setPaint(new Color(DrawingEllipses.fillRed,DrawingEllipses.fillGreen,DrawingEllipses.fillBlue));
                g2.fill(DrawingEllipses.myEllipse);
            }
            if(DrawingEllipses.isDrawn){
                g2.setStroke(new BasicStroke(3));
                g2.setPaint(Color.BLACK);
                g2.draw(DrawingEllipses.myEllipse);

            }
            g2.dispose();
        }
    }

}
