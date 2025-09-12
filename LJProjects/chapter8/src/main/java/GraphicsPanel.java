import javax.swing.*;
import java.awt.*;

class GraphicsPanel extends JPanel {
    public GraphicsPanel() {

    }
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        super.paintComponent(g2D);
        for(int i=0;i<BlackBoardRevisited.getMyLines().size();i++){
            ColoredLine thisLine = (ColoredLine)BlackBoardRevisited.getMyLines().get(i);
            g2D.setPaint(thisLine.theColor);
            g2D.draw(thisLine.theLine);
        }
        g2D.dispose();
    }
}