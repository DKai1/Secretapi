package com.kevin.secret.util;

import java.io.Serializable;

/**
 * @author dengkai
 */
public class ResultResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;

    public static <T> ResultResponse<T> ok() {
        return (ResultResponse<T>) restResult((Object)null, CommonConst.SUCCESS, (String)null);
    }

    public static <T> ResultResponse<T> ok(T data) {
        return restResult(data, CommonConst.SUCCESS, (String)null);
    }

    public static <T> ResultResponse<T> ok(T data, String msg) {
        return restResult(data, CommonConst.SUCCESS, msg);
    }

    public static <T> ResultResponse<T> failed() {
        return (ResultResponse<T>) restResult((Object)null, CommonConst.FAIL, (String)null);
    }

    public static <T> ResultResponse<T> failed(String msg) {
        return (ResultResponse<T>) restResult((Object)null, CommonConst.FAIL, msg);
    }

    public static <T> ResultResponse<T> failed(T data) {
        return restResult(data, CommonConst.FAIL, (String)null);
    }

    public static <T> ResultResponse<T> failed(T data, String msg) {
        return restResult(data, CommonConst.FAIL, msg);
    }

    private static <T> ResultResponse<T> restResult(T data, int code, String msg) {
        ResultResponse<T> apiResult = new ResultResponse();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    @Override
    public String toString() {
        return "ResultResponse(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }

    public ResultResponse() {
    }

    public ResultResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public ResultResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public ResultResponse<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public ResultResponse<T> setData(T data) {
        this.data = data;
        return this;
    }
}
