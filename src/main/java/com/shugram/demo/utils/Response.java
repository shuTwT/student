package com.shugram.demo.utils;

public class Response<T> {
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    private T data;
    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(200);
        response.setMessage("success");
        response.setData(data);
        return response;
    }
    public static <T> Response<T> fail(int code, String message) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
