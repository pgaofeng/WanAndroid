package com.example.wanandroid.bean;

/**
 * @author gaofengpeng
 * @date 2019/7/30
 * @description : 基础返回对象
 */
public class BaseResponse<T> {

    private T data;
    private String errorMsg;
    private int errorCode;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
