/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operImages;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author TitarX
 */
public class File_Filter extends FileFilter
{
    String fileType;
    String suffix;

    public File_Filter(String fileType,String suffix)
    {
        this.fileType=fileType;
        this.suffix=suffix;
    }

    public boolean accept(File f)
    {
        if(f==null)
        {
            return false;
        }
        if(f.isDirectory())
        {
            return true;
        }else
        {
            return (f.getName().endsWith(suffix));
        }
    }

    public String getDescription()
    {
        return fileType;
    }
}
