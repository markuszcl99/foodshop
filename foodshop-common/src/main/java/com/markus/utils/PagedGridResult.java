package com.markus.utils;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: markus
 * @date: 2022/10/15 9:37 PM
 * @Description: 分页数据封装类
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class PagedGridResult {

    private int page;            // 当前页数
    private int total;            // 总页数
    private long records;        // 总记录数
    private List<?> rows;        // 当前页每行显示的内容

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public static PagedGridResult getPagedGridResult(List<?> list, int page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page); // 当前查询页的页码
        grid.setRows(list); // 每页的数据内容
        grid.setTotal(pageList.getPages());// 总页数
        grid.setRecords(pageList.getTotal());// 总记录数
        return grid;
    }
}

