import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

public class Transparency {
    public static Image makeColorTransparent(Image img,final Color color){
        ImageFilter filter = new RGBImageFilter() {
            public int markerRGB = color.getRGB() | 0xFF000000;
            public final int filterRGB(int x,int y,int rgb) {
                if((rgb|0xFF000000)==markerRGB){
                    return 0x00FFFFFF&rgb;
                }else{
                    return rgb;
                }
            }
        };
        ImageProducer ip = new FilteredImageSource(img.getSource(),filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
}
