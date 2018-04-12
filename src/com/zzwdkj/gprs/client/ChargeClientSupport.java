/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package com.zzwdkj.gprs.client;

import java.net.SocketAddress;

import javax.net.ssl.SSLContext;

import com.zzwdkj.gprs.ssl.BogusSslContextFactory;
import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * A simple chat client for a given user.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public class ChargeClientSupport {

    private final static Logger LOGGER = Logger.getLogger(ChargeClientSupport.class);

    private final IoHandler handler;

    private final String devno;

    private IoSession session;

    public ChargeClientSupport(String devno, IoHandler handler) {
        if (devno == null) {
            throw new IllegalArgumentException("devno can not be null");
        }
        this.devno = devno;
        this.handler = handler;
    }

    public boolean connect(NioSocketConnector connector, SocketAddress address,
                           boolean useSsl) {
        if (session != null && session.isConnected()) {
            throw new IllegalStateException(
                    "Already connected. Disconnect first.");
        }

        try {
            IoFilter LOGGING_FILTER = new LoggingFilter();

            IoFilter CODEC_FILTER = new ProtocolCodecFilter(
                    new TextLineCodecFactory());

            connector.getFilterChain().addLast("mdc", new MdcInjectionFilter());
            connector.getFilterChain().addLast("codec", CODEC_FILTER);
            connector.getFilterChain().addLast("logger", LOGGING_FILTER);

            if (useSsl) {
                SSLContext sslContext = BogusSslContextFactory
                        .getInstance(false);
                SslFilter sslFilter = new SslFilter(sslContext);
                sslFilter.setUseClientMode(true);
                connector.getFilterChain().addFirst("sslFilter", sslFilter);
            }

            connector.setHandler(handler);
            ConnectFuture future1 = connector.connect(address);
            future1.awaitUninterruptibly();
            if (!future1.isConnected()) {
                return false;
            }
            session = future1.getSession();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void quit() {
        if (session != null) {
            if (session.isConnected()) {
                doS05(session, devno);
                // Wait until the chat ends.
                session.getCloseFuture().awaitUninterruptibly();
            }
            session.close(true);
        }
    }

    public void send(String message) {
        session.write(message);
    }

    /**
     * 关闭连接指令
     *
     * @param session IoSession
     * @param devno   设备编号
     */
    public void doS05(IoSession session, String devno) {
        LOGGER.info("服务器向设备发送关闭连接指令");
        StringBuilder sb = new StringBuilder("(");
        sb.append("S05").
                append(devno).append(")");
        session.write(sb.toString());
    }
}
