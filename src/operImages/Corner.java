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

public class Corner extends JComponent
{

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(255,220,0));
        g.fillRect(0,0,getWidth(),getHeight());
    }
}
