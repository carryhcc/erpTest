package com.example.util;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/11/10 16:49
 */

import com.github.pagehelper.PageInfo;

import java.util.LinkedHashMap;

/**
 * 统一API响应结果封装
 */
public class Result extends LinkedHashMap<String, Object> {

    public Result() {
    }

    public Result putMsg(String msg) {
        return this.set("msg", msg);
    }

    public Result putTitle(String title) {
        return this.set("title", title);
    }

    public Result putData(Object data) {
        return this.set("data", data);
    }

    public Object getData() {
        return this.get("data");
    }

    public Result putTotal(int total) {
        return this.set("total", total);
    }

    public Result putTotal(long total) {
        return this.set("total", total);
    }

    public Result putRows(Object rows) {
        return this.set("rows", rows);
    }

    public Result success(boolean success) {
        return this.set("success", success);
    }


    public Result set(String key, Object value) {
        this.put(key, value);
        return this;
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Result setPagionation(PageInfo pagionation, Object data) {
        this.set("list", data);
        this.set("pageSize", pagionation.getSize());
        this.set("pageNo", pagionation.getPageNum());
        this.set("next", pagionation.isHasNextPage() ? pagionation.getPageNum() + 1 : pagionation.getPageNum());
        this.set("last", pagionation.getPages());
        this.set("first", Integer.valueOf(1));
        this.set("prev", pagionation.isHasPreviousPage() ? pagionation.getPageNum() - 1 : pagionation.getPageNum());
        this.set("count", pagionation.getTotal());
        return this;
    }

    public static Result newSuccess() {
        return (new Result()).success(true);
    }

    public static Result newSuccess(Object data) {
        return (new Result()).success(true).putData(data);
    }

    public static Result newFail(String errorMsg) {
        return (new Result()).success(false).putMsg(errorMsg);
    }


    public static Object newEasyuiResult(long total, Object rows) {
        return (new Result()).putTotal(total).putRows(rows);
    }

    public static Object newEasyuiResult(int total, Object rows) {
        return (new Result()).putTotal(total).putRows(rows);
    }
}
