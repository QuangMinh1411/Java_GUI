import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuizzGame extends JFrame {
    private QASet[] qaSets = new QASet[5];
    // Initialize QA sets
    private int score = 0;
    private int currentQASet = 0;
    private JPanel currentPanel = new JPanel();
    private JButton nextButton = new JButton("Next");
    public QuizzGame() {
        qaSets[0] = new QASet(
                1,
                "Capital of USA",
                new String[]{"New York", "Washington DC","Los Angeles", "Chicago"},
                "Washington DC"
        );
        qaSets[1] = new QASet(
                2,
                "Capital of Vietnam",
                new String[]{"HCM city","Da nang","Hanoi","Haiphong"},
                "Hanoi"
        );
        qaSets[2] = new QASet(
                3,
                "Capital of England",
                new String[]{"Manchester","London","Oxford","Liverpool"},
                "London"
        );

        qaSets[3] = new QASet(
                4,
                "Capital of Japan",
                new String[]{"Osaka","Yokogawa","Kyoto","Tokyo"},
                "Tokyo"
        );
        qaSets[4] = new QASet(
                5,
                "Capital of Germany",
                new String[]{"Hamburg","Munich","Berlin","Frankfurt"},
                "Berlin"
        );
        List<QASet> qaSetList = Arrays.asList(qaSets);
        Collections.shuffle(qaSetList);
        qaSetList.toArray(qaSets);
        setTitle("Quizz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints;
        //Answer Question Panel
        currentPanel = setQAPanel(currentQASet);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(currentPanel, gridConstraints);

        //Button
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(nextButton, gridConstraints);
        nextButton.addActionListener(e -> {
            // Find the selected answer text, if any
            String selectedAnswer = null;
            for (Component comp : currentPanel.getComponents()) {
                if (comp instanceof JRadioButton) {
                    JRadioButton rb = (JRadioButton) comp;
                    if (rb.isSelected()) {
                        selectedAnswer = rb.getText();
                        break;
                    }
                }
            }

            // If nothing selected, prompt user and do not advance
            if (selectedAnswer == null) {
                JOptionPane.showMessageDialog(this, "Please select an answer before proceeding.");
                return;
            }

            // Evaluate the selected answer
            if (selectedAnswer.equals(qaSets[currentQASet].getCorrectAnswer())) {
                JOptionPane.showMessageDialog(this, "Correct Answer!");
                score++;
            } else {
                JOptionPane.showMessageDialog(this, "Wrong Answer!");
            }

            // Move to next question
            currentQASet++;

            if (currentQASet < qaSets.length) {
                // Replace current question panel with the next one
                getContentPane().remove(currentPanel);
                currentPanel = setQAPanel(currentQASet);
                GridBagConstraints gc = new GridBagConstraints();
                gc.gridx = 0;
                gc.gridy = 0;
                getContentPane().add(currentPanel, gc);
                getContentPane().revalidate();
                getContentPane().repaint();
                pack();
            } else {
                // Show final score
                getContentPane().remove(currentPanel);
                JLabel scoreLabel = new JLabel("Your score: " + score + "/" + qaSets.length);
                GridBagConstraints gc = new GridBagConstraints();
                gc.gridx = 0;
                gc.gridy = 0;
                getContentPane().add(scoreLabel, gc);
                getContentPane().revalidate();
                getContentPane().repaint();
                pack();
                JOptionPane.showMessageDialog(this, "Quiz finished!");
                // Optionally disable the Next button as the quiz is finished
                nextButton.setEnabled(false);
            }

        });
        pack();
        setLocationRelativeTo(null);

    }

    public JPanel setQAPanel(int ID){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Question: " + (ID + 1)));
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.GRAY);
        GridBagConstraints gridConstraints;
        JLabel questionLabel = new JLabel(qaSets[ID].getQuestion());
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.WEST;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        panel.add(questionLabel, gridConstraints);
        ButtonGroup buttonGroup = new ButtonGroup();
        for(int i = 0; i < qaSets[ID].getAnswers().length; i++){
            JRadioButton button = new JRadioButton(qaSets[ID].getAnswers()[i]);
            buttonGroup.add(button);
            gridConstraints = new GridBagConstraints();
            gridConstraints.gridx = 1;
            gridConstraints.gridy = i+1;
            gridConstraints.anchor = GridBagConstraints.WEST;
            gridConstraints.insets = new Insets(10, 10, 10, 10);
            panel.add(button, gridConstraints);
        }

        return panel;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizzGame().setVisible(true));
    }
}
