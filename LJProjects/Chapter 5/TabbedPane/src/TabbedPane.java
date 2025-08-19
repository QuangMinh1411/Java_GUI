import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TabbedPane extends JFrame {

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel panel1 = createPanel("Panel 1");
    private JPanel panel2 = createPanel("Panel 2");
    private JPanel panel3 = createPanel("Panel 3");
    public TabbedPane() {
        setTitle("TabbedPaneColor");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                getWidth())), (int) (0.5 * (screenSize.height -
                getHeight())), getWidth(), getHeight());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(tabbedPane, gridConstraints);
        tabbedPane.addTab("Red", panel1);
        tabbedPane.addTab("Green", panel2);
        tabbedPane.addTab("Blue", panel3);

        pack();
    }

    private JPanel createPanel(String title) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 100));
        panel.setLayout(new GridBagLayout());
        Random random = new Random();
        ButtonGroup group = new ButtonGroup();
        JRadioButton redBtn = new JRadioButton();
        redBtn.setSelected(true);
        JRadioButton greenBtn = new JRadioButton();
        JRadioButton blueBtn = new JRadioButton();
        group.add(redBtn);
        group.add(greenBtn);
        group.add(blueBtn);

        redBtn.setText(title);
        redBtn.addActionListener(e -> panel.setBackground(Color.red));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        panel.add(redBtn, gridBagConstraints);
        greenBtn.setText(title);
        greenBtn.addActionListener(e -> panel.setBackground(Color.green));
        gridBagConstraints= new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        panel.add(greenBtn, gridBagConstraints);
        blueBtn.setText(title);
        blueBtn.addActionListener(e -> panel.setBackground(Color.blue));
        gridBagConstraints.gridy = 2;
        panel.add(blueBtn, gridBagConstraints);

        return panel;

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new TabbedPane().setVisible(true);
        });
    }
}
