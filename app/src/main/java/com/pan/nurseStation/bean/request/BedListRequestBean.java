package com.pan.nurseStation.bean.request;

public class BedListRequestBean {

    /**
     * 责任护士工号
     */
    private String number;

    /**
     * 病床号/姓名/住院号
     */
    private String search;
    /**
     * 护理级别，0表示全部
     */
    private String level;
    /**
     * 当前页
     */
    private int page;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "BedListRequestBean{" +
                "number='" + number + '\'' +
                ", search='" + search + '\'' +
                ", level='" + level + '\'' +
                ", page=" + page +
                '}';
    }
}
