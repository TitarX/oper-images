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
public class InvertFilter extends RGBImageFilter implements InterfaceForFilters
{
    public Image applyFilter(Image img)
    {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(),this));
    }

    public int filterRGB(int x,int y,int rgb)
    {
        int r=0xff-(rgb>>16)&0xff;
        int g=0xff-(rgb>>8)&0xff;
        int b=0xff-rgb&0xff;
        return(0xff000000|r<<16|g<<8|b);
    }
}
