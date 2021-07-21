/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operImages;

/**
 *
 * @author TitarX
 */
import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.io.File;

public class ImagePreview extends JComponent implements PropertyChangeListener
{

    ImageIcon thumbnail=null;
    File file=null;

    public ImagePreview(JFileChooser fc)
    {
        setPreferredSize(new Dimension(100,50));
        fc.addPropertyChangeListener(this);
    }

    public void loadImage()
    {
        if(file==null)
        {
            thumbnail=null;
            return;
        }

        ImageIcon tmpIcon=new ImageIcon(file.getPath());
        if(tmpIcon!=null)
        {
            if(tmpIcon.getIconWidth()>90)
            {
                thumbnail=new ImageIcon(tmpIcon.getImage().getScaledInstance(90,-1,Image.SCALE_DEFAULT));
            }else
            {
                thumbnail=tmpIcon;
            }
        }
    }

    public void propertyChange(PropertyChangeEvent e)
    {
        boolean update=false;
        String prop=e.getPropertyName();

        if(JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop))
        {
            file=null;
            update=true;

        }else if(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop))
        {
            file=(File)e.getNewValue();
            update=true;
        }

        if(update)
        {
            thumbnail=null;
            if(isShowing())
            {
                loadImage();
                repaint();
            }
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(thumbnail==null)
        {
            loadImage();
        }
        if(thumbnail!=null)
        {
            int x=getWidth()/2-thumbnail.getIconWidth()/2;
            int y=getHeight()/2-thumbnail.getIconHeight()/2;

            if(y<0)
            {
                y=0;
            }

            if(x<5)
            {
                x=5;
            }
            thumbnail.paintIcon(this,g,x,y);
        }
    }
}
