import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class TimerExample extends JFrame {
    private final Timer beepTimer;
    private final JButton beepButton = new JButton("Start Beeping");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TimerExample().setVisible(true));
    }

    public TimerExample() {
        setTitle("Timer Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        add(beepButton, gridConstraints);

        beepButton.addActionListener(this::beepButtonActionPerformed);
        beepTimer = new Timer(1000, this::beepTimerActionPerformed);

        pack();
        setLocationRelativeTo(null);
    }

    private void beepTimerActionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().beep();
    }

    private void beepButtonActionPerformed(ActionEvent e) {
        if (beepTimer.isRunning()) {
            beepTimer.stop();
            beepButton.setText("Start Beeping");
        } else {
            beepTimer.start();
            beepButton.setText("Stop Beeping");
        }
    }
}
