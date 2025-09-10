import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class GradientPainting extends JFrame {
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
        point1Label.setPreferredSize(new Dimension(120, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(point1Label, gbc);

        color1ComboBox.setBackground(Color.WHITE);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 5, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(color1ComboBox, gbc);
        color1ComboBox.addActionListener(e -> updateGradientIfActive());

        point2Label.setPreferredSize(new Dimension(120, 20));
        point2Label.setHorizontalAlignment(SwingConstants.CENTER);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 5, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(point2Label, gbc);

        color2ComboBox.setBackground(Color.WHITE);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 5, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(color2ComboBox, gbc);
        color2ComboBox.addActionListener(e -> updateGradientIfActive());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 5, 0, 0);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(cyclicCheckBox, gbc);
        cyclicCheckBox.addActionListener(e -> updateGradientIfActive());

        paintButton.setEnabled(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 5, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(paintButton, gbc);
        paintButton.addActionListener(e -> applyGradient());

        // Paint panel
        paintPanel.setPreferredSize(new Dimension(350, 250));
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
        color2ComboBox.setSelectedIndex(6); // White

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
        return String.format("%s: (%.0f,%.0f)", prefix, p.getX(), p.getY());
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
        private Point2D p1;
        private Point2D p2;
        private Color c1 = Color.BLACK;
        private Color c2 = Color.WHITE;
        private boolean cyclic = false;
        private boolean gradientActive = false;

        public Point2D getFirstPoint() { return p1; }
        public Point2D getSecondPoint() { return p2; }
        public void setFirstPoint(Point2D p) { this.p1 = p; }
        public void setSecondPoint(Point2D p) { this.p2 = p; }
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
                    GradientPaint gp = new GradientPaint(p1, c1, p2, c2, cyclic);
                    g2.setPaint(gp);
                    g2.fillRect(0, 0, getWidth(), getHeight());
                    return;
                }

                // Draw selection guides (points and line)
                if (p1 != null) {
                    g2.setPaint(Color.RED);
                    g2.fill(new Ellipse2D.Double(p1.getX() - 1, p1.getY() - 1, 3, 3));
                }
                if (p2 != null) {
                    g2.setPaint(Color.RED);
                    g2.fill(new Ellipse2D.Double(p2.getX() - 1, p2.getY() - 1, 3, 3));
                    g2.setPaint(Color.BLACK);
                    g2.draw(new Line2D.Double(p1, p2));
                }
            } finally {
                g2.dispose();
            }
        }
    }
}
