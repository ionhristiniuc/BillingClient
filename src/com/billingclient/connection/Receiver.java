package com.billingclient.connection;

import java.io.IOException;

public interface Receiver
{
    String receive() throws IOException;
}
