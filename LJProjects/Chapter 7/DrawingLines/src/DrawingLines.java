/*
 Summary:
 This Swing app draws connected line segments between randomly generated points inside a panel.
 Clicking "Draw Lines" adds a couple of random points and repaints the panel; "Clear Lines" removes them.

 Refactor highlights:
 - Replaced static mutable arrays and counters with an instance List<Point>. This confines state to the frame instance
   and removes brittle static coupling with the panel.
 - Fixed the point-adding logic. The original do-while ensured there were only ever 2 total points. Now each click
   adds up to two new points until a configurable max (50), then disables the draw button.
 - Simplified painting: no storing of a Line2D field, no explicit Graphics disposal in paintComponent (Swing handles it).
 - Minor cleanups: concise action listeners, clearer field initialization, and safer iteration.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingLines extends JFrame {
    private final GraphicsPanel drawPanel = new GraphicsPanel();
    private final JButton drawButton = new JButton("Draw Lines");
    private final JButton clearButton = new JButton("Clear Lines");

    private final int maxPoints = 50;
    private final List<Point> points = new ArrayList<>(maxPoints);
    private final Random random = new Random();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawingLines().setVisible(true));
    }

    public DrawingLines() {
        setTitle("Drawing Lines");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        drawPanel.setPreferredSize(new Dimension(300, 200));
        drawPanel.setBackground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(drawPanel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        getContentPane().add(drawButton, gbc);
        drawButton.addActionListener(this::drawButtonActionPerformed);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 5, 0);
        getContentPane().add(clearButton, gbc);
        clearButton.addActionListener(e -> clearButtonActionPerformed());

        pack();
        setLocationRelativeTo(null);
    }

    private void clearButtonActionPerformed() {
        points.clear();
        drawButton.setEnabled(true);
        drawPanel.repaint();
    }

    private void drawButtonActionPerformed(ActionEvent e) {
        // Add up to two new random points per click, until the maximum is reached
        for (int i = 0; i < 2 && points.size() < maxPoints; i++) {
            int px = random.nextInt(Math.max(1, drawPanel.getWidth()));
            int py = random.nextInt(Math.max(1, drawPanel.getHeight()));
            points.add(new Point(px, py));
        }
        drawPanel.repaint();
        if (points.size() >= maxPoints) {
            drawButton.setEnabled(false);
        }
    }

    private final class GraphicsPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            try {
                g2.setPaint(Color.BLUE);
                g2.setStroke(new BasicStroke(3));
                for (int i = 1; i < points.size(); i++) {
                    Point p0 = points.get(i - 1);
                    Point p1 = points.get(i);
                    g2.draw(new Line2D.Double(p0, p1));
                }
            } finally {
                g2.dispose(); // dispose the copy created with g.create()
            }
        }
    }
}
