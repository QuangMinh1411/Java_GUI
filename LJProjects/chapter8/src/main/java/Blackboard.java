import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Blackboard extends JFrame {
    private JMenuBar mainMenuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem newMenuItem = new JMenuItem("New");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");
    private JPanel drawPanel = new JPanel();
    private JLabel leftColorLabel = new JLabel();
    private JLabel rightColorLabel = new JLabel();
    private JPanel colorPanel = new JPanel();
    private  JLabel[] colorLabels = new JLabel[8];
    private Graphics2D g2D;
    private double xPrevious,yPrevious;
    private Color drawColor,leftColor,rightColor;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new Blackboard().setVisible(true);
        });
    }
    public Blackboard() {
        setTitle("Blackboard");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());

        //build menu
        setJMenuBar(mainMenuBar);
        fileMenu.setMnemonic('F');
        mainMenuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        newMenuItem.addActionListener(this::newMenuItemActionPerformed);
        exitMenuItem.addActionListener(this::exitMenuItemActionPeformed);

        drawPanel.setPreferredSize(new Dimension(500,400));
        drawPanel.setBackground(Color.BLACK);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridheight = 2;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(drawPanel,gridConstraints);
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                drawPanelMousePressed(mouseEvent);
            }
        });

        drawPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                drawPanelMouseDragged(mouseEvent);
            }
        });
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                drawPanelMouseReleased(mouseEvent);
            }
        });

        leftColorLabel.setPreferredSize(new Dimension(40,40));
        leftColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,5,10,10);
        getContentPane().add(leftColorLabel,gridConstraints);
        rightColorLabel.setPreferredSize(new Dimension(40,40));
        rightColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,5,10,10);
        getContentPane().add(rightColorLabel,gridConstraints);

        colorPanel.setPreferredSize(new Dimension(80,160));
        colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 2;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(colorPanel,gridConstraints);

        colorPanel.setLayout(new GridBagLayout());
        int j = 0;
        for(int i=0;i<colorLabels.length;i++){
            colorLabels[i] = new JLabel();
            colorLabels[i].setPreferredSize(new Dimension(30,30));
            colorLabels[i].setOpaque(true);
           gridConstraints = new GridBagConstraints();
           gridConstraints.gridx = j;
           gridConstraints.gridy = i-j*4;
           colorPanel.add(colorLabels[i],gridConstraints);
           if(i==3){
               j++;
           }
           colorLabels[i].addMouseListener(new MouseAdapter() {
               @Override
               public void mousePressed(MouseEvent e) {
                   colorMousePressed(e);
               }
           });

        }

        //set color labels
        colorLabels[0].setBackground(Color.GRAY);
        colorLabels[1].setBackground(Color.BLUE);
        colorLabels[2].setBackground(Color.GREEN);
        colorLabels[3].setBackground(Color.CYAN);
        colorLabels[4].setBackground(Color.RED);
        colorLabels[5].setBackground(Color.MAGENTA);
        colorLabels[6].setBackground(Color.YELLOW);
        colorLabels[7].setBackground(Color.WHITE);
        leftColor = colorLabels[0].getBackground();
        rightColor = colorLabels[7].getBackground();
        leftColorLabel.setBackground(leftColor);
        rightColorLabel.setBackground(rightColor);

        pack();
        setLocationRelativeTo(null);
        g2D = (Graphics2D)drawPanel.getGraphics();
    }

    private void exitMenuItemActionPeformed(ActionEvent actionEvent) {
        int response;
        response = JOptionPane.showConfirmDialog(null,"Are you sure you wnat to exit the Blackboard program?",
                "Exit Program",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE
                );
        if(response==JOptionPane.YES_OPTION){
            g2D.dispose();
            System.exit(0);
        }else {
            return;
        }
    }

    private void newMenuItemActionPerformed(ActionEvent e) {
        int response;
        response = JOptionPane.showConfirmDialog(null,"Are you sure you want to start a new drawing?",
                "New Drawing",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(response==JOptionPane.YES_OPTION){
            g2D.setPaint(drawPanel.getBackground());
            g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(),
                    drawPanel.getHeight()));
        }
    }

    private void drawPanelMousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1 || e.getButton()==MouseEvent.BUTTON3){
            xPrevious = e.getX();
            yPrevious = e.getY();
            if(e.getButton()==MouseEvent.BUTTON1){
                drawColor = leftColor;
            }else {
                drawColor = rightColor;
            }

        }
    }
    private  void drawPanelMouseDragged(MouseEvent e) {
        Line2D.Double myLine = new Line2D.Double(xPrevious,yPrevious,e.getX(),e.getY());
        g2D.setPaint(drawColor);
        g2D.draw(myLine);
        xPrevious = e.getX();
        yPrevious = e.getY();
    }
    private  void drawPanelMouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1 || e.getButton()==MouseEvent.BUTTON3){
            Line2D.Double myLine = new Line2D.Double(xPrevious,yPrevious,e.getX(),e.getY());
            g2D.setPaint(drawColor);
            g2D.draw(myLine);
        }

    }
    private void colorMousePressed(MouseEvent e) {
        Component clickedColor = e.getComponent();
        Toolkit.getDefaultToolkit().beep();
        if(e.getButton()==MouseEvent.BUTTON1){
            leftColor = clickedColor.getBackground();
            leftColorLabel.setBackground(leftColor);

        } else if (e.getButton()==MouseEvent.BUTTON3) {
            rightColor = clickedColor.getBackground();
            rightColorLabel.setBackground(rightColor);
        }

    }
}
