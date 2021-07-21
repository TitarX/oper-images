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
public class ContrastUpFilter extends RGBImageFilter implements InterfaceForFilters
{
    double d=1.1;

    public Image applyFilter(Image img)
    {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(),this));
    }

    private int inc(int i,double d)
    {
        i=(int)(i*d);
        return (i>255)?255:i;
    }

    private int cont(int i)
    {
        return (i<128)?(int)(i/d):inc(i,d);
    }

    public int filterRGB(int x,int y,int rgb)
    {
        int r=cont((rgb>>16)&0xff);
        int g=cont((rgb>>8)&0xff);
        int b=cont(rgb&0xff);
        return(0xff000000|r<<16|g<<8|b);
    }
}
