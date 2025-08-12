import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class RGBControl extends JFrame {
    private final JLabel colorLabel = new JLabel("Color Preview", SwingConstants.CENTER);
    private final JSlider redSlider = createColorSlider();
    private final JSlider greenSlider = createColorSlider();
    private final JSlider blueSlider = createColorSlider();

    public RGBControl() {
        setTitle("RGB Control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        // Preview label setup
        colorLabel.setOpaque(true); // Needed to show background color
        colorLabel.setPreferredSize(new Dimension(240, 120));
        colorLabel.setFont(colorLabel.getFont().deriveFont(Font.BOLD, 16f));

        // Initial slider values
        redSlider.setValue(128);
        greenSlider.setValue(128);
        blueSlider.setValue(128);

        // Shared change listener updates the preview background
        ChangeListener updateColorListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updatePreviewColor();
            }
        };
        redSlider.addChangeListener(updateColorListener);
        greenSlider.addChangeListener(updateColorListener);
        blueSlider.addChangeListener(updateColorListener);

        // Layout components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.BOTH;
        getContentPane().add(colorLabel, gbc);

        gbc.gridwidth = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1; gbc.gridx = 0; getContentPane().add(new JLabel("R:"), gbc);
        gbc.gridx = 1; getContentPane().add(redSlider, gbc);

        gbc.gridy = 2; gbc.gridx = 0; getContentPane().add(new JLabel("G:"), gbc);
        gbc.gridx = 1; getContentPane().add(greenSlider, gbc);

        gbc.gridy = 3; gbc.gridx = 0; getContentPane().add(new JLabel("B:"), gbc);
        gbc.gridx = 1; getContentPane().add(blueSlider, gbc);

        // Initialize preview color
        updatePreviewColor();

        pack();
        setLocationRelativeTo(null); // center on screen
    }

    private static JSlider createColorSlider() {
        JSlider slider = new JSlider(0, 255, 0);
        slider.setMajorTickSpacing(85);
        slider.setMinorTickSpacing(17);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    private void updatePreviewColor() {
        int r = redSlider.getValue();
        int g = greenSlider.getValue();
        int b = blueSlider.getValue();
        Color c = new Color(r, g, b);
        colorLabel.setBackground(c);
        colorLabel.setForeground(getContrastingColor(c));
        colorLabel.setText(String.format("RGB(%d, %d, %d)", r, g, b));
    }

    // Ensures text is readable on the chosen background
    private static Color getContrastingColor(Color c) {
        // Perceived luminance (sRGB luma)
        double luminance = (0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue()) / 255.0;
        return luminance > 0.5 ? Color.BLACK : Color.WHITE;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RGBControl frame = new RGBControl();
            frame.setVisible(true);
        });
    }
}
