package com.billingclient.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Connects to server
 */
public class SenderReceiverTCP implements Sender, Receiver, AutoCloseable
{
    private Socket handler;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public SenderReceiverTCP(String serverHost, int portNumber) throws IOException
    {
        handler = new Socket(serverHost, portNumber);
        getStreams();
    }

    private void getStreams() throws IOException
    {
        output = new ObjectOutputStream(handler.getOutputStream());
        output.flush();
        input = new ObjectInputStream(handler.getInputStream());
    }

    @Override
    public String receive() throws IOException
    {
        try
        {
            return (String) input.readObject();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void send(String message) throws IOException
    {
        output.writeObject(message);
        output.flush();
    }

    @Override
    public void close() throws Exception
    {
        output.close();
        input.close();
        handler.close();
    }
}
