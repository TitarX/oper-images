/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operImages;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author TitarX
 */
public class ImageViewer extends JPanel
{

    private Image img;
    private Dimension dim;
    private boolean isImageLoaded;
    private Image imgLoader;
    private MediaTracker mtLoader;

    public ImageViewer(boolean isImageLoaded,Image img,Dimension dim)
    {
        this.isImageLoaded=isImageLoaded;
        this.img=img;
        this.dim=dim;
    }

    public ImageViewer(boolean isImageLoaded,Dimension dim)
    {
        this.isImageLoaded=isImageLoaded;
        this.dim=dim;

        imgLoader=new ImageIcon(getClass().getResource("/ico/loader.gif")).getImage();
        try
        {
            mtLoader=new MediaTracker(this);
            mtLoader.addImage(imgLoader,0);
            mtLoader.waitForAll();
        }catch(InterruptedException ex)
        {
            Logger.getLogger(ImageViewer.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(isImageLoaded)
        {
            g.drawImage(img,0,0,this);
        }else
        {
            g.drawImage(imgLoader,(int)dim.getWidth()/2-16,(int)dim.getHeight()/2-16,this);
            g.setColor(Color.blue);
            g.setFont(new Font("SansSerif",Font.ITALIC,16));
            g.drawString("Загрузка изображения...",(int)dim.getWidth()/2-95,(int)dim.getHeight()/2-55);
        }
    }

    public Dimension getPreferredSize()
    {
        return dim;
    }

    public Dimension getMinimumSize()
    {
        return dim;
    }

    public Dimension preferredSize()
    {
        return dim;
    }

    public Dimension minimumSize()
    {
        return dim;
    }
}
