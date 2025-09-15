import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Scrolling extends JFrame {
    JPanel displayPanel = new JPanel();
    JScrollBar backgroundScrollBar = new JScrollBar();
    Image backgroundImage = new ImageIcon("src/sea.gif").getImage();
    Timer scrollTimer;
    Image fishTemp = new ImageIcon("src/fish.gif").getImage();
    Image fishImage = Transparency.makeColorTransparent(fishTemp, new Color(192,192,192));
    int fishW = 37;
    int fishH= 38;
    int fishX,fishY;
    int scrollX = 0;
    int imageSize = 130;
    Graphics2D g2D;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new Scrolling().setVisible(true);
        });
    }
    public Scrolling(){
        setTitle("Scrolling");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();

        displayPanel.setPreferredSize(new Dimension(imageSize,imageSize));
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(displayPanel,gridConstraints);
        displayPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
               displayPanelKeyPressed(e);
            }
        });

        gridConstraints = new GridBagConstraints();
        backgroundScrollBar.setPreferredSize(new Dimension(imageSize,20));
        backgroundScrollBar.setMinimum(0);
        backgroundScrollBar.setMaximum(20+backgroundScrollBar.getVisibleAmount());
        backgroundScrollBar.setBlockIncrement(2);
        backgroundScrollBar.setUnitIncrement(1);
        backgroundScrollBar.setValue(0);
        backgroundScrollBar.setOrientation(JScrollBar.HORIZONTAL);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(backgroundScrollBar,gridConstraints);

        scrollTimer = new Timer(100, this::scrollTImerActionPerformed);



        pack();
        setLocationRelativeTo(null);
        g2D = (Graphics2D)displayPanel.getGraphics();
        fishX = (int) (0.5*(displayPanel.getWidth()-fishW));
        fishY = (int) (0.5*(displayPanel.getHeight()-fishH));
        displayPanel.requestFocus();
        scrollTimer.start();
    }

    private void scrollTImerActionPerformed(ActionEvent e) {
        int addedWidth ;
        scrollX += backgroundScrollBar.getValue();
        if(scrollX > backgroundImage.getWidth(this)){
            scrollX = 0;
        }
        if(scrollX > (backgroundImage.getWidth(this)-imageSize)){
            addedWidth = backgroundImage.getWidth(this) - scrollX;
            g2D.drawImage(backgroundImage,0,0,addedWidth-1,imageSize-1,
                    scrollX,0,scrollX+addedWidth-1,imageSize-1,this);
            g2D.drawImage(backgroundImage,addedWidth,0,imageSize-1,imageSize-1,
                    0,0,imageSize-addedWidth-1,imageSize-1,this);
        }else{
            g2D.drawImage(backgroundImage,0,0,imageSize-1,imageSize-1,
                    scrollX,0,scrollX+imageSize-1,imageSize-1,this);
        }
        g2D.drawImage(fishImage,fishX,fishY,this);
    }

    private void displayPanelKeyPressed(KeyEvent e) {
        if(e.getKeyCode()== e.VK_UP){
            fishY-=5;
        }
        else if(e.getKeyCode()== e.VK_DOWN){
            fishY+=5;
        }
    }
}
