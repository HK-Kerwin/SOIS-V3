package com.tedu.sois.common.vo;

import java.io.Serializable;

/**
 * 通过此对象对控制层数据进行封装,主要目的
 * 是业务的执行结果添加状态信息.
 */

public class JsonResult implements Serializable {

    /**状态码 0表示SUCCESS,1表示ERROR*/
    private Integer code = 0;

    /**状态信息*/
    private String msg = "ok!";

    /**数据长度(通过查询获得)*/
    private Integer count = 1;

    /**当前页的页码值*/
    private Integer curr;

    /**页面大小*/
    private Integer pageSize;

    /**总页数(通过计算获得)*/
    private Integer pageCount;

    /**封装的数据*/
    private Object data;

    public JsonResult() {
    }

    public JsonResult(String massage, Object data) {
        this.data = data;
        this.msg = massage;
    }

    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(String massage) {
        this.msg = massage;
    }

    public JsonResult(Object data) {
        this.data = data;
    }

    public JsonResult(Object data,Integer count) {
        this.count = count;
        this.data = data;
    }

    public JsonResult(Throwable e) {
        this.code = 555;
        this.msg = e.getMessage();
    }

    /** 分页JSON */
    public JsonResult(int page, int pageSize, int count, Object data) {
        this.curr = page;
        this.pageSize = pageSize;
        this.count = count;
        this.pageCount=(count-1)/pageSize+1;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCurr() {
        return curr;
    }

    public void setCurr(Integer curr) {
        this.curr = curr;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", curr=" + curr +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", data=" + data +
                '}';
    }
}
