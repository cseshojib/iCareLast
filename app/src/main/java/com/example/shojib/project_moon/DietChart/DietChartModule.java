package com.example.shojib.project_moon.DietChart;

import java.io.Serializable;

/**
 * Created by hasan on 11/24/15.
 */
public class DietChartModule implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    private long dietId;
    private String foodItem;
    private long userId;

    public DietChartModule(long dietId, String foodItem, long userId){
        this.dietId = dietId;
        this.foodItem = foodItem;
        this.userId = userId;
    }

    public DietChartModule(){

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getDietId() {
        return dietId;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public long getUserId() {
        return userId;
    }
}
