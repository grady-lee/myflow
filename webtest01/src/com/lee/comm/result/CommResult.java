package com.lee.comm.result;

public class CommResult<T> {

    private boolean success;
    private int code;
    private T result;
    private String message;

    public CommResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CommResult(boolean success, T result, String message) {
        this.success = success;
        this.result = result;
        this.message = message;
    }

    public static <T> CommResult<T> success(T data) {
        return new CommResult<>(true, data, null);
    }

    public static <T> CommResult<T> fail(String message) {
        return new CommResult<>(false, null, message);
    }

}
