/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import filters.*;
import java.awt.*;
import java.awt.image.*;

/**
 *
 * @author TitarX
 */
public class NoGreenFilter extends RGBImageFilter implements InterfaceForFilters
{

    public Image applyFilter(Image img)
    {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(),this));
    }

    public int filterRGB(int x,int y,int rgb)
    {
        int r=(rgb>>16)&0xff;
        int g=(rgb>>8)&0xff;
        int b=rgb&0xff;
        int n=0;
        return(0xff000000|r<<16|n<<8|b);
    }
}
