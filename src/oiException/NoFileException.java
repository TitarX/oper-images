/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oiException;

/**
 *
 * @author TitarX
 */
public class NoFileException extends Exception
{
    public NoFileException()
    {
        super();
    }

    public NoFileException(Throwable t)
    {
        super(t);
    }

    public NoFileException(String str)
    {
        super(str);
    }

    public NoFileException(String str,Throwable t)
    {
        super(str,t);
    }
}
