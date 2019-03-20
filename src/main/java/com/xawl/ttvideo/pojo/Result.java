package com.xawl.ttvideo.pojo;


public class Result {
    private int code;
    private String msg;
    private Object data;

    public Result() {

    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this(code, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result success(int code, String msg) {
        return new Result(code, msg, null);
    }

    public static Result success(Object data) {
        return new Result(200, "ok", data);
    }

    public static Result err(int code, String msg) {
        return new Result(code, msg, null);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
