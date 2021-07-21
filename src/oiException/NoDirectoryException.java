/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oiException;

/**
 *
 * @author TitarX
 */
public class NoDirectoryException extends Exception
{
    public NoDirectoryException()
    {
        super();
    }

    public NoDirectoryException(Throwable t)
    {
        super(t);
    }

    public NoDirectoryException(String str)
    {
        super(str);
    }

    public NoDirectoryException(String str,Throwable t)
    {
        super(str,t);
    }
}
