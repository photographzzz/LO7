package com.photograph.lo7.httpsender.entity;

public class Response<T> {

    private int code; //状态 0:接口调用成功
    private T data; //当status=0，将返回的数据封装到data中
    private String msg; //提示信息


    private Response() {
    }

    private Response(int code) {
        this.code = code;
    }

    private Response(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private Response(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return this.code == 0;
    }

    public static Response createResponseBySuccess() {
        return new Response(0);
    }

    public static <T> Response createResponseBySuccess(T data) {
        return new Response(0, data);
    }

    public static <T> Response createResponseBySuccess(T data, String msg) {
        return new Response(0, data, msg);
    }

    public static Response createResponseByFail(int status) {
        return new Response(status);
    }

    public static Response createResponseByFail(int status, String msg) {
        return new Response(status, null, msg);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
