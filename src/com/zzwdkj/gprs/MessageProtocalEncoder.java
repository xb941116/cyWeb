package com.zzwdkj.gprs;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/**
 * @author guoxianwei 2016-09-17 15:26
 */
public class MessageProtocalEncoder extends ProtocolEncoderAdapter {

    private Charset charset;

    public MessageProtocalEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput out) throws Exception {

        if(o instanceof String){
            // new buf
            IoBuffer buf = IoBuffer.allocate(1024).setAutoExpand(true);
            // object --> AbsMP
            String absMp = (String) o;
            //System.out.println(absMp);
            buf.putString(absMp, charset.newEncoder());
            buf.flip();
            out.write(buf);
        }else if (o instanceof byte[]){
            // new buf
            IoBuffer buf = IoBuffer.allocate(1024).setAutoExpand(true);
            // object --> AbsMP
            byte[] absMp = (byte[]) o;
            //System.out.println(absMp);
            buf.put(absMp);
            buf.flip();
            out.write(buf);
        }

    }
}
