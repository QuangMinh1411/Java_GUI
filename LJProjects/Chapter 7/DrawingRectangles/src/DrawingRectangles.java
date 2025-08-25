import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

public class DrawingRectangles extends JFrame {
    private GraphicsPanel drawingPanel = new GraphicsPanel();
    private JButton drawButton = new JButton();
    private JButton fillButton = new JButton();
    private JButton clearButton = new JButton();
    private static Rectangle2D.Double myRectangle;
    private static RoundRectangle2D.Double myRoundRectangle;
    static boolean isRound = false;
    static boolean isDrawn = false;
    static boolean isFilled = false;
    static int fillRed,fillGreen,fillBlue;
    Random myRandom = new Random();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new DrawingRectangles().setVisible(true);
        });
    }
    public DrawingRectangles() {
        setTitle("Drawing Rectangles");
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
        if(myRandom.nextInt(100)<=49){
            isRound = true;
            int cw = (myRandom.nextInt(21) + 10) * drawingPanel.getWidth() / 100;
            int ch = (myRandom.nextInt(21) + 10) * drawingPanel.getHeight() / 100;
            // Fix: cw and ch are the corner arc sizes, not the rectangle's width/height.
            // Use w and h for the rectangle dimensions and cw/ch for the corner arcs.
            myRoundRectangle = new RoundRectangle2D.Double(x, y, w, h, cw, ch);
        }else {
            isRound = false;
            myRectangle = new Rectangle2D.Double(x, y, w, h);
        }
        isDrawn = true;
        isFilled = false;
        drawButton.setEnabled(false);
        fillButton.setEnabled(true);
        clearButton.setEnabled(true);
        drawingPanel.repaint();
    }

    private final class GraphicsPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            if(DrawingRectangles.isFilled){
                g2.setPaint(new Color(DrawingRectangles.fillRed,DrawingRectangles.fillGreen,DrawingRectangles.fillBlue));
                if(DrawingRectangles.isRound){
                    g2.fill(DrawingRectangles.myRoundRectangle);
                }else{
                    g2.fill(DrawingRectangles.myRectangle);
                }
            }
            if(DrawingRectangles.isDrawn){
                g2.setStroke(new BasicStroke(3));
                g2.setPaint(Color.BLACK);
                if(DrawingRectangles.isRound){
                    g2.draw(DrawingRectangles.myRoundRectangle);
                }else{
                    g2.draw(DrawingRectangles.myRectangle);
                }
            }
            g2.dispose();
        }
    }

}
