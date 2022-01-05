package google.guava.eventbus;

public class MyEvent {
    private String msg;
    private int code;

    public MyEvent(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
