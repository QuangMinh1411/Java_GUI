import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class GradientPainting extends JFrame {
    private static final long serialVersionUID = 1L;

    // UI constants
    private static final int LABEL_WIDTH = 120;
    private static final int LABEL_HEIGHT = 20;
    private static final int PAINT_PANEL_WIDTH = 350;
    private static final int PAINT_PANEL_HEIGHT = 250;
    private static final int GRID_INSET_TOP = 10;
    private static final int GRID_INSET_LEFT = 5;
    private static final int GRID_INSET_RIGHT = 0;
    private static final int GRID_INSET_BOTTOM_DEFAULT = 0;
    private static final int GRID_INSET_BOTTOM_BUTTON = 10;

    // Selection marker constants
    private static final int MARKER_SIZE = 3;
    private static final int MARKER_OFFSET = 1; // half of size for centering

    // Defaults
    private static final int DEFAULT_COLOR1_INDEX = 0; // Black
    private static final int DEFAULT_COLOR2_INDEX = 6; // White

    // Numeric stability
    private static final double POINT_EPSILON = 1e-6;

    private final JLabel point1Label = new JLabel("Point 1: (xxx,xxx)");
    private final JLabel point2Label = new JLabel("Point 2: (xxx,xxx)");

    private final JComboBox<ColorItem> color1ComboBox = new JComboBox<>();
    private final JComboBox<ColorItem> color2ComboBox = new JComboBox<>();
    private final JCheckBox cyclicCheckBox = new JCheckBox("Cyclic");
    private final JButton paintButton = new JButton("Paint");

    private final GradientPanel paintPanel = new GradientPanel();

    private boolean loading = true;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GradientPainting().setVisible(true));
    }

    public GradientPainting() {
        setTitle("Gradient Paint");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        // Left controls
        point1Label.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        point1Label.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(GRID_INSET_TOP, GRID_INSET_LEFT, GRID_INSET_BOTTOM_DEFAULT, GRID_INSET_RIGHT);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(point1Label, gbc);

        color1ComboBox.setBackground(Color.WHITE);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(GRID_INSET_TOP, GRID_INSET_LEFT, GRID_INSET_BOTTOM_DEFAULT, GRID_INSET_RIGHT);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(color1ComboBox, gbc);
        color1ComboBox.addActionListener(e -> updateGradientIfActive());

        point2Label.setPreferredSize(new Dimension(LABEL_WIDTH, LABEL_HEIGHT));
        point2Label.setHorizontalAlignment(SwingConstants.LEFT);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(GRID_INSET_TOP, GRID_INSET_LEFT, GRID_INSET_BOTTOM_DEFAULT, GRID_INSET_RIGHT);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(point2Label, gbc);

        color2ComboBox.setBackground(Color.WHITE);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(GRID_INSET_TOP, GRID_INSET_LEFT, GRID_INSET_BOTTOM_DEFAULT, GRID_INSET_RIGHT);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(color2ComboBox, gbc);
        color2ComboBox.addActionListener(e -> updateGradientIfActive());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(GRID_INSET_TOP, GRID_INSET_LEFT, GRID_INSET_BOTTOM_DEFAULT, GRID_INSET_RIGHT);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(cyclicCheckBox, gbc);
        cyclicCheckBox.addActionListener(e -> updateGradientIfActive());

        paintButton.setEnabled(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(GRID_INSET_TOP, GRID_INSET_LEFT, GRID_INSET_BOTTOM_BUTTON, GRID_INSET_RIGHT);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(paintButton, gbc);
        paintButton.addActionListener(e -> applyGradient());

        // Paint panel
        paintPanel.setPreferredSize(new Dimension(PAINT_PANEL_WIDTH, PAINT_PANEL_HEIGHT));
        paintPanel.setBackground(Color.WHITE);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 6;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        getContentPane().add(paintPanel, gbc);

        paintPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handlePaintPanelClick(e);
            }
        });

        // Populate colors
        ColorItem[] items = new ColorItem[] {
                new ColorItem("Black", Color.BLACK),
                new ColorItem("Blue", Color.BLUE),
                new ColorItem("Green", Color.GREEN),
                new ColorItem("Red", Color.RED),
                new ColorItem("Yellow", Color.YELLOW),
                new ColorItem("Magenta", Color.MAGENTA),
                new ColorItem("White", Color.WHITE),
                new ColorItem("Cyan", Color.CYAN)
        };
        for (ColorItem item : items) {
            color1ComboBox.addItem(item);
            color2ComboBox.addItem(item);
        }
        // Defaults: keep same as original intent
        color1ComboBox.setSelectedIndex(DEFAULT_COLOR1_INDEX); // Black
        color2ComboBox.setSelectedIndex(DEFAULT_COLOR2_INDEX); // White

        pack();
        setLocationRelativeTo(null);
        loading = false;
    }

    private void handlePaintPanelClick(MouseEvent e) {
        Point2D click = e.getPoint();

        // Start new selection when first point is null or both points already set
        if (paintPanel.getFirstPoint() == null || paintPanel.getSecondPoint() != null) {
            paintPanel.setFirstPoint(click);
            paintPanel.setSecondPoint(null);
            point1Label.setText(formatPointLabel("Point 1", click));
            point2Label.setText("Point 2: (xxx,xxx)");
            paintButton.setEnabled(false);
            paintPanel.setGradientActive(false);
        } else { // set second point
            paintPanel.setSecondPoint(click);
            point2Label.setText(formatPointLabel("Point 2", click));
            paintButton.setEnabled(true);
            paintPanel.setGradientActive(false);
        }
        paintPanel.repaint();
    }

    private void applyGradient() {
        if (loading) return;
        if (paintPanel.getFirstPoint() == null || paintPanel.getSecondPoint() == null) return;

        Color c1 = getSelectedColor(color1ComboBox);
        Color c2 = getSelectedColor(color2ComboBox);
        boolean cyclic = cyclicCheckBox.isSelected();

        paintPanel.setColors(c1, c2);
        paintPanel.setCyclic(cyclic);
        paintPanel.setGradientActive(true);
        paintPanel.repaint();
    }

    private void updateGradientIfActive() {
        if (!loading && paintPanel.isGradientActive()) {
            applyGradient();
        }
    }

    private static Color getSelectedColor(JComboBox<ColorItem> combo) {
        ColorItem item = (ColorItem) combo.getSelectedItem();
        return item != null ? item.color : Color.BLACK;
    }

    private static String formatPointLabel(String prefix, Point2D p) {
        int x = (int) Math.round(p.getX());
        int y = (int) Math.round(p.getY());
        return String.format("%s: (%d,%d)", prefix, x, y);
    }

    private static final class ColorItem {
        final String name;
        final Color color;
        ColorItem(String name, Color color) {
            this.name = name;
            this.color = color;
        }
        @Override
        public String toString() {
            return name;
        }
    }

    private static final class GradientPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        private Point2D p1;
        private Point2D p2;
        private Color c1 = Color.BLACK;
        private Color c2 = Color.WHITE;
        private boolean cyclic = false;
        private boolean gradientActive = false;

        public Point2D getFirstPoint() { return p1; }
        public Point2D getSecondPoint() { return p2; }
        public void setFirstPoint(Point2D p) { this.p1 = (p != null) ? new Point2D.Double(p.getX(), p.getY()) : null; }
        public void setSecondPoint(Point2D p) { this.p2 = (p != null) ? new Point2D.Double(p.getX(), p.getY()) : null; }
        public void setColors(Color c1, Color c2) { this.c1 = c1; this.c2 = c2; }
        public void setCyclic(boolean cyclic) { this.cyclic = cyclic; }
        public boolean isGradientActive() { return gradientActive; }
        public void setGradientActive(boolean active) { this.gradientActive = active; }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            try {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (gradientActive && p1 != null && p2 != null) {
                    // Avoid degenerate gradient when points are identical
                    Point2D lp1 = p1;
                    Point2D lp2 = p2;
                    if (lp1.distance(lp2) < POINT_EPSILON) {
                        lp2 = new Point2D.Double(lp2.getX() + 1, lp2.getY());
                    }
                    GradientPaint gp = new GradientPaint(lp1, c1, lp2, c2, cyclic);
                    g2.setPaint(gp);
                    g2.fillRect(0, 0, getWidth(), getHeight());
                    return;
                }

                // Draw selection guides (points and line)
                if (p1 != null) {
                    g2.setPaint(Color.RED);
                    g2.fill(new Ellipse2D.Double(p1.getX() - MARKER_OFFSET, p1.getY() - MARKER_OFFSET, MARKER_SIZE, MARKER_SIZE));
                }
                if (p2 != null) {
                    g2.setPaint(Color.RED);
                    g2.fill(new Ellipse2D.Double(p2.getX() - MARKER_OFFSET, p2.getY() - MARKER_OFFSET, MARKER_SIZE, MARKER_SIZE));
                }
                if (p1 != null && p2 != null) {
                    g2.setPaint(Color.BLACK);
                    g2.draw(new Line2D.Double(p1, p2));
                }
            } finally {
                g2.dispose();
            }
        }
    }
}