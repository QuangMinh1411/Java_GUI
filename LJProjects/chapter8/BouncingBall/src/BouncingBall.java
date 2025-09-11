import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BouncingBall extends JFrame {
    GraphicsPanel displayPanel = new GraphicsPanel();
    JButton startButton = new JButton();
    static Image myBall = new ImageIcon("src/resources/animated-ball.gif").getImage();
    static Timer ballTimer;
    static int ballSize,ballX,ballY,ballDir;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new BouncingBall().setVisible(true);
        });
    }
    public BouncingBall() {
        setTitle("Bouncing Ball");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        displayPanel.setPreferredSize(new Dimension(100,400));
        displayPanel.setBackground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,10,10,10);
        getContentPane().add(displayPanel,c);

        startButton.setText("Start");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,10,10,10);
        getContentPane().add(startButton,c);
        startButton.addActionListener(e->{
            startButtonActionPerformed(e);
        });

        ballTimer = new Timer(100,e->{
            ballTimerActionPerformed(e);
        });

        pack();
        setLocationRelativeTo(null);
        ballSize = 50;
        ballX = (int)(0.5*(displayPanel.getWidth()-ballSize));
        ballY = 0;
        ballDir = 1;
        displayPanel.repaint();
    }

    private void ballTimerActionPerformed(ActionEvent e) {
        ballY = ballY + ballDir*displayPanel.getHeight()/50;
        if(ballY < 0) {
            ballY = 0;
            ballDir = 1;
        } else if (ballY + ballSize>displayPanel.getHeight()) {
            ballY = displayPanel.getHeight()-ballSize;
            ballDir = -1;
        }
        displayPanel.repaint();
    }

    private void startButtonActionPerformed(ActionEvent e) {
        if(ballTimer.isRunning()) {
            ballTimer.stop();
            startButton.setText("Start");

        }else {
            ballTimer.start();
            startButton.setText("Stop");
        }
    }

    class GraphicsPanel extends JPanel {
        public void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            super.paintComponent(g2D);
            //draw ball
            g2D.drawImage(BouncingBall.myBall, BouncingBall.ballX,BouncingBall.ballY,
                    BouncingBall.ballSize, BouncingBall.ballSize, this);
            g2D.dispose();
        }
    }
}
