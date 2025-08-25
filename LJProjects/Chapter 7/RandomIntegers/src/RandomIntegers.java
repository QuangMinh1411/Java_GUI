import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ThreadLocalRandom;

/*
 A tiny Swing app that displays a list of integers in a random order.

 UI:
 - JTextArea inside a scroll pane shows the numbers.
 - JSpinner chooses how many integers to include (min 2, max 100).
 - A button generates and displays a new random permutation.

 Logic:
 - The method sortIntegers(int n) returns a permutation of [0..n-1].
 - It implements the Fisher–Yates (Knuth) shuffle, which runs in O(n) time
   and uses O(1) extra space, producing an unbiased random order.
*/
public class RandomIntegers extends JFrame {
    private JScrollPane randomScrollPane = new JScrollPane();
    private JTextArea randomTextArea = new JTextArea();
    private JSpinner randomSpinner = new JSpinner();
    private JButton randomButton = new JButton();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new RandomIntegers().setVisible(true);
        });
    }
    public RandomIntegers(){
        setTitle("Random Integers");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        randomTextArea.setColumns(6);
        randomTextArea.setRows(10);
        randomTextArea.setLineWrap(true);
        randomTextArea.setEditable(false);
        randomScrollPane.setViewportView(randomTextArea);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets= new Insets(5,5,5,5);
        getContentPane().add(randomScrollPane, gridConstraints);
        randomSpinner.setModel(new SpinnerNumberModel(2, 2, 100, 1));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(5,0,5,0);
        getContentPane().add(randomSpinner, gridConstraints);
        randomButton.setText("Shuffle Integers");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(5,0,5,0);
        getContentPane().add(randomButton, gridConstraints);
        randomButton.addActionListener(this::randomButtonActionPerformed);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Generates a random permutation of the integers [0..n-1].
     *
     * This uses the modern Fisher–Yates shuffle (a.k.a. Knuth shuffle):
     * starting from the end of the array and moving left, pick a uniformly
     * random index j in [0, i] and swap elements at i and j. This produces
     * an unbiased permutation in O(n) time and O(1) extra space.
     */
    public int[] sortIntegers(int n){
        // Initialize array with 0..n-1
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = i;
        }
        // Shuffle in-place using ThreadLocalRandom for simple, fast RNG
        for (int i = n - 1; i >= 1; i--) {
            int j = ThreadLocalRandom.current().nextInt(i + 1); // [0, i]
            int tmp = values[j];
            values[j] = values[i];
            values[i] = tmp;
        }
        return values;
    }
    private void randomButtonActionPerformed(ActionEvent e) {
        int arraySize = ((Number) randomSpinner.getValue()).intValue();
        int[] integerArray = sortIntegers(arraySize);

        // Build output once for better performance than many JTextArea.append calls
        StringBuilder sb = new StringBuilder(arraySize * 4);
        for (int value : integerArray) {
            sb.append(value).append('\n');
        }
        randomTextArea.setText(sb.toString());
    }
}
