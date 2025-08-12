import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class StudentInfor extends JFrame {
    private JScrollBar gradeScrollBar;
    private JLabel gradeLabel;
    private JLabel gradeValueLabel;
    private JLabel nameLabel = new JLabel("Student name");
    private JTextField nameField = new JTextField(10);
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;

    // Date spinners: month (1-12), day (1-31 adjusted by month/year), year (range)
    private final JSpinner monthSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
    private final JSpinner daySpinner   = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
    private final JSpinner yearSpinner  = new JSpinner(new SpinnerNumberModel(2000, 1900, Calendar.getInstance().get(Calendar.YEAR), 1));
    private final int IMAGE_WIDTH = 150;  // Fixed width for the image display
    private final int IMAGE_HEIGHT = 200; // Fixed height for the image display
    private JLabel imageLabel;
    private JButton chooseImageButton;

    private String sex = "Male";
    private int grade = 1;
    public StudentInfor() {
        setTitle("Student information");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                getWidth())), (int) (0.5 * (screenSize.height -
                getHeight())), getWidth(), getHeight());
        GridBagConstraints gridConstraints;

        //Name
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(nameLabel, gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        getContentPane().add(nameField, gridConstraints);

        //Student grade panel
        JPanel gradePanel = new JPanel(new GridBagLayout());
        gradePanel.setBorder(BorderFactory.createTitledBorder("Student Grade"));
        gridConstraints = new GridBagConstraints();
        gradeLabel = new JLabel("Grade");
        gradeValueLabel = new JLabel("1");
        gradeScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 1, 0, 1, 6);
        gradeScrollBar.setPreferredSize(new Dimension(200, 20));
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 0, 10, 5);
        gridConstraints.anchor = GridBagConstraints.WEST;
        gradePanel.add(gradeLabel, gridConstraints);

        gridConstraints.gridx = 1;
        gridConstraints.gridy=0;
        gridConstraints.insets = new Insets(10, 5, 10, 5);
        gradePanel.add(gradeValueLabel, gridConstraints);

        gridConstraints.gridx = 2;
        gridConstraints.gridy=0;
        gridConstraints.insets = new Insets(10, 5, 10, 10);
        gridConstraints.anchor = GridBagConstraints.EAST;
        gradePanel.add(gradeScrollBar, gridConstraints);
        gradeScrollBar.addAdjustmentListener(e -> {
            gradeValueLabel.setText(String.valueOf(gradeScrollBar.getValue()));
            grade = gradeScrollBar.getValue();
        });

        //Add grade panel to frame
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth=2;
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(gradePanel, gridConstraints);

        //Sex panel
        JPanel sexPanel = new JPanel(new GridBagLayout());
        sexPanel.setBorder(BorderFactory.createTitledBorder("Sex"));
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        ButtonGroup sexButtonGroup = new ButtonGroup();
        sexButtonGroup.add(maleRadioButton);
        sexButtonGroup.add(femaleRadioButton);
        maleRadioButton.setSelected(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(5, 10, 5, 10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        sexPanel.add(maleRadioButton, gridConstraints);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        sexPanel.add(femaleRadioButton, gridConstraints);
        //Add sex panel to frame
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 1;
        getContentPane().add(sexPanel,gridConstraints);
        maleRadioButton.addActionListener(this::sexRadionAction);
        femaleRadioButton.addActionListener(this::sexRadionAction);
        //Student Date Panel
        JPanel datePanel = new JPanel(new GridBagLayout());
        datePanel.setBorder(BorderFactory.createTitledBorder("Student Date"));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        datePanel.add(new JLabel("Date of Birth"), gridConstraints);
        //Month spinner
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        datePanel.add(labeled(monthSpinner, "Month"), gridConstraints);
        //Day spinner
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        datePanel.add(labeled(daySpinner, "Day"), gridConstraints);
        //Year spinner
        gridConstraints.gridx = 3;
        gridConstraints.gridy = 0;
        datePanel.add(labeled(yearSpinner, "Year"), gridConstraints);
        monthSpinner.addChangeListener(e -> updateDaySpinner());
        yearSpinner.addChangeListener(e -> updateDaySpinner());


        //Add the date panel
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.gridwidth=4;
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(datePanel, gridConstraints);

        //Image panel
        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setBorder(BorderFactory.createTitledBorder("Student Image"));
        //Initalize the image label
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //Choose image button
        chooseImageButton = new JButton("Choose Image");
        chooseImageButton.addActionListener(e -> {
           chooseImage();
        });
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(5, 5, 5, 5);
        imagePanel.add(imageLabel, gridConstraints);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(0, 5, 5, 5);
        imagePanel.add(chooseImageButton, gridConstraints);

        //Add image panel to frame
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 4;
        gridConstraints.gridheight=4;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        gridConstraints.anchor = GridBagConstraints.EAST;
        getContentPane().add(imagePanel, gridConstraints);

        //Button to show all information
        JButton showButton = new JButton("Show");

        showButton.addActionListener(this::showInfor);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 4;
        gridConstraints.gridwidth=2;
        gridConstraints.insets = new Insets(20, 10, 20, 10);
        getContentPane().add(showButton, gridConstraints);
        pack();
    }

    private void showInfor(ActionEvent actionEvent) {
        String msg = "";
        int birthYear = (Integer) yearSpinner.getValue();
        String s = sex.equals("Male") ? "He" : "She";
        int age = Calendar.getInstance().get(Calendar.YEAR) - birthYear;

        if( !nameField.getText().isEmpty() && imageLabel.getIcon() != null){
            msg += nameField.getText() + " " + " is a student in the ";
            msg += grade + " grade.\n";
            msg += s+" is "+ age + " years old.\n";

        }
        JOptionPane.showConfirmDialog(null, msg, "Student Profile", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);

    }

    private void sexRadionAction(ActionEvent e) {
        sex = e.getActionCommand();
    }

    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                String name = f.getName().toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".jpeg");
            }
            public String getDescription() {
                return "JPEG Images (*.jpg, *.jpeg)";
            }
        });
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Read the image
                BufferedImage originalImage = ImageIO.read(selectedFile);

                // Scale the image to fit our display size while maintaining aspect ratio
                double scale = Math.min(
                        (double) IMAGE_WIDTH / originalImage.getWidth(),
                        (double) IMAGE_HEIGHT / originalImage.getHeight()
                );

                int scaledWidth = (int) (originalImage.getWidth() * scale);
                int scaledHeight = (int) (originalImage.getHeight() * scale);

                // Create scaled image
                Image scaledImage = originalImage.getScaledInstance(
                        scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

                // Update the image label
                imageLabel.setIcon(new ImageIcon(scaledImage));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error loading image: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private JComponent labeled(JComponent component, String caption) {
        JPanel panel = new JPanel(new BorderLayout(3, 0));
        panel.add(new JLabel(caption), BorderLayout.WEST);
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }
    private void updateDaySpinner() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, (Integer) yearSpinner.getValue());
        calendar.set(Calendar.MONTH, (Integer) monthSpinner.getValue() - 1);

        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDay = (Integer) daySpinner.getValue();

        // Update the day spinner model with new maximum
        SpinnerNumberModel dayModel = (SpinnerNumberModel) daySpinner.getModel();
        dayModel.setMaximum(maxDay);

        // Adjust the current day if it exceeds the new maximum
        if (currentDay > maxDay) {
            daySpinner.setValue(maxDay);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new StudentInfor().setVisible(true);
        });
    }
}
