package com.example.jon.yisoufang2.bean;

import java.util.List;

/**
 * Created by Jon on 2016/10/16.
 */
public class RentDetailResult {

    /**
     * status : success
     * pictures : ["images/1.jpg","images/1.jpg","images/1.jpg"]
     * houseTitle : 南源花园 间隔方正 温馨舒适 安静宜居
     * houseRental : 2200
     * houseType : 1室1厅
     * houseArea : 35
     * houseStorey : 低楼层
     * houseOrientation : 南北
     * houseDeco : 精装
     * houseDecade : 1998
     * floorType : 塔楼
     * rentWay : 整租
     * houseNum : GZZ0000000001
     * commName : 南源花园
     * subwayInfo : 距离8号线赤岗站545米
     * evaluation : 假装有房评
     * agentId : 1
     * agentName : 经纪人
     * agentPhone : 123232329
     */

    private String status;
    private String houseTitle;
    private int houseRental;
    private String houseType;
    private int houseArea;
    private String houseStorey;
    private String houseOrientation;
    private String houseDeco;
    private String houseDecade;
    private String floorType;
    private String rentWay;
    private String houseNum;
    private String commName;
    private String subwayInfo;
    private String evaluation;
    private int agentId;
    private String agentName;
    private String agentPhone;
    private List<String> pictures;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHouseTitle() {
        return houseTitle;
    }

    public void setHouseTitle(String houseTitle) {
        this.houseTitle = houseTitle;
    }

    public int getHouseRental() {
        return houseRental;
    }

    public void setHouseRental(int houseRental) {
        this.houseRental = houseRental;
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

    public String getHouseDeco() {
        return houseDeco;
    }

    public void setHouseDeco(String houseDeco) {
        this.houseDeco = houseDeco;
    }

    public String getHouseDecade() {
        return houseDecade;
    }

    public void setHouseDecade(String houseDecade) {
        this.houseDecade = houseDecade;
    }

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public String getRentWay() {
        return rentWay;
    }

    public void setRentWay(String rentWay) {
        this.rentWay = rentWay;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    public String getSubwayInfo() {
        return subwayInfo;
    }

    public void setSubwayInfo(String subwayInfo) {
        this.subwayInfo = subwayInfo;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "RentDetailResult{" +
                "status='" + status + '\'' +
                ", houseTitle='" + houseTitle + '\'' +
                ", houseRental=" + houseRental +
                ", houseType='" + houseType + '\'' +
                ", houseArea=" + houseArea +
                ", houseStorey='" + houseStorey + '\'' +
                ", houseOrientation='" + houseOrientation + '\'' +
                ", houseDeco='" + houseDeco + '\'' +
                ", houseDecade='" + houseDecade + '\'' +
                ", floorType='" + floorType + '\'' +
                ", rentWay='" + rentWay + '\'' +
                ", houseNum='" + houseNum + '\'' +
                ", commName='" + commName + '\'' +
                ", subwayInfo='" + subwayInfo + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", agentId=" + agentId +
                ", agentName='" + agentName + '\'' +
                ", agentPhone='" + agentPhone + '\'' +
                ", pictures=" + pictures +
                '}';
    }
}
