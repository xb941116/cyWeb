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

import com.zzwdkj.gprs.ChargeCmd;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * �������
 */
public class   ChargeClientHandler extends IoHandlerAdapter {

    private final static Logger LOGGER = Logger.getLogger(ChargeClientHandler.class);

    public interface Callback {

        void connected();

        void disconnected();

        void messageReceived(String message);

        void error(String message);
    }

    private final Callback callback;

    public ChargeClientHandler(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        LOGGER.info("received: " + message);
        String theMessage = (String) message;
        String theCommand = theMessage.substring(0, 3);
        try {
            ChargeCmd command = ChargeCmd.valueOf(theCommand);
            switch (command) {
//                case C01:
//                    break;
//                case C02:
//                    break;
//                case C03:
//                    break;
//                case C04:
//                    break;
//                case C07:
                   // break;
                default:
                    LOGGER.info("Unhandled command: " + command);
                    break;
            }
        } catch (IllegalArgumentException e) {
            LOGGER.debug("Illegal argument", e);
        }
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        callback.disconnected();
    }


    @Override
    public void sessionOpened(IoSession session) throws Exception {
        callback.connected();
    }

}
