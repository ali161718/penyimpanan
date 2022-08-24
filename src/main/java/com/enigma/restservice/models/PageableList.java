package com.enigma.restservice.models;

import java.util.List;

public class PageableList<T> {

    private List<T> list;
    private Integer page;
    private Integer size;
    private long total;

    public PageableList(List<T> list, Integer page, Integer size, long total) {
        this.list = list;
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
