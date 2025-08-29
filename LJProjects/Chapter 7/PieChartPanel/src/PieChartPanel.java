import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;

public class PieChartPanel extends JPanel {
    private Rectangle2D.Double borderRectangle;
    private int n;
    private double[] y;
    private Color[] c;
    public PieChartPanel(Rectangle2D.Double border, int nSegments,
                         double[] yValues, Color[] colorValues){
        this.borderRectangle = border;
        this.n = nSegments;
        this.y = yValues;
        this.c = colorValues;

    }
    public void paintComponent(Graphics g){
        //Draws a pie chart
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);
        double sum = 0.0;
        for(int i=0;i<n;i++){
            sum+=y[i];
        }
        //draw pie
        double startAngle = 0;
        Arc2D.Double myArc;
        for(int i=0;i<n;i++){
            myArc = new Arc2D.Double(borderRectangle.x, borderRectangle.y, borderRectangle.width, borderRectangle.height,
                    startAngle, y[i]*360/sum, Arc2D.OPEN);
            g2D.fill(myArc);
            g2D.setPaint(c[i]);
            g2D.draw(myArc);
            startAngle+=y[i]*360/sum;
        }
        g2D.dispose();
    }
}
