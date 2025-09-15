import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Collision extends JFrame {
    public Collision() {
        super("No Collision");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        DrawPanel displayPanel = new DrawPanel();
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        add(displayPanel, gridConstraints);

        pack();
        setLocationRelativeTo(null);

        SwingUtilities.invokeLater(displayPanel::requestFocusInWindow);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Collision().setVisible(true));
    }

    private static class DrawPanel extends JPanel {
        private static final double STEP = 5.0;
        private final Rectangle2D.Double rect1 = new Rectangle2D.Double(10, 10, 80, 40);
        private final Rectangle2D.Double rect2 = new Rectangle2D.Double(150, 150, 100, 100);

        DrawPanel() {
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(400, 400));
            setFocusable(true);
            setupKeyBindings();
        }

        private void setupKeyBindings() {
            InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = getActionMap();

            im.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
            im.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
            im.put(KeyStroke.getKeyStroke("UP"), "moveUp");
            im.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");

            am.put("moveLeft", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) { move(-STEP, 0); }
            });
            am.put("moveRight", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) { move(STEP, 0); }
            });
            am.put("moveUp", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) { move(0, -STEP); }
            });
            am.put("moveDown", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) { move(0, STEP); }
            });
        }

        private void move(double dx, double dy) {
            double newX = rect1.getX() + dx;
            double newY = rect1.getY() + dy;

            double maxX = Math.max(0, getWidth() - rect1.getWidth());
            double maxY = Math.max(0, getHeight() - rect1.getHeight());
            newX = Math.max(0, Math.min(newX, maxX));
            newY = Math.max(0, Math.min(newY, maxY));

            rect1.setRect(newX, newY, rect1.getWidth(), rect1.getHeight());
            updateTitle();
            repaint();
        }

        private void updateTitle() {
            Rectangle2D inter = rect1.createIntersection(rect2);
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window instanceof JFrame frame) {
                if (inter.isEmpty()) {
                    frame.setTitle("No Collision");
                } else {
                    double overlap = 100.0 * inter.getWidth() * inter.getHeight()
                            / (rect1.getWidth() * rect1.getHeight());
                    frame.setTitle(String.format("%.2f%% Collision", overlap));
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            try {
                g2.setPaint(Color.RED);
                g2.fill(rect2);

                g2.setPaint(Color.BLUE);
                g2.fill(rect1);
            } finally {
                g2.dispose();
            }
        }
    }
}
