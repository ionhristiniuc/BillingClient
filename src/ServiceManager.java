import com.billingclient.connection.SenderReceiverTCP;

import java.io.IOException;
import java.util.regex.Pattern;

import static com.billingclient.connection.ConnectionConstants.*;

/**
 * Interacts with server
 */
public class ServiceManager
{
    private SenderReceiverTCP handler;

    ServiceManager( String host, int portNumber ) throws IOException
    {
        handler = new SenderReceiverTCP(host, portNumber);
    }

    boolean authenticate(String number) throws IOException
    {
        handler.send(CONNECT + SEPARATOR + number);
        String response = handler.receive();
        if (response.equals(CONNECT))
            return true;
        else
            return false;
    }

    public void sendMessage( String message )
    {
        try
        {
            handler.send(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String receiveMessage()
    {
        try
        {
            return handler.receive();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

//    String getBalance() throws IOException
//    {
//        handler.send(BALANCE);
//        String response = handler.receive();
//        String data[] = response.split(Pattern.quote(SEPARATOR));
//        if (data.length > 0 && data[0].equals(BALANCE))
//        {
//            return data[1];
//        }
//
//        return null;
//    }


}

