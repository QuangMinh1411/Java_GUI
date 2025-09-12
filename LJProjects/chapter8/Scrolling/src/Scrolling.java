/**
 * Scrolling.java
 *
 * A small Swing application that animates a horizontally scrolling background image
 * inside a fixed-size panel. The scroll speed is controlled by a horizontal
 * scrollbar. A Swing Timer advances the animation at a fixed interval and the
 * panel repaints itself using proper Swing painting conventions.
 *
 * Structure
 * - Scrolling (JFrame): Creates the UI, consisting of an AnimationPanel in the
 *   center and a horizontal JScrollBar at the bottom. The scrollbar value is
 *   interpreted as the number of pixels to scroll per timer tick.
 * - Timer (50 ms): On each tick, calls AnimationPanel.tick() to advance the
 *   scroll offset and request a repaint.
 * - AnimationPanel (JPanel): Encapsulates state (scrollX, speed) and all rendering.
 *   paintComponent draws a size x size subsection of the source image starting at
 *   the current scroll offset. When the subsection would cross the image's right
 *   edge, it splits the draw into two passes (right remainder, then left start)
 *   to produce a seamless horizontal wrap.
 *
 * Notes
 * - Uses SwingUtilities.invokeLater to start on the Event Dispatch Thread (EDT).
 * - Avoids using getGraphics(); instead overrides paintComponent and calls repaint().
 * - Double-buffered panel to minimize flicker.
 * - BorderLayout is used for a simple layout: CENTER = panel, SOUTH = scrollbar.
 * - Image path: "src/sea.gif". The image is logically tiled horizontally via wrapping.
 */
import javax.swing.*;
import java.awt.*;

public class Scrolling extends JFrame {
    private static final long serialVersionUID = 1L;

    private final int imageSize = 130;
    private final JScrollBar backgroundScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
    private final Timer scrollTimer;
    private final AnimationPanel displayPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Scrolling().setVisible(true));
    }

    public Scrolling() {
        super("Scrolling");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.BLUE);
        getContentPane().setLayout(new BorderLayout());

        // Drawing panel
        displayPanel = new AnimationPanel("src/sea.gif", imageSize);
        displayPanel.setPreferredSize(new Dimension(imageSize, imageSize));
        displayPanel.setBackground(Color.BLACK);

        // Scrollbar controls the scroll speed (pixels per tick)
        backgroundScrollBar.setPreferredSize(new Dimension(imageSize, 14));
        backgroundScrollBar.setMinimum(0);
        backgroundScrollBar.setMaximum(20 + backgroundScrollBar.getVisibleAmount());
        backgroundScrollBar.setBlockIncrement(2);
        backgroundScrollBar.setUnitIncrement(1);
        backgroundScrollBar.setValue(0);
        backgroundScrollBar.addAdjustmentListener(e -> displayPanel.setSpeed(e.getValue()));

        // Layout
        getContentPane().add(displayPanel, BorderLayout.CENTER);
        getContentPane().add(backgroundScrollBar, BorderLayout.SOUTH);

        // Timer drives the animation
        scrollTimer = new Timer(50, e -> displayPanel.tick());

        pack();
        setLocationRelativeTo(null);
        scrollTimer.start();
    }

    /**
     * Panel responsible for rendering the scrolling image using proper Swing painting.
     */
    private static class AnimationPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        private final Image backgroundImage;
        private final int imageSize;
        private int scrollX = 0;  // current x offset in the source image
        private int speed = 0;    // pixels per timer tick

        AnimationPanel(String imagePath, int imageSize) {
            this.imageSize = imageSize;
            this.backgroundImage = new ImageIcon(imagePath).getImage();
            setDoubleBuffered(true);
            setOpaque(true);
        }

        void setSpeed(int speed) {
            this.speed = Math.max(0, speed);
        }

        void tick() {
            int imgW = backgroundImage.getWidth(this);
            if (imgW <= 0) {
                repaint();
                return;
            }
            scrollX = (scrollX + speed) % imgW;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int imgW = backgroundImage.getWidth(this);
            int imgH = backgroundImage.getHeight(this);
            if (imgW <= 0 || imgH <= 0) {
                // Image not available yet
                return;
            }

            int size = this.imageSize;
            int x = scrollX % imgW;

            // When near the right edge, draw two segments to wrap horizontally
            if (x > imgW - size) {
                int firstWidth = imgW - x;
                // right segment of the source image -> left of the panel
                g.drawImage(backgroundImage, 0, 0, firstWidth, size,
                        x, 0, x + firstWidth, size, this);
                // left segment of the source image -> remainder of the panel
                g.drawImage(backgroundImage, firstWidth, 0, size, size,
                        0, 0, size - firstWidth, size, this);
            } else {
                // single blit when not wrapping
                g.drawImage(backgroundImage, 0, 0, size, size,
                        x, 0, x + size, size, this);
            }
        }
    }
}
