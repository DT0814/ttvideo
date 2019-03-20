package com.xawl.ttvideo.utils;

import java.util.List;

public class PageInfo<T> {
    private int start;
    private int end;
    private List<T> data;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int currentNum;
    private int[] navigatepageNums;
    private int nextPage;
    private int proPage;
    private long totals;

    public PageInfo() {
    }

    public long getTotals() {
        return totals;
    }

    public void setTotals(long totals) {
        this.totals = totals;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getProPage() {
        return proPage;
    }

    public void setProPage(int proPage) {
        this.proPage = proPage;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }
}
