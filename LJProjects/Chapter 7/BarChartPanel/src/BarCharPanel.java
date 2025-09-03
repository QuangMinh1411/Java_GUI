import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class BarCharPanel extends JFrame {
    private Rectangle2D.Double borderRectangle;
    private int n;
    private double[] y;
    private double b;
    private Color c;
    public BarCharPanel(){}
    public BarCharPanel(Rectangle2D.Double borderRectangle, int n, double[] y, double b, Color c){
        this.borderRectangle = borderRectangle;
        this.n = n;
        this.y = y;
        this.b = b;
        this.c = c;
    }
    public void paintComponent(Graphics g){
        double yMin = y[0];
        double yMax = y[0];
        for(int i = 1; i < n; i++){
            if(y[i] < yMin)
                yMin = y[i];
            if(y[i] > yMax)
                yMax = y[i];
        }
        Graphics2D g2D = (Graphics2D) g;
        super.paint(g2D);
        double barWidth =2*(borderRectangle.width - 1)/(3*n+1);
        double clientBase = yPhysicalToyUser(borderRectangle,b,yMin,yMax);
        Rectangle2D.Double myRectangle;
        for(int i = 0; i < n; i++){
            if(y[i]>b){
                myRectangle = new
                        Rectangle2D.Double(borderRectangle.x + (1.5*i + 0.5)* barWidth,
                        yPhysicalToyUser(borderRectangle, y[i], yMin, yMax), barWidth,
                        clientBase -yPhysicalToyUser(borderRectangle, y[i], yMin, yMax));
            }else{
                myRectangle = new
                        Rectangle2D.Double(borderRectangle.x + (1.5*i + 0.5)*barWidth,
                        clientBase, barWidth, yPhysicalToyUser(borderRectangle, y[i], yMin,
                        yMax) -clientBase);
            }
            g2D.setPaint(c);
            g2D.fill(myRectangle);
        }
        g2D.setPaint(Color.BLACK);
        g2D.draw(borderRectangle);
        g2D.draw(new Line2D.Double(borderRectangle.x, clientBase,
                borderRectangle.x + borderRectangle.width - 1, clientBase));
        g2D.dispose();
    }
    private double xPhysicalToxUser(Rectangle2D.Double r,double xPhysical,double xMin,double xMax){
        return (r.x+(xPhysical-xMin)*(r.width-1)/(xMax-xMin));
    }
    private double yPhysicalToyUser(Rectangle2D.Double r,double yPhysical,double yMin,double yMax){
        return (r.y+(yMax-yPhysical)*(r.height-1)/(yMax-yMin));
    }
}
