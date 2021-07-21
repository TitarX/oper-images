/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package oiException;

/**
 *
 * @author TitarX
 */
public class NotSupportedFormatException extends Exception
{
    public NotSupportedFormatException()
    {
        super();
    }

    public NotSupportedFormatException(Throwable t)
    {
        super(t);
    }

    public NotSupportedFormatException(String str)
    {
        super(str);
    }

    public NotSupportedFormatException(String str,Throwable t)
    {
        super(str,t);
    }
}
