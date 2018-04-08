package com.taxation.api.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  @author zjw
 */
public class PaginationDto<T> implements Serializable {

    /**
     * 总记录数
     */
    private int totalCount;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 当前页数
     */
    private int currentPage;

    /**
     * 数据集合
     */
    private List<T> data;

    public PaginationDto() {
        this.data = new ArrayList<T>();
    }

    public PaginationDto(int page, int pageSize) {
        this();
        this.currentPage = page;
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return pageSize == 0 || totalCount == 0 ? 0 : (int) Math.ceil(this.totalCount * 1.0 / this.pageSize);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        if (data == null) {
            this.data.clear();
        } else {
            this.data = data;
        }
    }
}
