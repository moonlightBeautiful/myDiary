package com.java1234.model;

public class PageBean {

    /**
     * 当前页
     */
    private int page;
    /**
     * 每页大小
     */
    private int pageSize;
    /**
     * 起始页
     */
    private int start;


    public PageBean(int page, int pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (page - 1) * pageSize;
    }


}
