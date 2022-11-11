package com.sharing.cn.common;


import java.io.Serializable;
import java.util.List;

public class Query<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private T query;

    public Query() {
    }

    public Query(T query) {
        this.query = query;
    }

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }

    private List<Sort> sortList;

    public List<Sort> getSortList() {
        return sortList;
    }

    public void setSortList(List<Sort> sortList) {
        this.sortList = sortList;
    }

}
