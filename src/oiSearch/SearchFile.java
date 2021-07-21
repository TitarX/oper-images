/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oiSearch;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

public class SearchFile implements Runnable
{

    private Pattern p=null;
    private Matcher m=null;
    private Thread t=null;
    private ArrayList res;
    private File topDirectory;

    public List find(String startPath,String mask) throws Exception
    {
        topDirectory=new File(startPath);

        if(topDirectory.isDirectory())
        {
            if(t!=null&&t.isAlive())
            {
                t.destroy();
                t=null;
                t=new Thread(this,"Дочерний поток поиска");
            }else
            {
                t=new Thread(this,"Дочерний поток поиска");
            }

            p=Pattern.compile(mask,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);

            res=new ArrayList(100);

            t.start();
            try
            {
                t.join();
            }catch(InterruptedException ex)
            {
                Logger.getLogger(SearchFile.class.getName()).log(Level.SEVERE,null,ex);
            }
        }else
        {
            throw new Exception("Указанный путь не существует");
        }

        return res;
    }

    private boolean accept(String name)
    {
        m=p.matcher(name);

        if(m.matches())
        {
            return true;
        }else
        {
            return false;
        }
    }

    private void search(File topDirectory,List res)
    {

        File[] list=topDirectory.listFiles();

        if(list!=null)
        {

            for(int i=0;i<list.length;i++)
            {

                if(list[i].isDirectory())
                {
                    search(list[i],res);
                }else
                {

                    if(accept(list[i].getName()))
                    {
                        res.add(list[i]);
                    }
                }
            }
        }
    }

    public void run()
    {
        search(topDirectory,res);
    }
}
