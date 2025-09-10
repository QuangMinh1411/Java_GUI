import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TexturePainting extends JFrame {
    JFileChooser paintChooser = new JFileChooser();
    JPanel paintPanel = new JPanel();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new TexturePainting().setVisible(true);
        });
    }
    public TexturePainting() {
        setTitle("Texture Painting");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gridConstraints = new GridBagConstraints();
        paintChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png","gif"));
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(paintChooser, gridConstraints);
        paintChooser.addActionListener(e->{
            paintChooserActionPerformed(e);
        });

        paintPanel.setPreferredSize(new Dimension(270,300));
        paintPanel.setBackground(Color.WHITE);
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(paintPanel, gridConstraints);

        pack();
        setLocationRelativeTo(null);
    }

    private void paintChooserActionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)){
            Image myImage = new ImageIcon(paintChooser.getSelectedFile().getAbsolutePath()).getImage();
            BufferedImage tImage = new BufferedImage(myImage.getWidth(this),myImage.getHeight(this),BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2DtImage = (Graphics2D) tImage.getGraphics();
            g2DtImage.drawImage(myImage,0,0,this);
            Rectangle2D.Double tRect = new Rectangle2D.Double(0,0,myImage.getWidth(this),
                    myImage.getHeight(this));
            TexturePaint tPaint = new TexturePaint(tImage,tRect);
            Graphics2D g2D = (Graphics2D) paintPanel.getGraphics();
            Rectangle2D.Double myRectangle = new Rectangle2D.Double(0,0,paintPanel.getWidth(),
                    paintPanel.getHeight());
            g2D.setPaint(tPaint);
            g2D.fill(myRectangle);
        }
    }
}
