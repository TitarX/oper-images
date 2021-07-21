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
public class Green_RedFilter extends RGBImageFilter implements InterfaceForFilters
{

    public Image applyFilter(Image img)
    {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(),this));
    }

    public int filterRGB(int x,int y,int rgb)
    {
        return ((rgb&0xff0000ff)|((rgb&0xff0000)>>8)|((rgb&0xff00)<<8));
    }
}
