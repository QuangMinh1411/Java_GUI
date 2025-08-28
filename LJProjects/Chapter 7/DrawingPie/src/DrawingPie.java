import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * Refactored DrawingPie
 *
 * What changed and why (high level):
 * - Removed static UI state (ellipse, slices, colors, isDrawn) and made them instance fields.
 *   This keeps the state encapsulated in the window and avoids accidental cross‑instance sharing
 *   and NullPointerExceptions from uninitialized static fields.
 * - Always initialize the pie geometry and data when the user clicks Draw.
 *   We compute: the bounding ellipse (centered in the panel with a margin), the number of slices,
 *   the extents in degrees that sum exactly to 360, and a distinct color per slice.
 * - Rewrote slice generation: we use random positive weights, normalize them to 360 degrees,
 *   round to integer degrees, and then fix the final slice to ensure the total is exactly 360.
 *   This produces a clean partition with no gaps/overflows.
 * - Simplified event listeners (lambdas) and toggled buttons reliably.
 * - Improved painting: added anti‑aliasing and removed reliance on static members.
 *
 * How it works (flow):
 * 1) User clicks "Draw/Fill Pie" -> we compute the pie bounds inside the drawing panel and
 *    generate random slices (2..6) with colors -> we set isDrawn=true and repaint.
 * 2) paintComponent reads the prepared data and draws filled Arc2D.PIE segments around the ellipse,
 *    walking the start angle around the circle.
 * 3) "Clear Pie" resets the flag and repaints the empty panel; buttons are toggled accordingly.
 */
public class DrawingPie extends JFrame {
    private final GraphicsPanel drawPanel = new GraphicsPanel();
    private final JButton drawButton = new JButton();
    private final JButton clearButton = new JButton();

    // Instance state for the pie to draw
    private Ellipse2D.Double pieBounds;   // Bounding ellipse for the pie, computed on draw
    private int sliceCount;               // Number of slices in the current pie (2..6)
    private double[] sliceExtents;        // Angles in degrees for each slice; sums to 360
    private Color[] sliceColors;          // Fill color per slice
    private boolean isDrawn = false;      // Whether we have a pie to paint

    private final Random rnd = new Random();

    public DrawingPie(){
        setTitle("Drawing Pie Segments");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gridConstraints = new GridBagConstraints();
        drawPanel.setPreferredSize(new Dimension(250, 250));
        drawPanel.setBackground(Color.WHITE);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(drawPanel, gridConstraints);

        drawButton.setText("Draw/Fill Pie");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        getContentPane().add(drawButton, gridConstraints);
        drawButton.addActionListener(this::drawButtonActionPerformed);

        clearButton.setText("Clear Pie");
        clearButton.setEnabled(false);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(5, 0, 5, 0);
        getContentPane().add(clearButton, gridConstraints);
        clearButton.addActionListener(this::clearButtonActionPerformed);

        pack();
        setLocationRelativeTo(null);
    }

    private void clearButtonActionPerformed(ActionEvent e) {
        isDrawn = false;
        drawButton.setEnabled(true);
        clearButton.setEnabled(false);
        drawPanel.repaint();
    }

    private void drawButtonActionPerformed(ActionEvent e) {
        // 1) Decide slice count between 2 and 6 inclusive
        sliceCount = rnd.nextInt(5) + 2; // 2..6

        // 2) Generate positive random weights and convert to degrees summing exactly to 360
        double[] weights = new double[sliceCount];
        double sum = 0.0;
        for (int i = 0; i < sliceCount; i++) {
            // Random weight in (0,1]; ensure > 0 to avoid zero-degree slices
            weights[i] = 0.1 + rnd.nextDouble();
            sum += weights[i];
        }
        sliceExtents = new double[sliceCount];
        int total = 0;
        for (int i = 0; i < sliceCount - 1; i++) {
            int deg = (int) Math.max(1, Math.round(weights[i] / sum * 360.0));
            sliceExtents[i] = deg;
            total += deg;
        }
        // Last slice gets the remainder to make the sum exactly 360
        sliceExtents[sliceCount - 1] = Math.max(1, 360 - total);

        // 3) Generate a distinct color for each slice using HSB
        sliceColors = new Color[sliceCount];
        for (int i = 0; i < sliceCount; i++) {
            float hue = (float) i / (float) sliceCount; // evenly spaced hues
            sliceColors[i] = Color.getHSBColor(hue, 0.60f, 0.95f);
        }

        // 4) Compute the bounding ellipse inside the panel (centered with margin)
        Insets insets = drawPanel.getInsets();
        int w = drawPanel.getWidth() - insets.left - insets.right;
        int h = drawPanel.getHeight() - insets.top - insets.bottom;
        int margin = 10; // px margin inside the panel
        int size = Math.max(0, Math.min(w, h) - 2 * margin);
        int x = insets.left + (w - size) / 2;
        int y = insets.top + (h - size) / 2;
        pieBounds = new Ellipse2D.Double(x, y, size, size);

        isDrawn = true;
        drawButton.setEnabled(false);
        clearButton.setEnabled(true);
        drawPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawingPie().setVisible(true));
    }

    private final class GraphicsPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            try {
                // Improve rendering quality
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (isDrawn && pieBounds != null && sliceExtents != null && sliceColors != null) {
                    double startAngle = 0.0;
                    for (int i = 0; i < sliceCount; i++) {
                        Arc2D.Double arc = new Arc2D.Double(
                                pieBounds.x, pieBounds.y, pieBounds.width, pieBounds.height,
                                startAngle, sliceExtents[i], Arc2D.PIE);
                        g2.setPaint(sliceColors[i]);
                        g2.fill(arc);
                        g2.setPaint(Color.DARK_GRAY);
                        g2.draw(arc);
                        startAngle += sliceExtents[i];
                    }
                    // Outline the whole pie for clarity
                    g2.setPaint(Color.DARK_GRAY);
                    g2.draw(pieBounds);
                }
            } finally {
                g2.dispose();
            }
        }
    }
}
