import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimpleAnimation extends JFrame {
    private JLabel displayLabel = new JLabel();
    private ImageIcon image0 = new ImageIcon("src/image0.gif");
    private ImageIcon image1 = new ImageIcon("src/image1.gif");
    int pictureNumber = 0;
    public SimpleAnimation() {
        setTitle("Simple Animation");
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        displayLabel.setPreferredSize(new Dimension(image0.getIconWidth(), image0.getIconHeight()));
        displayLabel.setIcon(image0);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(displayLabel, gridConstraints);
        displayLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayMouseClicked(e);
            }
        });
        
        pack();
        setLocationRelativeTo(null);
    }
    private void displayMouseClicked(MouseEvent e) {
        if (pictureNumber == 0) {
            displayLabel.setIcon(image1);
            pictureNumber = 1;
        } else {
            displayLabel.setIcon(image0);
            pictureNumber = 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new SimpleAnimation().setVisible(true);
        });
    }
}
