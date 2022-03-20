package com.ujskylxwechat.kylxwechat.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * JSON 返回模型
 */
public class Result<D> implements Serializable {

    @JsonProperty("isSuccess")
    private boolean success = false;

    private String status;
    private String message;

    private D data;

    public static <T> Result<T> create() {
        return new Result<T>();
    }

    public boolean isSuccess() {
        return success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Result<D> setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<D> setMessage(String message) {
        this.message = message;
        return this;
    }

    public D getData() {
        return data;
    }

    public Result<D> setData(D data) {
        this.data = data;
        return this;
    }
}
