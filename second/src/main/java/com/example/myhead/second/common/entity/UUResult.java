package com.example.myhead.second.common.entity;

public class UUResult {
    private boolean success;

    private Object data;

    private String message;

    public UUResult(){

    }

    public UUResult(boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        this.message = this.success ?"操作成功！":"操作失败，请检查操作是否合规！";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
