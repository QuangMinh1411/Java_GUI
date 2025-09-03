import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class LineChartPanel extends JPanel {
    private Rectangle2D.Double borderRectangle;
    private int n;
    private double[] x;
    private double[] y;
    private Color c;

    public LineChartPanel() {
        // Provide sensible defaults
        this.borderRectangle = new Rectangle2D.Double(10, 10, 200, 120);
        this.c = Color.BLUE;
    }

    public LineChartPanel(Rectangle2D.Double border, int nPoints,
                          double[] xValues, double[] yValues, Color colorValue) {
        this.borderRectangle = border;
        this.n = nPoints;
        this.x = xValues;
        this.y = yValues;
        this.c = (colorValue != null ? colorValue : Color.BLUE);
    }

    // Optional convenience: set or update data after construction
    public void setData(Rectangle2D.Double border, int nPoints,
                        double[] xValues, double[] yValues, Color colorValue) {
        this.borderRectangle = border;
        this.n = nPoints;
        this.x = xValues;
        this.y = yValues;
        this.c = (colorValue != null ? colorValue : Color.BLUE);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Basic validations
        if (x == null || y == null || borderRectangle == null || c == null) {
            return;
        }
        if (n <= 1) {
            return;
        }
        if (x.length < n || y.length < n) {
            return;
        }

        // Compute ranges
        double xMin = x[0];
        double xMax = x[0];
        double yMin = y[0];
        double yMax = y[0];
        for (int i = 1; i < n; i++) {
            xMin = Math.min(xMin, x[i]);
            xMax = Math.max(xMax, x[i]);
            yMin = Math.min(yMin, y[i]);
            yMax = Math.max(yMax, y[i]);
        }
        // Avoid zero ranges to prevent division by zero
        if (xMax == xMin) {
            xMax = xMin + 1.0;
        }
        if (yMax == yMin) {
            yMax = yMin + 1.0;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        try {
            // Improve rendering quality
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw polyline segments
            g2.setPaint(c);
            for (int i = 0; i < n - 1; i++) {
                double x1 = toScreenX(borderRectangle, x[i], xMin, xMax);
                double y1 = toScreenY(borderRectangle, y[i], yMin, yMax);
                double x2 = toScreenX(borderRectangle, x[i + 1], xMin, xMax);
                double y2 = toScreenY(borderRectangle, y[i + 1], yMin, yMax);
                Line2D.Double seg = new Line2D.Double(x1, y1, x2, y2);
                g2.draw(seg);
            }

            // Draw border last
            g2.setPaint(Color.BLACK);
            g2.draw(borderRectangle);
        } finally {
            // Dispose the copy, not the system-provided Graphics
            g2.dispose();
        }
    }

    private double toScreenX(Rectangle2D.Double r, double xVal, double xMin, double xMax) {
        return r.x + (xVal - xMin) * (r.width - 1) / (xMax - xMin);
    }

    private double toScreenY(Rectangle2D.Double r, double yVal, double yMin, double yMax) {
        // Flip Y so that larger data values appear higher up within the rectangle
        double t = (yVal - yMin) / (yMax - yMin);
        return r.y + (r.height - 1) - t * (r.height - 1);
    }
}
