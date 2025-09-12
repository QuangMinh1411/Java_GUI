import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * BarCharPanel is a lightweight Swing component that draws a simple bar chart
 * inside a given border rectangle. It maps an array of values (y) into bars,
 * using a horizontal baseline b (which can be anywhere between min and max),
 * and fills bars with a given color.
 *
 * Key ideas:
 * - borderRectangle: The drawing area for the chart (in component pixels).
 * - n: Number of bars to draw (uses the first n elements of y).
 * - y: Data values to visualize.
 * - b: Baseline value in data space. Bars above b extend upward; below b downward.
 * - c: Fill color for the bars.
 *
 * The coordinate helpers (xPhysicalToxUser, yPhysicalToyUser) convert data-space
 * values into pixel positions within the border rectangle. Only the y mapping is
 * used here because bars are laid out at uniform x positions.
 */
public class BarCharPanel extends JPanel {
    private Rectangle2D.Double borderRectangle; // Chart drawing bounds in component coordinates
    private int n;                               // Number of bars (uses first n entries of y)
    private double[] y;                          // Data values in "physical" (data) space
    private double b;                            // Baseline value (in data space) for the bars
    private Color c;                             // Bar fill color

    public BarCharPanel(){}

    public BarCharPanel(Rectangle2D.Double borderRectangle, int n, double[] y, double b, Color c){
        this.borderRectangle = borderRectangle;
        this.n = n;
        this.y = y;
        this.b = b;
        this.c = c;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        // Basic sanity checks. If missing data or bounds, nothing to draw.
        if (y == null || n <= 0 || borderRectangle == null) return;

        // Find the data range [yMin, yMax] among the first n values.
        double yMin = y[0];
        double yMax = y[0];
        for(int i = 1; i < n; i++){
            if(y[i] < yMin) yMin = y[i];
            if(y[i] > yMax) yMax = y[i];
        }

        // Create a fresh Graphics2D context so we can set hints safely and always dispose.
        Graphics2D g2D = (Graphics2D) g.create();
        try {
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Compute the bar width with gaps: pattern is [gap, bar, gap, bar, ...].
            // Formula 2*(W-1)/(3*n+1) yields: one initial half-gap, then repeating [bar + full gap], ending neatly.
            double barWidth = 2 * (borderRectangle.width - 1) / (3 * n + 1);

            // Convert the baseline b from data space to pixel Y within the border.
            double clientBase = yPhysicalToyUser(borderRectangle, b, yMin, yMax);

            Rectangle2D.Double myRectangle;
            for(int i = 0; i < n; i++){
                // Left X position for the i-th bar: 0.5 barWidth initial gap, then 1.5*barWidth per bar (bar + gap).
                double leftX = borderRectangle.x + (1.5 * i + 0.5) * barWidth;

                if(y[i] > b){
                    // Bar extends upward from baseline to the mapped y[i].
                    double topY = yPhysicalToyUser(borderRectangle, y[i], yMin, yMax);
                    myRectangle = new Rectangle2D.Double(
                            leftX,
                            topY,
                            barWidth,
                            clientBase - topY
                    );
                } else {
                    // Bar extends downward from baseline to the mapped y[i].
                    double valueY = yPhysicalToyUser(borderRectangle, y[i], yMin, yMax);
                    myRectangle = new Rectangle2D.Double(
                            leftX,
                            clientBase,
                            barWidth,
                            valueY - clientBase
                    );
                }

                // Paint the bar.
                g2D.setPaint(c);
                g2D.fill(myRectangle);
            }

            // Draw the border rectangle and the horizontal baseline.
            g2D.setPaint(Color.BLACK);
            g2D.draw(borderRectangle);
            g2D.draw(new Line2D.Double(
                    borderRectangle.x,
                    clientBase,
                    borderRectangle.x + borderRectangle.width - 1,
                    clientBase
            ));
        } finally {
            // Always dispose the temporary Graphics2D to free resources.
            g2D.dispose();
        }
    }

    // Map an x value in data space to pixel X within rectangle r.
    private double xPhysicalToxUser(Rectangle2D.Double r, double xPhysical, double xMin, double xMax){
        return (r.x + (xPhysical - xMin) * (r.width - 1) / (xMax - xMin));
    }

    // Map a y value in data space to pixel Y within rectangle r (inverted because screen Y grows downward).
    private double yPhysicalToyUser(Rectangle2D.Double r, double yPhysical, double yMin, double yMax){
        return (r.y + (yMax - yPhysical) * (r.height - 1) / (yMax - yMin));
    }
}
