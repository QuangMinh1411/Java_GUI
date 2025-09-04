import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Random;

public class DiceGame extends JFrame {
    private static final Dimension GAME_PANEL_SIZE = new Dimension(400, 300);
    private static final Dimension CONTROL_PANEL_SIZE = new Dimension(400, 100);
    private static final Dimension DICE_LABEL_SIZE = new Dimension(120, 120);
    private static final Insets DEFAULT_INSETS = new Insets(10, 10, 10, 10);

    private final JLabel diceLabel1 = new JLabel("?", SwingConstants.CENTER);
    private final JLabel diceLabel2 = new JLabel("?", SwingConstants.CENTER);
    private final JPanel gamePanel = new JPanel(new GridBagLayout());
    private final JPanel controlPanel = new JPanel(new GridBagLayout());
    private final JButton rollButton = new JButton("Roll");
    private final JButton quitButton = new JButton("Quit");
    private final JLabel scoreLabel1 = new JLabel();
    private final JLabel scoreLabel2 = new JLabel();
    private final Random random = new Random();
    private final int[] scores = new int[2];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DiceGame().setVisible(true));
    }

    public DiceGame() {
        setTitle("Dice Game Board");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        initGamePanel();
        initControlPanel();

        pack();
        setLocationRelativeTo(null);
    }

    private void initGamePanel() {
        gamePanel.setBackground(Color.GRAY);
        gamePanel.setBorder(BorderFactory.createTitledBorder("Dice"));
        gamePanel.setPreferredSize(GAME_PANEL_SIZE);

        // Configure dice labels once and add them to the grid
        configureDiceLabel(diceLabel1);
        configureDiceLabel(diceLabel2);

        GridBagConstraints gc = baseGC();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = DEFAULT_INSETS;
        gamePanel.add(diceLabel1, gc);

        gc = baseGC();
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = DEFAULT_INSETS;
        gamePanel.add(diceLabel2, gc);

        GridBagConstraints frameGC = baseGC();
        frameGC.gridx = 0;
        frameGC.gridy = 0;
        getContentPane().add(gamePanel, frameGC);
    }

    private void initControlPanel() {
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setBorder(BorderFactory.createTitledBorder("Playing control"));
        controlPanel.setPreferredSize(CONTROL_PANEL_SIZE);

        GridBagConstraints gc = baseGC();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(0, 10, 10, 10);
        controlPanel.add(rollButton, gc);
        rollButton.addActionListener(this::rollButtonActionPerformed);

        gc = baseGC();
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = DEFAULT_INSETS;
        gc.anchor = GridBagConstraints.WEST;
        controlPanel.add(quitButton, gc);
        quitButton.addActionListener(e -> System.exit(0));

        scores[0] = 0;
        scores[1] = 0;
        updateScoreLabels();

        gc = baseGC();
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 10, 0, 10);
        controlPanel.add(scoreLabel1, gc);

        gc = baseGC();
        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(0, 10, 0, 10);
        controlPanel.add(scoreLabel2, gc);

        GridBagConstraints frameGC = baseGC();
        frameGC.gridx = 0;
        frameGC.gridy = 1;
        getContentPane().add(controlPanel, frameGC);
    }

    private void configureDiceLabel(JLabel label) {
        label.setPreferredSize(DICE_LABEL_SIZE);
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        label.setFont(label.getFont().deriveFont(Font.BOLD, 24f));
    }

    private GridBagConstraints baseGC() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        return gc;
    }

    private void rollButtonActionPerformed(ActionEvent e) {
        int dice1 = random.nextInt(6) + 1;
        int dice2 = random.nextInt(6) + 1;

        setDice(diceLabel1, dice1);
        setDice(diceLabel2, dice2);

        scores[0] += dice1;
        scores[1] += dice2;
        updateScoreLabels();

        gamePanel.revalidate();
        gamePanel.repaint();
    }

    private void setDice(JLabel label, int value) {
        ImageIcon icon = loadDiceIcon(value);
        if (icon != null) {
            label.setIcon(icon);
            label.setText("");
        } else {
            // Fallback to text if resource is not found
            label.setIcon(null);
            label.setText(String.valueOf(value));
        }
    }

    private void updateScoreLabels() {
        scoreLabel1.setText("Player 1: " + scores[0]);
        scoreLabel2.setText("Player 2: " + scores[1]);
    }

    private ImageIcon loadDiceIcon(int value) {
        // Try both relative and absolute resource paths to be resilient
        String path = "resources/image/face" + value + ".png";
        URL url = getClass().getResource(path);
        if (url == null) {
            url = getClass().getResource("/" + path);
        }
        return url != null ? new ImageIcon(url) : null;
    }
}
