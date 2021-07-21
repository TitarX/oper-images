/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.util.logging.*;

abstract class SharpnessFilter implements ImageConsumer,InterfaceForFilters
{

    int width, height;
    int imgpixels[], newimgpixels[];
    boolean imageReady=false;

    abstract void convolve();

    public Image applyFilter(Image img)
    {
        img.getSource().startProduction(this);
        imageReady=false;
        waitForImage();
        newimgpixels=new int[width*height];

        try
        {
            convolve();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width,height,newimgpixels,0,width));
    }

    synchronized void waitForImage()
    {

        while(!imageReady)
        {
            try
            {
                wait();
            }catch(InterruptedException ex)
            {
                Logger.getLogger(SharpnessFilter.class.getName()).log(Level.SEVERE,null,ex);
            }
        }

    }

    public void setProperties(java.util.Hashtable dummy)
    {
    }

    public void setColorModel(ColorModel dummy)
    {
    }

    public void setHints(int dummy)
    {
    }

    public synchronized void imageComplete(int dummy)
    {
        imageReady=true;
        notifyAll();
    }

    public void setDimensions(int x,int y)
    {
        width=x;
        height=y;
        imgpixels=new int[x*y];
    }

    public void setPixels(int x1,int y1,int w,int h,ColorModel model,byte pixels[],int off,int scansize)
    {
        int pix, x, y, x2, y2, sx, sy;

        x2=x1+w;
        y2=y1+h;
        sy=off;
        for(y=y1;y<y2;y++)
        {
            sx=sy;
            for(x=x1;x<x2;x++)
            {
                pix=model.getRGB(pixels[sx++]);
                if((pix&0xff000000)==0)
                {
                    pix=0x00ffffff;
                }
                imgpixels[y*width+x]=pix;
            }
            sy+=scansize;
        }
    }

    public void setPixels(int x1,int y1,int w,int h,ColorModel model,int pixels[],int off,int scansize)
    {
        int pix, x, y, x2, y2, sx, sy;

        x2=x1+w;
        y2=y1+h;
        sy=off;
        for(y=y1;y<y2;y++)
        {
            sx=sy;
            for(x=x1;x<x2;x++)
            {
                pix=model.getRGB(pixels[sx++]);
                if((pix&0xff000000)==0)
                {
                    pix=0x00ffffff;
                }
                imgpixels[y*width+x]=pix;
            }
            sy+=scansize;
        }
    }
}
