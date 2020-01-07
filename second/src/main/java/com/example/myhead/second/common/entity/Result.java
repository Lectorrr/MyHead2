package com.example.myhead.second.common.entity;

/**
 * @author lector
 */
public class Result {

    /**
     * 响应码
     */
    private int code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 状态
     */
    private boolean success;

    /**
     * 返回数据
     */
    private Object data;

    public Result(){

    }
    public Result(int code, boolean success, Object data, String message) {
        this.code = code;
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        this.message = this.success ? "操作成功！" : "操作失败，请检查操作是否合规！";
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
