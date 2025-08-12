import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class NumberGuess extends JFrame {

    private final JScrollBar numberScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
    private final JLabel instructionLabel = new JLabel("Guess the secret number (1-100)");
    private final JLabel rangeLabel = new JLabel("Range: 1 - 100");
    private final JLabel guessLabel = new JLabel("Current guess: 1");
    private final JLabel feedbackLabel = new JLabel(" ");
    private final JButton guessButton = new JButton("Guess");
    private final JButton newGameButton = new JButton("New Game");

    private final Random random = new Random();
    private int secret;
    private int minBound;
    private int maxBound;

    public NumberGuess() {
        setTitle("NumberGuess Problem");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        // Configure scroll bar
        numberScrollBar.setUnitIncrement(1);
        numberScrollBar.setBlockIncrement(5);
        numberScrollBar.setVisibleAmount(1); // extent; effective max = maximum - extent

        // Layout components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(instructionLabel, gbc);

        gbc.gridy = 1; gbc.gridwidth = 2;
        getContentPane().add(rangeLabel, gbc);

        gbc.gridy = 2; gbc.gridwidth = 2;
        getContentPane().add(numberScrollBar, gbc);

        gbc.gridy = 3; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        getContentPane().add(guessLabel, gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.EAST;
        getContentPane().add(guessButton, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        getContentPane().add(feedbackLabel, gbc);

        gbc.gridy = 5; gbc.gridwidth = 2;
        getContentPane().add(newGameButton, gbc);

        // Listeners
        numberScrollBar.addAdjustmentListener(e -> {
            int val = numberScrollBar.getValue();
            guessLabel.setText("Current guess: " + val);
        });

        guessButton.addActionListener(this::onGuess);
        newGameButton.addActionListener(e -> resetGame());

        resetGame();
        pack();
        setLocationRelativeTo(null);
    }

    private void onGuess(ActionEvent e) {
        int guess = numberScrollBar.getValue();
        if (guess == secret) {
            feedbackLabel.setText("Correct! The number was " + secret + ".");
            guessButton.setEnabled(false);
            numberScrollBar.setEnabled(false);
            return;
        }
        if (guess < secret) {
            // Increase minimum bound to help user adjust their guess upward
            minBound = Math.min(secret, Math.max(minBound, guess + 1));
            feedbackLabel.setText("Too low! Try higher.");
        } else { // guess > secret
            // Decrease maximum bound to help user adjust their guess downward
            maxBound = Math.max(secret, Math.min(maxBound, guess - 1));
            feedbackLabel.setText("Too high! Try lower.");
        }
        applyBoundsToScrollBar();
    }

    private void resetGame() {
        minBound = 1;
        maxBound = 100;
        secret = random.nextInt(100) + 1; // 1-100
        feedbackLabel.setText(" ");
        guessButton.setEnabled(true);
        numberScrollBar.setEnabled(true);
        applyBoundsToScrollBar();
        numberScrollBar.setValue(minBound);
        guessLabel.setText("Current guess: " + numberScrollBar.getValue());
    }

    private void applyBoundsToScrollBar() {
        // Ensure bounds are valid
        if (minBound > maxBound) {
            int tmp = minBound;
            minBound = maxBound;
            maxBound = tmp;
        }
        // Update scroll bar min/max considering extent (visible amount)
        int extent = numberScrollBar.getVisibleAmount();
        numberScrollBar.setMinimum(minBound);
        numberScrollBar.setMaximum(maxBound + extent);

        // Clamp current value into bounds
        int val = numberScrollBar.getValue();
        if (val < minBound) val = minBound;
        if (val > maxBound) val = maxBound;
        numberScrollBar.setValue(val);

        rangeLabel.setText("Range: " + minBound + " - " + maxBound);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuess numberGuess = new NumberGuess();
            numberGuess.setVisible(true);
        });
    }
}
