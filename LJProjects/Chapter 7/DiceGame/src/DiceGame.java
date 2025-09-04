import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class DiceGame extends JFrame {
    private JLabel diceLabel1 = new JLabel();
    private JLabel diceLabel2 = new JLabel();
    private JPanel gamePanel = new JPanel();
    private ImageIcon diceIcon1 = new ImageIcon();
    private ImageIcon diceIcon2 = new ImageIcon();
    private JButton rollButton = new JButton();
    private JButton quitButton = new JButton();
    private JLabel scoreLabel1 = new JLabel();
    private JLabel scoreLabel2 = new JLabel();
    private JPanel controlPanel = new JPanel();
    private Random myRandom = new Random();
    int[] scores = new int[2];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new DiceGame().setVisible(true);
        });
    }
    public DiceGame() {
        setTitle("Dice Game Board");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints;
        scores[0]=0;
        scores[1]=0;
        //Dice Panel
        gamePanel.setLayout(new GridBagLayout());
        gamePanel.setBackground(Color.GRAY);
        gamePanel.setBorder(BorderFactory.createTitledBorder("Dice"));
        gamePanel.setPreferredSize(new Dimension(400,300));

        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(gamePanel, gridConstraints);

        //Control Panel
        controlPanel.setLayout(new GridBagLayout());
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setBorder(BorderFactory.createTitledBorder("Playing control"));
        controlPanel.setPreferredSize(new Dimension(400,100));

        rollButton.setText("Roll");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(0,10,10,10);
        controlPanel.add(rollButton, gridConstraints);
        rollButton.addActionListener(e->{
            rollButtonActionPerformed(e);
        });

        quitButton.setText("Quit");
        gridConstraints= new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10,10,10,10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        quitButton.addActionListener(e->{
            System.exit(0);
        });

        controlPanel.add(quitButton, gridConstraints);

        scoreLabel1.setText("Player 1: " + scores[0]);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(0,10,0,10);
        controlPanel.add(scoreLabel1, gridConstraints);

        scoreLabel2.setText("Player 2: " + scores[1]);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(0,10,0,10);
        controlPanel.add(scoreLabel2, gridConstraints);

        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        getContentPane().add(controlPanel, gridConstraints);
        pack();
        setLocationRelativeTo(null);

    }

    private void rollButtonActionPerformed(ActionEvent e) {

        int dice1 = myRandom.nextInt(6) + 1;
        int dice2 = myRandom.nextInt(6) + 1;
        diceIcon1 = new ImageIcon(getClass().getResource("resources/image/face"+dice1+".png"));
        diceIcon2 = new ImageIcon(getClass().getResource("resources/image/face"+dice2+".png"));
        diceLabel1.setIcon(diceIcon1);
        diceLabel1.setPreferredSize(new Dimension(120,120));
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,10,10,10);
        gamePanel.add(diceLabel1, gridConstraints);

        diceLabel2.setIcon(diceIcon2);
        diceLabel2.setPreferredSize(new Dimension(120,120));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,10,10,10);
        gamePanel.add(diceLabel2, gridConstraints);
        scores[0] += dice1;
        scores[1] += dice2;
        scoreLabel1.setText("Player 1: " + scores[0]);
        scoreLabel2.setText("Player 2: " + scores[1]);


        }
    }


