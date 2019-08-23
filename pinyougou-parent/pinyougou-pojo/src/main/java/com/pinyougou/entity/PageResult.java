package com.pinyougou.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果 分装对象
 */
public class PageResult<T> implements Serializable {
    private long total;//总记录数
    private List<T> rows;//当前页结果

    public PageResult(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
