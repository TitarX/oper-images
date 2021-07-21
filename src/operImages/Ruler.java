/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operImages;

/**
 *
 * @author TitarX
 */
import java.awt.*;
import javax.swing.*;

public class Ruler extends JComponent
{

    public static final int INCH=Toolkit.getDefaultToolkit().getScreenResolution();
    public static final int HORIZONTAL=0;
    public static final int VERTICAL=1;
    public static final int SIZE=35;
    public int orientation;
    public boolean isMetric;
    private int increment;
    private int units;
    private int sizeImg;

    public Ruler(int o,boolean m, int sizeImg)
    {
        orientation=o;
        isMetric=m;
        setIncrementAndUnits();
        this.sizeImg=sizeImg;
    }

    public void setIsMetric(boolean isMetric)
    {
        this.isMetric=isMetric;
        setIncrementAndUnits();
        repaint();
    }

    private void setIncrementAndUnits()
    {
        if(isMetric)
        {
            units=(int)((double)INCH/(double)2.54);
            increment=units;
        }else
        {
            units=INCH;
            increment=units/2;
        }
    }

    public boolean isMetric()
    {
        return this.isMetric;
    }

    public int getIncrement()
    {
        return increment;
    }

    public void setPreferredHeight(int ph)
    {
        setPreferredSize(new Dimension(SIZE,ph));
    }

    public void setPreferredWidth(int pw)
    {
        setPreferredSize(new Dimension(pw,SIZE));
    }

    public void paintComponent(Graphics g)
    {
        Rectangle drawHere=g.getClipBounds();

        //g.setColor(new Color(230,163,4));
        g.setColor(new Color(255,220,0));
        g.fillRect(drawHere.x,drawHere.y,drawHere.width,drawHere.height);
        g.setFont(new Font("SansSerif",Font.PLAIN,10));
        g.setColor(Color.black);

        int end=0;
        int start=0;
        int tickLength=0;
        String text=null;

        if(orientation==HORIZONTAL)
        {
            start=(drawHere.x/increment)*increment;
            end=(((drawHere.x+drawHere.width)/increment)+1)
                    *increment;
        }else
        {
            start=(drawHere.y/increment)*increment;
            end=(((drawHere.y+drawHere.height)/increment)+1)
                    *increment;
        }

        if(start==0)
        {
            text=Integer.toString(0)+(isMetric?" см":" дюйм");
            tickLength=10;
            if(orientation==HORIZONTAL)
            {
                g.setColor(Color.black);
                g.drawLine(0,SIZE-1,0,SIZE-tickLength-1);
                g.drawString(text,2,21);
                g.setColor(Color.red);
                g.drawLine(sizeImg-1,0,sizeImg-1,SIZE);
                g.setColor(Color.black);
            }else
            {
                g.setColor(Color.black);
                g.drawLine(SIZE-1,0,SIZE-tickLength-1,0);
                g.drawString("0",9,10);
                g.setColor(Color.red);
                g.drawLine(0,sizeImg-1,SIZE,sizeImg-1);
                g.setColor(Color.black);
            }
            text=null;
            start=increment;
        }

        for(int i=start;i<end;i+=increment)
        {
            if(i%units==0)
            {
                tickLength=10;
                text=Integer.toString(i/units);
            }else
            {
                tickLength=7;
                text=null;
            }

            if(tickLength!=0)
            {
                if(orientation==HORIZONTAL)
                {
                    g.drawLine(i,SIZE-1,i,SIZE-tickLength-1);
                    if(text!=null)
                    {
                        g.drawString(text,i-3,21);
                    }
                }else
                {
                    g.drawLine(SIZE-1,i,SIZE-tickLength-1,i);
                    if(text!=null)
                    {
                        g.drawString(text,9,i+3);
                    }
                }
            }
        }
    }
}
