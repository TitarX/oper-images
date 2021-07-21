/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.awt.*;
import java.awt.image.*;

/**
 *
 * @author TitarX
 */
public class BrightnessUpFilter extends RGBImageFilter implements InterfaceForFilters
{
    double d=1.1;

    public Image applyFilter(Image img)
    {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(),this));
    }

    private int inc(int i)
    {
        i=(int)(i*d);
        return (i>255)?255:i;
    }

    public int filterRGB(int x,int y,int rgb)
    {
        int r=inc((rgb>>16)&0xff);
        int g=inc((rgb>>8)&0xff);
        int b=inc(rgb&0xff);
        return(0xff000000|r<<16|g<<8|b);
    }
}
