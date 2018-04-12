package com.zzwdkj.gprs;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author guoxianwei 2016-09-17 15:19
 */
public class MessageProtocalDecoder extends ProtocolDecoderAdapter {

    private Logger log = Logger.getLogger(MessageProtocalDecoder.class);

    private Charset charset;

    public MessageProtocalDecoder(Charset charset) {
        this.charset = charset;
    }
    
    @Override
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {

        String writeStr=in.getHexDump().replaceAll(" ","");
        System.out.println("\r\n获得的转换字符串是："+writeStr);
        String sms = ioBufferToString(in);
        //String sms = "*E"+"\r\n"+" "+",�,�#";
        if (sms.equals("$") || (sms.startsWith("{") && sms.endsWith("}")) || (sms.startsWith("[") && sms.endsWith("]")) || (sms.startsWith("*") && sms.endsWith("#"))) {
            if ((sms.startsWith("*") && sms.endsWith("#"))) {
                out.write(writeStr.toLowerCase());
            } else {
                out.write(sms);
            }
            return;
        } else {
            out.write(ioBufferToByte(in));
            return;
        }
    }


    public static byte[] ioBufferToByte(Object message) {
        if (!(message instanceof IoBuffer)) {
            return null;
        }
        IoBuffer ioBuffer = (IoBuffer) message;
        byte[] b = new byte[ioBuffer.limit()];
        ioBuffer.get(b);
        return b;
    }

    public static String ioBufferToString(Object message) {
        if (!(message instanceof IoBuffer)) {
            return "";
        }
        IoBuffer ioBuffer = (IoBuffer) message;
        byte[] b = new byte[ioBuffer.limit()];
        ioBuffer.get(b);
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < b.length; i++) {

            stringBuffer.append((char) b[i]);
        }
        return stringBuffer.toString();
    }

    public static String ioBufferToString2(Object message) {
        IoBuffer ioBuffer = (IoBuffer) message;
        System.out.println("message = " + message + ioBuffer.limit());
        ioBuffer.flip();    //调换buffer当前位置，并将当前位置设置成0
        byte[] b = new byte[ioBuffer.limit()];
        ioBuffer.get(b);
        //此处用stringbuffer是因为　String类是字符串常量，是不可更改的常量。而StringBuffer是字符串变量，它的对象是可以扩充和修改的。
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            System.out.println("====" + b[i]);
            stringBuffer.append((Byte) b[i]); //可以根据需要自己改变类型
            System.out.println(b[i] + "---------" + i);
        }
        return stringBuffer.toString();
    }

    public static String ioBufferToString3(IoBuffer iobuffer) {
        System.out.println("message = " + iobuffer + iobuffer.limit());
        iobuffer.flip();    //调换buffer当前位置，并将当前位置设置成0
        byte[] b = new byte[iobuffer.limit()];
        iobuffer.get(b);
        //此处用stringbuffer是因为　String类是字符串常量，是不可更改的常量。而StringBuffer是字符串变量，它的对象是可以扩充和修改的。
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < b.length; i++) {
            System.out.println("====" + b[i]);
            stringBuffer.append((Byte) b[i]); //可以根据需要自己改变类型
            System.out.println(b[i] + "---------" + i);
        }
        return stringBuffer.toString();
    }

    public String ioBufferToString4(Object message) {
        String str = message.toString();
        IoBuffer result = (IoBuffer) message;
        byte[] result1 = new byte[1024];
        result.put(result1);
        StringBuilder sb = new StringBuilder();
        for (byte b : result1) {
            sb.append(b);
        }
        System.out.println("转化的二进制数据：" + sb);
        System.out.println(ChargeProtocolHandler.StringToHex(str));
        return sb.toString();
    }

    public static String str2HexStr(String str)
    {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++)
        {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

}
