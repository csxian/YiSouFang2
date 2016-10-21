package com.example.jon.yisoufang2.bean;

import java.util.List;

/**
 * Created by Jon on 2016/10/18.
 */
public class MapMarkerResult {
    private String status;
    private int regionTag;
    private List<MapMarkerInfo> mapMarkerInfoList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRegionTag() {
        return regionTag;
    }

    public void setRegionTag(int regionTag) {
        this.regionTag = regionTag;
    }

    public List<MapMarkerInfo> getMapMarkerInfoList() {
        return mapMarkerInfoList;
    }

    public void setMapMarkerInfoList(List<MapMarkerInfo> mapMarkerInfoList) {
        this.mapMarkerInfoList = mapMarkerInfoList;
    }

    public static class MapMarkerInfo{
        private String regionName;
        private String lat;
        private String lon;
        private int count;

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    @Override
    public String toString() {
        return "MapMarkerResult{" +
                "status='" + status + '\'' +
                ", regionTag=" + regionTag +
                ", mapMarkerInfoList=" + mapMarkerInfoList +
                '}';
    }
}
