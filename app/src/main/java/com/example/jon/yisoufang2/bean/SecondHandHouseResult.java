package com.example.jon.yisoufang2.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jon on 2016/10/17.
 */
public class SecondHandHouseResult {

    private String status; // 查询结果状态

    private int currentPageNum; // 当前页数

    private int pageSize = 12; // 每页显示数目

    private int totalRows; // 总结果数

    private int totalPages; // 总页数

    private List<HouseSecondInfo> secondHandInfoList; // 租房数据


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<HouseSecondInfo> getSecondHandInfoList() {
        return secondHandInfoList;
    }

    public void setSecondHandInfoList(List<HouseSecondInfo> secondHandInfoList) {
        this.secondHandInfoList = secondHandInfoList;
    }

    public static class HouseSecondInfo implements Serializable{

        private String houseNum;  //房源编号

        private String houseTitle; //房源标题

        private String commName;   //小区名字

        private String houseType;  //房子户型

        private int houseArea;  //房子面积

        private String houseDeco;  //房子装修

        private String floorType; //房子楼型

        private String houseOrientation; //房子朝向

        private String commRegionDetail;  //区域细化

        private String houseStorey;  //楼层

        private String houseDecade;// 建楼年份

        private int houseSellPrice; // 房子售价

        private int houseAvgPrice;  //房子均价

        private String pictureUrl;

        public String getHouseNum() {
            return houseNum;
        }

        public void setHouseNum(String houseNum) {
            this.houseNum = houseNum;
        }

        public String getHouseTitle() {
            return houseTitle;
        }

        public void setHouseTitle(String houseTitle) {
            this.houseTitle = houseTitle;
        }

        public String getCommName() {
            return commName;
        }

        public void setCommName(String commName) {
            this.commName = commName;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public int getHouseArea() {
            return houseArea;
        }

        public void setHouseArea(int houseArea) {
            this.houseArea = houseArea;
        }

        public String getHouseDeco() {
            return houseDeco;
        }

        public void setHouseDeco(String houseDeco) {
            this.houseDeco = houseDeco;
        }

        public String getFloorType() {
            return floorType;
        }

        public void setFloorType(String floorType) {
            this.floorType = floorType;
        }

        public String getHouseOrientation() {
            return houseOrientation;
        }

        public void setHouseOrientation(String houseOrientation) {
            this.houseOrientation = houseOrientation;
        }

        public String getCommRegionDetail() {
            return commRegionDetail;
        }

        public void setCommRegionDetail(String commRegionDetail) {
            this.commRegionDetail = commRegionDetail;
        }

        public String getHouseStorey() {
            return houseStorey;
        }

        public void setHouseStorey(String houseStorey) {
            this.houseStorey = houseStorey;
        }

        public String getHouseDecade() {
            return houseDecade;
        }

        public void setHouseDecade(String houseDecade) {
            this.houseDecade = houseDecade;
        }

        public int getHouseSellPrice() {
            return houseSellPrice;
        }

        public void setHouseSellPrice(int houseSellPrice) {
            this.houseSellPrice = houseSellPrice;
        }

        public int getHouseAvgPrice() {
            return houseAvgPrice;
        }

        public void setHouseAvgPrice(int houseAvgPrice) {
            this.houseAvgPrice = houseAvgPrice;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }
    }
}
