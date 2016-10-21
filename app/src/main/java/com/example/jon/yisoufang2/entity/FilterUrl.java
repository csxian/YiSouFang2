package com.example.jon.yisoufang2.entity;

import android.text.TextUtils;

/**
 * 描述：
 */
public class FilterUrl {
    private volatile static FilterUrl filterUrl;

    private FilterUrl() {
    }

    public static FilterUrl instance() {
        if (filterUrl == null) {
            synchronized (FilterUrl.class) {
                if (filterUrl == null) {
                    filterUrl = new FilterUrl();
                }
            }
        }
        return filterUrl;
    }

    public String singleListPosition;
    public String doubleListLeft;
    public String doubleListRight;
    public String singleGridPosition;
    public String doubleGridTop;
    public String doubleGridBottom;

    public String rentRegion;
    public String rentPrice;
    public String rentHouseType;
    public String rentOrientation;
    public String rentAreas;


    public String secondHandRegion;
    public String secondHandPrice;
    public String secondHouseType;
    public String mOrientation;
    public String mAreas;
    public String mAge;
    public String mFloor;
    public String mFitment;





    public int position;
    public String positionTitle;

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        if (!TextUtils.isEmpty(singleListPosition)) {
            buffer.append("singleListPosition=");
            buffer.append(singleListPosition);
            buffer.append("\n");
        }


        if (!TextUtils.isEmpty(doubleListLeft)) {
            buffer.append("doubleListLeft=");
            buffer.append(doubleListLeft);
            buffer.append("\n");
        }

        if (!TextUtils.isEmpty(doubleListRight)) {
            buffer.append("doubleListRight=");
            buffer.append(doubleListRight);
            buffer.append("\n");
        }

        if (!TextUtils.isEmpty(singleGridPosition)) {
            buffer.append("singleGridPosition=");
            buffer.append(singleGridPosition);
            buffer.append("\n");
        }

        if (!TextUtils.isEmpty(doubleGridTop)) {
            buffer.append("doubleGridTop=");
            buffer.append(doubleGridTop);
            buffer.append("\n");
        }

        if (!TextUtils.isEmpty(doubleGridBottom)) {
            buffer.append("doubleGridBottom=");
            buffer.append(doubleGridBottom);
            buffer.append("\n");
        }

        if(!TextUtils.isEmpty(mOrientation)){
            buffer.append("朝向=");
            buffer.append(mOrientation);
            buffer.append("\n");
        }

        if(!TextUtils.isEmpty(mAreas)){
            buffer.append("面积=");
            buffer.append(mAreas);
            buffer.append("\n");
        }

        if(!TextUtils.isEmpty(mAge)){
            buffer.append("楼龄=");
            buffer.append(mAge);
            buffer.append("\n");
        }

        if(!TextUtils.isEmpty(mFloor)){
            buffer.append("楼层=");
            buffer.append(mFloor);
            buffer.append("\n");
        }

        if(!TextUtils.isEmpty(mFitment)){
            buffer.append("装修=");
            buffer.append(mFitment);
            buffer.append("\n");
        }

        return buffer.toString();
    }

    public void clear() {
        filterUrl = null;
    }


}