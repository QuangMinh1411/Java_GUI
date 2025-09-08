import java.awt.*;
import java.awt.geom.Line2D;

public class ColoredLine {
    public Line2D.Double theLine;
    public Color theColor;
    public ColoredLine(Line2D.Double line,Color color){
        theLine = line;
        theColor = color;
    }
}
