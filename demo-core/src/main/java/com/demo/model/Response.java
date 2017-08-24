package com.demo.model;


import com.demo.enums.ICode;
import com.demo.enums.ResponseCode;

import java.io.Serializable;

/**
 * 与客户端交互json model
 * Created by sunjz on 2017/4/18.
 */
public class Response implements Serializable {

    private String msg;

    private int code;

    private Object data;

    public Response() {
    }

    public Response(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Response newInstance(ResponseCode responseCode) {
        Response response = new Response();
        response.setCode(responseCode.getCode());
        response.setMsg(responseCode.getMsg());

        return response;
    }

    public static Response getSuccessResponse(Object data) {
        Response response = new Response();
        response.setCode(ResponseCode._000.getCode());
        response.setMsg(ResponseCode._000.getMsg());
        response.setData(data);

        return response;
    }

    public static Response getErrorResponse(ResponseCode responseCode, String param) {
        Response response = new Response();
        response.setCode(responseCode.getCode());
        response.setMsg(String.format(responseCode.getMsg(), param));

        return response;
    }

    public static Response getErrorResponse(int code, String msg) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);

        return response;
    }

    public static Response getErrorResponse(ICode iCode) {
        return getErrorResponse(iCode.getCode(), iCode.getMsg());
    }
}
