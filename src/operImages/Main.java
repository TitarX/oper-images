/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operImages;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author TitarX
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {

            public void run()
            {
                FiltersForm ff=new FiltersForm("OperImages");
                ff.setMinimumSize(new Dimension(480,360));
                ff.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize().getSize());
                ff.setSize(640,480);
                ff.setLocationRelativeTo(null);
                ff.setVisible(true);
                ff.setFF(ff);
            }
        });
    }
}
