package com.zzwdkj.common;

/**
 * 向后台extjs传递信息
 *
 * @author guoxianwei 2015-07-10 14:20
 */
public class ExtMsg {

    private boolean success;
    private String msg;

    public ExtMsg() {
        this.success = true;
    }

    public ExtMsg(String message) {
        this();
        this.msg = message;
    }

    public ExtMsg(boolean success, String message) {
        this.success = success;
        this.msg = message;
}

    public static ExtMsg success() {
        return new ExtMsg();
    }

    public static ExtMsg success(String message) {
        return new ExtMsg(message);
    }

    public static ExtMsg fail(String message) {
        return new ExtMsg(false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String message) {
        this.msg = message;
    }
}
