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
public class GrayFilter extends RGBImageFilter implements InterfaceForFilters
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
        int n=(int)(.56*g+.33*r+.11*b);
        return(0xff000000|n<<16|n<<8|n);
    }
}
