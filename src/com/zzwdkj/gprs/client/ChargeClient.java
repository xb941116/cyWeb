package com.zzwdkj.gprs.client;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * ���GPRSģ�飬�ͻ��˵��ó���
 */
public class ChargeClient implements ChargeClientHandler.Callback {

    private final static Logger LOGGER = Logger.getLogger(ChargeClient.class);

    private ChargeClientSupport client;

    private ChargeClientHandler handler;

    private NioSocketConnector connector;

    public ChargeClient() {
        connector = new NioSocketConnector();
    }

    public void connect(String devno, String serverAddress, boolean isUseSsl) {

        SocketAddress address = parseSocketAddress(serverAddress);

        handler = new ChargeClientHandler(ChargeClient.this);

        client = new ChargeClientSupport(devno, handler);

        if (!client.connect(connector, address, isUseSsl))

        {
            //����ʧ��
            LOGGER.info("����ʧ�ܣ������豸���:" + devno + " " + serverAddress);
        }
    }

    public void close() {
        if (client != null) {
            client.quit();
        }
        connector.dispose();
    }

    public void send(String message) {
        if (client != null) {
            client.send(message);
        }
    }

    private SocketAddress parseSocketAddress(String s) {
        s = s.trim();
        int colonIndex = s.indexOf(":");
        if (colonIndex > 0) {
            String host = s.substring(0, colonIndex);
            int port = parsePort(s.substring(colonIndex + 1));
            return new InetSocketAddress(host, port);
        } else {
            int port = parsePort(s.substring(colonIndex + 1));
            return new InetSocketAddress(port);
        }
    }

    private int parsePort(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Illegal port number: " + s);
        }
    }

    public void connected() {
    }

    public void error(String message) {
    }

    public void messageReceived(String message) {
    }

    @Override
    public void disconnected() {
    }

}
