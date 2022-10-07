package com.markus.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: markus
 * @date: 2022/10/7 4:13 PM
 * @Description: 提供restful接口统一返回数据对象
 *  自定义响应数据结构
 * 				本类可提供给 H5/ios/安卓/公众号/小程序 使用
 *  			前端接受此类数据（json object)后，可自行根据业务去实现相关功能
 *
 *  			200：表示成功
 *  			500：表示错误，错误信息在msg字段中
 *  			501：bean验证错误，不管多少个错误都以map形式返回
 *  			502：拦截器拦截到用户token出错
 *  			555：异常抛出信息
 *      		556: 用户qq校验异常
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class CommonReturnResult {
    private static final ObjectMapper Mapper = new ObjectMapper();

    private Integer status;

    private String msg;

    private Object data;

    @JsonIgnore
    private String ok;

    public static CommonReturnResult build(Integer status, String msg, Object data) {
        return new CommonReturnResult(status, msg, data);
    }

    public static CommonReturnResult build(Integer status, String msg, Object data, String ok) {
        return new CommonReturnResult(status, msg, data, ok);
    }

    public static CommonReturnResult ok(Object data) {
        return new CommonReturnResult(data);
    }

    public static CommonReturnResult ok() {
        return new CommonReturnResult(null);
    }

    public static CommonReturnResult errorMsg(String msg) {
        return new CommonReturnResult(500, msg, null);
    }

    public static CommonReturnResult errorMap(Object data) {
        return new CommonReturnResult(501, "error", data);
    }

    public static CommonReturnResult errorTokenMsg(String msg) {
        return new CommonReturnResult(502, msg, null);
    }

    public static CommonReturnResult errorException(String msg) {
        return new CommonReturnResult(555, msg, null);
    }

    public static CommonReturnResult errorUserQQ(String msg) {
        return new CommonReturnResult(556, msg, null);
    }


    public CommonReturnResult() {
    }

    public CommonReturnResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public CommonReturnResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public CommonReturnResult(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
