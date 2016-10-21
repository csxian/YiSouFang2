package com.example.jon.yisoufang2.bean;

/**
 * Created by Jon on 2016/10/10.
 */
public class RentHouseSearchCon {
    private Integer searchPageNum; // 要查询页数
    private Integer pageSize = 12; // 页面显示数目
    private Integer totalPage; // 总页数
    private Integer totalRows; // 总结果数

    private String commName; // 小区名字

    private String houseNum; // 房源号

    private String houseType; // 户型

    private String houseStorey; // 楼层

    private String houseOrientation; // 朝向

    private String houseDecade;// 楼龄

    private String houseStatus; // 房源状态

    private String houseDeco; // 装修

    private String houseArea; // 面积条件

    private String regionName; // 区域

    private String cityName; // 城市

    public String getHouseRental() {
        return houseRental;
    }

    public void setHouseRental(String houseRental) {
        this.houseRental = houseRental;
    }

    private String houseRental;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getSearchPageNum() {
        return searchPageNum;
    }

    public void setSearchPageNum(Integer searchPageNum) {
        this.searchPageNum = searchPageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseStorey() {
        return houseStorey;
    }

    public void setHouseStorey(String houseStorey) {
        this.houseStorey = houseStorey;
    }

    public String getHouseOrientation() {
        return houseOrientation;
    }

    public void setHouseOrientation(String houseOrientation) {
        this.houseOrientation = houseOrientation;
    }

    public String getHouseDecade() {
        return houseDecade;
    }

    public void setHouseDecade(String houseDecade) {
        this.houseDecade = houseDecade;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public String getHouseDeco() {
        return houseDeco;
    }

    public void setHouseDeco(String houseDeco) {
        this.houseDeco = houseDeco;
    }

    public String getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
