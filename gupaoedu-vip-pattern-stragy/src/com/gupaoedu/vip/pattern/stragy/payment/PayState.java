package com.gupaoedu.vip.pattern.stragy.payment;

public class PayState {
    private int code;
    private String msg;
    private Object data;

   public PayState(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "PayState{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
