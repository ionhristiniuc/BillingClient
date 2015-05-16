package com.billingclient.connection;

/**
 * Constants used to interact with server
 */
public interface ConnectionConstants
{
    int SERVER_PORT = 12345;
    int MAX_PACKET_LENGTH = 100;
    String SEPARATOR = "|";
    String CONNECT = "CONN";
    String DISCONNECT = "DISCONN";

    // operations
    String CALL = "CALL";
    String BALANCE = "BAL";
}
