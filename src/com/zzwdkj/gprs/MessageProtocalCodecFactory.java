package com.zzwdkj.gprs;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * @author guoxianwei 2016-09-17 15:19
 */
public class MessageProtocalCodecFactory implements ProtocolCodecFactory {

    private final MessageProtocalDecoder decoder;

    private final MessageProtocalEncoder encoder;

    public MessageProtocalCodecFactory(Charset charset) {
        this.decoder = new MessageProtocalDecoder(charset);
        this.encoder = new MessageProtocalEncoder(charset);
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }

    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }

}
