package com.zzwdkj.gprs;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 会话保持工厂实现
 *
 * @author guoxianwei 2016-04-01 17:50
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

    private final static Logger LOGGER = Logger.getLogger(KeepAliveMessageFactoryImpl.class);
    private static final String RESPONSE = "$$$";

    @Override
    public boolean isRequest(IoSession ioSession, Object message) {
        String theMessage="";
        boolean isRequest = false;
        if(message instanceof String){
            theMessage=message.toString();
            if (theMessage != null && theMessage.startsWith("$")) {
                isRequest = true;
            }
        }else if (message instanceof IoBuffer){
            isRequest=false;
        }
        return isRequest;
    }

    @Override
    public boolean isResponse(IoSession ioSession, Object message) {
        return false;
    }

    @Override
    public Object getRequest(IoSession ioSession) {
        return null;
    }

    @Override
    public Object getResponse(IoSession ioSession, Object message) {
        if (message instanceof IoBuffer){
            return null;
        }
        String theMessage = (String) message;
        if (theMessage != null && (theMessage.startsWith("$") || theMessage.startsWith("WD"))) {
            return "#";
        }
        return null;
    }




    public static String BytesHexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }
}
