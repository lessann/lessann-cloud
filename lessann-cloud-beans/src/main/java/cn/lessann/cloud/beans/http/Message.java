package cn.lessann.cloud.beans.http;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/26 3:16 下午
 */
public class Message {

    private String msg;
    private boolean err;
    private Object obj;

    public Message() {
    }

    public Message(String msg) {
        this.msg = msg;
        this.err = true;
    }

    public Message(String msg, Object obj) {
        this.msg = msg;
        this.err = true;
        this.obj = obj;
    }

    public Message(String msg, boolean err) {
        this.msg = msg;
        this.err = err;
    }

    public Message(String msg, boolean err, Object obj) {
        this.msg = msg;
        this.err = err;
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isErr() {
        return err;
    }

    public void setErr(boolean err) {
        this.err = err;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
