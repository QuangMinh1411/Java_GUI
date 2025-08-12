import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageFilter;

public class ImageViewer extends JFrame {
    private JFileChooser imageChooser = new JFileChooser();
    private JLabel imageLabel = new JLabel();
    public ImageViewer() {
        setTitle("ImageViewer");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width -
                getWidth())), (int) (0.5 * (screenSize.height -
                getHeight())), getWidth(), getHeight());
        GridBagConstraints gridConstraints;

        //File choose

        imageChooser.addChoosableFileFilter(new FileNameExtensionFilter("Graphics Files (.gif, .jpg)",
                "gif", "jpg"));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx =0;
        gridConstraints.gridy = 0;
        getContentPane().add(imageChooser, gridConstraints);
        imageChooser.addActionListener(this::imageChooserActionPerformed);

        imageLabel.setPreferredSize(new Dimension(270,300));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.WHITE);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        gridConstraints.gridx=1;
        gridConstraints.gridy=0;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(imageLabel, gridConstraints);
        pack();
    }

    private void imageChooserActionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
            ImageIcon myImage = new ImageIcon(imageChooser.getSelectedFile().toString());
            imageLabel.setIcon(myImage);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageViewer window = new ImageViewer();
            window.setVisible(true);
        });
    }
}
