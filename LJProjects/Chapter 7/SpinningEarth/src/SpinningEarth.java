import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SpinningEarth extends JFrame {
    private static final int FRAME_DELAY_MS = 1000;

    private final JLabel displayLabel = new JLabel();
    private final JButton earthButton = new JButton();

    private final ImageIcon[] frames = new ImageIcon[]{
            new ImageIcon("src/image/earth0.gif"),
            new ImageIcon("src/image/earth1.gif"),
            new ImageIcon("src/image/earth2.gif"),
            new ImageIcon("src/image/earth3.gif"),
            new ImageIcon("src/image/earth4.gif"),
            new ImageIcon("src/image/earth5.gif")
    };

    private final Timer earthTimer;
    private int pictureNumber = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SpinningEarth().setVisible(true));
    }

    public SpinningEarth(){
        setTitle("Spinning Earth");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();

        // Initialize label with the first frame size and icon
        displayLabel.setPreferredSize(new Dimension(frames[0].getIconWidth(), frames[0].getIconHeight()));
        displayLabel.setIcon(frames[0]);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(displayLabel, gridConstraints);

        gridConstraints = new GridBagConstraints();
        earthButton.setText("Start Spinning");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(earthButton, gridConstraints);

        earthButton.addActionListener(e -> earthButtonActionPerformed(e));

        earthTimer = new Timer(FRAME_DELAY_MS, e -> earthTimerActionPerformed(e));

        pack();
        setLocationRelativeTo(null);
    }

    private void earthTimerActionPerformed(ActionEvent e) {
        displayLabel.setIcon(frames[pictureNumber]);
        pictureNumber = (pictureNumber + 1) % frames.length;
    }

    private void earthButtonActionPerformed(ActionEvent e) {
        if(earthTimer.isRunning()){
            earthTimer.stop();
            earthButton.setText("Start Spinning");
        } else {
            earthTimer.start();
            earthButton.setText("Stop Spinning");
        }
    }
}
