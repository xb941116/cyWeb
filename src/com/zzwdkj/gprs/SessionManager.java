package com.zzwdkj.gprs;

import org.apache.mina.core.session.IoSession;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author guoxianwei 2016-04-01 15:55
 */
public class SessionManager {


    private static final Map<String, IoSession> sessions = Collections.synchronizedMap(new HashMap<String, IoSession>());

    public static void putSession(String devno, IoSession session) {
        session.setAttribute("gprsNo", devno);
        sessions.put(devno, session);
    }

    public static IoSession getSession(String devno) {
        return sessions.get(devno);
    }

    public static void removeSession(String devno) {
        sessions.remove(devno);
    }

    /**
     * @return  在线会话数量
     */
    public static int getCount() {
        return sessions.size();
    }

}
