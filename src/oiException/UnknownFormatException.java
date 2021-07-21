/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oiException;

/**
 *
 * @author TitarX
 */
public class UnknownFormatException extends Exception
{
    public UnknownFormatException()
    {
        super();
    }

    public UnknownFormatException(Throwable t)
    {
        super(t);
    }

    public UnknownFormatException(String str)
    {
        super(str);
    }

    public UnknownFormatException(String str,Throwable t)
    {
        super(str,t);
    }
}
