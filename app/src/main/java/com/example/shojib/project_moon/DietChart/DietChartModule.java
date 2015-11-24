package com.example.shojib.project_moon.DietChart;

import java.io.Serializable;

/**
 * Created by hasan on 11/24/15.
 */
public class DietChartModule implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    private long dietId;
    private String foodItem;

    public DietChartModule(long dietId, String foodItem){
        this.dietId = dietId;
        this.foodItem = foodItem;
    }

    public DietChartModule(){

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setDietId(long dietId) {
        this.dietId = dietId;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public long getDietId() {
        return dietId;
    }

    public String getFoodItem() {
        return foodItem;
    }

}
