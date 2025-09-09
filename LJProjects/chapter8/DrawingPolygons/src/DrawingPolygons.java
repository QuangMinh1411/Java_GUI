/*
DrawingPolygons

Usage:
- Left-click on the white panel to place vertices. Small red dots mark each click.
- After at least three points, the "Draw Polygon" button enables; click it to close and outline the polygon in blue.
- Click "Fill Polygon" to fill the current polygon with a random color.
- Click the panel again to start a new polygon (clears existing points and disables buttons).

Implementation notes:
- Points are stored in a Vector<Point2D.Double> (myPoints).
- While adding points (shapeDrawn == false), points are shown as tiny red circles (Ellipse2D).
- When drawing, a GeneralPath is built from the points, closed, optionally filled, then stroked in blue.
- UI is built with Swing; custom painting occurs in GraphicsPanel.paintComponent using Graphics2D.
*/
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

public class DrawingPolygons extends JFrame {
    private GraphicsPanel drawPanel = new GraphicsPanel();
    private JButton drawButton = new JButton("Draw Polygon");
    private JButton fillButton = new JButton("Fill Polygon");
    static boolean shapeDrawn = true;
    static boolean shapeFilled = false;
    static Color fillColor;
    static Vector<Point2D.Double> myPoints = new Vector(50,10);
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DrawingPolygons().setVisible(true);
            }
        });
    }

    public DrawingPolygons() {
        setTitle("Drawing Polygons");
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
        pack();
        setLocationRelativeTo(null);
    }

    private void fillButtonActionPerformed(ActionEvent actionEvent) {
        fillColor = new Color(new Random().nextInt(256),new Random().nextInt(256),new Random().nextInt(256));
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

    class GraphicsPanel extends JPanel{
        public GraphicsPanel() {}
        @Override
        protected void paintComponent(Graphics g) {
            GeneralPath myShape = new GeneralPath();
            Graphics2D g2d = (Graphics2D) g;
            super.paintComponent(g);
            if(DrawingPolygons.myPoints.size() == 0){
                return;
            }
            for(int i = 0; i < DrawingPolygons.myPoints.size(); i++) {
                Point2D.Double myPoint = DrawingPolygons.myPoints.elementAt(i);
                if (!DrawingPolygons.shapeDrawn) {
                    g2d.setPaint(Color.RED);
                    g2d.fill(new Ellipse2D.Double(myPoint.getX() - 1, myPoint.getY() - 1, 3, 3));
                } else {
                    if (i == 0)
                        myShape.moveTo((float) myPoint.getX(), (float) myPoint.getY());
                    else
                        myShape.lineTo((float) myPoint.getX(), (float) myPoint.getY());
                }
            }
            if(DrawingPolygons.shapeDrawn){
                    myShape.closePath();
                    if(DrawingPolygons.shapeFilled){
                        g2d.setPaint(DrawingPolygons.fillColor);
                        g2d.fill(myShape);
                    }
                    g2d.setStroke(new BasicStroke(2));
                    g2d.setPaint(Color.BLUE);
                    g2d.draw(myShape);
                }
            g2d.dispose();
        }

    }
}
