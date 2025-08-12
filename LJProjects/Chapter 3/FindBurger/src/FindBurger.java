import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class FindBurger extends JFrame {
    private JLabel label0 = new JLabel();
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JLabel[] choiceLabel = new JLabel[3];
    private ImageIcon burger = new ImageIcon("src/resources/images/burger.jpg");
    private JButton newButton = new JButton();
    int burgerLocation;
    Random myRandom = new Random();

    public FindBurger() {
        setTitle("Find The Burger");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                getWidth())), (int) (0.5 * (screenSize.height -
                getHeight())), getWidth(), getHeight());
        GridBagConstraints gridConstraints;
        //position controls
        choiceLabel[0] = label0;
        choiceLabel[1] = label1;
        choiceLabel[2] = label2;
        for(int i = 0; i < 3; i++) {
            gridConstraints = new GridBagConstraints();
            choiceLabel[i].setPreferredSize(new Dimension(burger.getIconWidth(),burger.getIconHeight()));
            choiceLabel[i].setOpaque(true);
            choiceLabel[i].setBackground(Color.red);
            gridConstraints.gridx=i;
            gridConstraints.gridy=0;
            gridConstraints.insets = new Insets(10,10,10,10);
            getContentPane().add(choiceLabel[i], gridConstraints);
            choiceLabel[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    labelMouseClicked(e);
                }
            });
        }
        newButton.setText("Play Again");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx=1;
        gridConstraints.gridy=1;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(newButton, gridConstraints);
        newButton.addActionListener(this::newButtonActionPerformed);
        pack();
        newButton.doClick();
    }

    private void newButtonActionPerformed(ActionEvent e) {
        for(int i = 0; i < 3; i++) {
            choiceLabel[i].setIcon(null);
            choiceLabel[i].setBackground(Color.red);
        }
        burgerLocation = myRandom.nextInt(3);
        newButton.setEnabled(false);
    }

    private void labelMouseClicked(MouseEvent e) {
        Component clickedComponent = e.getComponent();
        int choice;
        for(choice=0;choice<3;choice++) {
            if(clickedComponent == choiceLabel[choice]) {
                break;
            }
        }
        choiceLabel[choice].setBackground(Color.white);
        if(choice==burgerLocation){
            choiceLabel[choice].setIcon(burger);
            newButton.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FindBurger window = new FindBurger();
            window.setVisible(true);
        });
    }
}