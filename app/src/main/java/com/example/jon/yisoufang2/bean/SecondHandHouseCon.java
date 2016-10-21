package com.example.jon.yisoufang2.bean;

/**
 * Created by Jon on 2016/10/17.
 */
public class SecondHandHouseCon {
    private Integer searchPageNum; // 要查询页数

    private Integer pageSize =12;  //默认查询结果数


    private String commName; // 小区名字

    private String houseNum; // 房源号

    private String houseType; // 户型

    private String houseStorey; // 楼层

    private String houseOrientation; // 朝向

    private String houseDecade;// 楼龄

    private String houseDeco; // 装修

    private String houseArea; // 面积条件

    private String regionName; // 区域

    private String cityName; // 城市

    private String houseSellPrice; //售价

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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getHouseSellPrice() {
        return houseSellPrice;
    }

    public void setHouseSellPrice(String houseSellPrice) {
        this.houseSellPrice = houseSellPrice;
    }
}
