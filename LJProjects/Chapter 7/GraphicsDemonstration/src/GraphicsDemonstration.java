import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;

public class GraphicsDemonstration extends JFrame {
    private final DrawingPanel drawingPanel = new DrawingPanel();
    private final JButton toggleButton = new JButton("Draw Line");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GraphicsDemonstration().setVisible(true);
        });
    }
    public GraphicsDemonstration() {
        super("Graphics Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        drawingPanel.setPreferredSize(new Dimension(300, 200));
        drawingPanel.setBackground(Color.WHITE);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(drawingPanel, gridConstraints);
        toggleButton.setText("Draw Line");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(0, 0, 10, 0);
        getContentPane().add(toggleButton, gridConstraints);
        toggleButton.addActionListener(this::toggleButtonActionPerformed);
        pack();
        setLocationRelativeTo(null);
    }
    private void toggleButtonActionPerformed(ActionEvent e) {
        boolean next = !drawingPanel.isLineVisible();
        drawingPanel.setLineVisible(next);
        toggleButton.setText(next ? "Clear Line" : "Draw Line");
        drawingPanel.repaint();
    }
    private static final class DrawingPanel extends JPanel {
        private boolean lineVisible = false;
        boolean isLineVisible() {
            return lineVisible;
        }
        void setLineVisible(boolean visible) {
            this.lineVisible = visible;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!lineVisible) return;
            Graphics2D g2 = (Graphics2D) g.create();
            try {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(Color.BLUE);
                Line2D line = new Line2D.Double(0, 0, getWidth(), getHeight());
                g2.draw(line);
            } finally {
                g2.dispose();
            }
        }
    }
}
