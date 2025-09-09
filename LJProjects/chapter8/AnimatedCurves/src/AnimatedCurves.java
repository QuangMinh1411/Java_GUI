import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Vector;

public class AnimatedCurves extends JFrame{
    GraphicsPanel drawPanel = new GraphicsPanel();
    JButton drawButton = new JButton("Draw Curve");
    JButton fillButton = new JButton("Fill Curve");
    javax.swing.Timer myTimer;
    static boolean shapeDrawn = true; //set to true for proper initialization
    static boolean shapeFilled = false;
    static Color fillColor;
    static Vector<Point2D.Double> myPoints = new Vector<>(50, 10);
    Random myRandom = new Random();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new AnimatedCurves().setVisible(true);
        });
    }

    public AnimatedCurves() {
        setTitle("Animated Curves");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

//drawPanel
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        drawPanel.setPreferredSize(new Dimension(350, 250));
        drawPanel.setBackground(Color.WHITE);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(drawPanel, gridBagConstraints);
        drawPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                drawPanelMousePressed(e);
            }
        });

        drawButton.setEnabled(false);
        gridBagConstraints= new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        getContentPane().add(drawButton, gridBagConstraints);
        drawButton.addActionListener(this::drawButtonActionPerformed);

        fillButton.setEnabled(false);
        gridBagConstraints= new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        getContentPane().add(fillButton, gridBagConstraints);
        fillButton.addActionListener(this::fillButtonActionPerformed);
        
        myTimer = new javax.swing.Timer(100, this::myTimerActionPerformed
        );
        pack();
        setLocationRelativeTo(null);
    }

    private void myTimerActionPerformed(ActionEvent e) {
        for(int i = 0; i < myPoints.size(); i++){
            Point2D.Double myPoint = myPoints.elementAt(i);
            myPoint.x += myRandom.nextDouble()*20-10.0;
            myPoint.y += myRandom.nextDouble()*20-10.0;
            myPoints.setElementAt(myPoint, i);
        }
        drawPanel.repaint();
    }

    private void fillButtonActionPerformed(ActionEvent actionEvent) {
        myTimer.start();
        fillColor = new Color(myRandom.nextInt(256),
                myRandom.nextInt(256), myRandom.nextInt(256));
        shapeFilled = true;
        drawPanel.repaint();
    }

    private void drawButtonActionPerformed(ActionEvent actionEvent) {
        drawButton.setEnabled(false);
        fillButton.setEnabled(true);
        shapeDrawn = true;
        drawPanel.repaint();
    }

    private void drawPanelMousePressed(MouseEvent e) {
        if(shapeDrawn){
            myTimer.stop();
            drawButton.setEnabled(false);
            fillButton.setEnabled(false);
            shapeDrawn = false;
            shapeFilled = false;
            myPoints.removeAllElements();
        }
        Point2D.Double myPoint = new Point2D.Double(e.getX(), e.getY());
        myPoints.add(myPoint);
        if(myPoints.size() > 2){
            drawButton.setEnabled(true);
        }
        drawPanel.repaint();
    }

     static class GraphicsPanel extends JPanel {
        public GraphicsPanel() {}
        @Override
        protected void paintComponent(Graphics g) {
            GeneralPath myCurve = new GeneralPath();
            Graphics2D g2D = (Graphics2D) g;
            super.paintComponent(g);
            int numberPoints = AnimatedCurves.myPoints.size();
            if (numberPoints == 0)
            {
                return;
            }
// array for control points
            Point2D[] controlPoint = new Point2D[numberPoints];
            for (int i = 0; i < numberPoints; i++)
            {
                controlPoint[i] = AnimatedCurves.myPoints.elementAt(i);
                if (!AnimatedCurves.shapeDrawn)
                {
// points only
                    g2D.setPaint(Color.RED);
                    g2D.fill(new Ellipse2D.Double(controlPoint[i].getX() - 1, controlPoint[i].getY() - 1, 3, 3));
                }
            }
            if(AnimatedCurves.shapeDrawn)
            {
// array for curve points
                Point2D[] curvePoint = new Point2D[numberPoints];
// establish last point first
                curvePoint[numberPoints - 1] = new Point2D.Double(0.5 *(controlPoint[numberPoints - 1].getX() + controlPoint[0].getX()),
                        0.5 *(controlPoint[numberPoints -1].getY() + controlPoint[0].getY()));
                myCurve.moveTo((float) curvePoint[numberPoints -1].getX(), (float) curvePoint[numberPoints - 1].getY());
                for (int i = 0; i < numberPoints; i++) {
                    if (i < numberPoints - 1)
                    {
                        curvePoint[i] = new Point2D.Double(0.5 *(controlPoint[i].getX() + controlPoint[i + 1].getX()),
                                0.5* (controlPoint[i].getY() + controlPoint[i + 1].getY()));
                    }
                    myCurve.quadTo((float) controlPoint[i].getX(), (float) controlPoint[i].getY(),
                            (float) curvePoint[i].getX(),
                            (float) curvePoint[i].getY()); }
                if (AnimatedCurves.shapeFilled)
                {
                    g2D.setPaint(AnimatedCurves.fillColor);
                    g2D.fill(myCurve);
                }
                g2D.setStroke(new BasicStroke(2));
                g2D.setPaint(Color.BLUE);
                g2D.draw(myCurve);
            }
            g2D.dispose();

        }
    }
}
