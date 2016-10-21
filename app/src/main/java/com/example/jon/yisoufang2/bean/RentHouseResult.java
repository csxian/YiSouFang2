package com.example.jon.yisoufang2.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jon on 2016/10/10.
 */
public class RentHouseResult {

    /**
     * status : 成功
     * currentPageNum : 1
     * pageSize : 12
     * totalRows : 12
     * totalPages : 2
     * rentInfoList : [{"houseNum":"GZZ0000000001","houseTitle":"南源花园 间隔方正 温馨舒适 安静宜居",
     * "commName":"南源花园","houseType":"1室1厅","houseArea":35,"houseOrientation":"西南",
     * "commRegionDetail":"南岸路","houseStorey":"低楼层","houseDecade":"1998","houseRental":2200,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000004","houseTitle":"同德花园 舒适两房 地铁上盖",
     * "commName":"同德花园","houseType":"2室1厅","houseArea":60,"houseOrientation":"南",
     * "commRegionDetail":"同德围","houseStorey":"中楼层","houseDecade":"1998","houseRental":1800,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000005","houseTitle":"同德花园 方正户型 采光一流",
     * "commName":"同德花园","houseType":"3室1厅","houseArea":101,"houseOrientation":"西",
     * "commRegionDetail":"同德围","houseStorey":"高楼层","houseDecade":"2003","houseRental":3000,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000002","houseTitle":"泽德花苑3房1厅 环境优美
     * 交通便利","commName":"泽德花园","houseType":"3室1厅 ","houseArea":75,"houseOrientation":"西",
     * "commRegionDetail":"同德围","houseStorey":"高楼层","houseDecade":"1998","houseRental":2499,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000003","houseTitle":"泽德花园大两房 双阳台 超舒服",
     * "commName":"泽德花园","houseType":"3室2厅","houseArea":108,"houseOrientation":"北",
     * "commRegionDetail":"同德围","houseStorey":"高楼层","houseDecade":"1998","houseRental":2900,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000006","houseTitle":"白云雅苑云雅大街 2室1厅
     * 2200元","commName":"白云雅苑云雅大街 ","houseType":"2室1厅","houseArea":60,"houseOrientation":"东南",
     * "commRegionDetail":"同德围","houseStorey":"高楼层","houseDecade":"2006","houseRental":2200,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000007","houseTitle":"白云雅苑 装修靓 通风采光好",
     * "commName":"白云雅苑云雅大街 ","houseType":"2室1厅","houseArea":70,"houseOrientation":"南",
     * "commRegionDetail":"同德围","houseStorey":"中楼层","houseDecade":"2006","houseRental":2500,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000008","houseTitle":"白云雅苑1房 装修新净 通风采光好",
     * "commName":"白云雅苑云雅大街 ","houseType":"1室1厅","houseArea":45,"houseOrientation":"南",
     * "commRegionDetail":"同德围","houseStorey":"中楼层","houseDecade":"2006","houseRental":1300,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000009","houseTitle":"白云花园 电梯三大房 实用采光好",
     * "commName":"白云花园","houseType":"3室1厅","houseArea":136,"houseOrientation":"东",
     * "commRegionDetail":"新市","houseStorey":"高楼层","houseDecade":"2003","houseRental":4300,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000010","houseTitle":"机场路 翠逸家园 实用3房",
     * "commName":"翠逸家园","houseType":"3室2厅","houseArea":103,"houseOrientation":"南",
     * "commRegionDetail":"机场路","houseStorey":"低楼层","houseDecade":"2000","houseRental":4500,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000011","houseTitle":"远景路 翠逸家园 家电齐全",
     * "commName":"翠逸家园","houseType":"3室1厅","houseArea":88,"houseOrientation":"东北",
     * "commRegionDetail":"机场路","houseStorey":"中楼层","houseDecade":"2000","houseRental":4500,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000012","houseTitle":"翠逸家园 望花园精装大两房
     * 家电齐全","commName":"翠逸家园","houseType":"2室1厅","houseArea":88,"houseOrientation":"北",
     * "commRegionDetail":"机场路","houseStorey":"低楼层","houseDecade":"2000","houseRental":4600,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000013","houseTitle":"大两房，阳台采光好",
     * "commName":"云顶花园","houseType":"2室1厅","houseArea":82,"houseOrientation":"北",
     * "commRegionDetail":"机场路","houseStorey":"中楼层","houseDecade":"1998","houseRental":3000,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000014","houseTitle":"云顶花园 精致一房 黄金地段",
     * "commName":"云顶花园","houseType":"1室1厅","houseArea":55,"houseOrientation":"西",
     * "commRegionDetail":"机场路","houseStorey":"中楼层","houseDecade":"1998","houseRental":2500,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000015","houseTitle":"云顶花园 家私电器齐 漂亮装修",
     * "commName":"云顶花园","houseType":"1室1厅","houseArea":38,"houseOrientation":"西",
     * "commRegionDetail":"机场路","houseStorey":"高楼层","houseDecade":"1998","houseRental":1700,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000016","houseTitle":"机场西路万方园 精致装修电梯房
     * 拎包入住","commName":"万方园","houseType":"4室1厅","houseArea":103,"houseOrientation":"北",
     * "commRegionDetail":"机场路","houseStorey":"低楼层","houseDecade":"1999","houseRental":4500,
     * "pictureUrl":"images/1.jpg"},{"houseNum":"GZZ0000000017","houseTitle":"万方园 高层厅出阳台两房
     * 家私电器齐全","commName":"万方园","houseType":"2室2厅","houseArea":60,"houseOrientation":"东北",
     * "commRegionDetail":"机场路","houseStorey":"中楼层","houseDecade":"1999","houseRental":3500,
     * "pictureUrl":"images/1.jpg"}]
     */

    private String status;
    private int currentPageNum;
    private int pageSize;
    private int totalRows;
    private int totalPages;
    /**
     * houseNum : GZZ0000000001
     * houseTitle : 南源花园 间隔方正 温馨舒适 安静宜居
     * commName : 南源花园
     * houseType : 1室1厅
     * houseArea : 35
     * houseOrientation : 西南
     * commRegionDetail : 南岸路
     * houseStorey : 低楼层
     * houseDecade : 1998
     * houseRental : 2200
     * pictureUrl : images/1.jpg
     */

    private List<RentInfoListBean> rentInfoList;

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

    public List<RentInfoListBean> getRentInfoList() {
        return rentInfoList;
    }

    public void setRentInfoList(List<RentInfoListBean> rentInfoList) {
        this.rentInfoList = rentInfoList;
    }

    public static class RentInfoListBean implements Serializable {
        private String houseNum;
        private String houseTitle;
        private String commName;
        private String houseType;
        private int houseArea;
        private String houseOrientation;
        private String commRegionDetail;
        private String houseStorey;
        private String houseDecade;
        private int houseRental;
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

        public int getHouseRental() {
            return houseRental;
        }

        public void setHouseRental(int houseRental) {
            this.houseRental = houseRental;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }
    }
}
