package com.example.shojib.project_moon.Rbs;

import java.io.Serializable;

/**
 * Created by hasan on 11/23/15.
 */
public class RbsModule implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    private long rbsId;
    private float rbsUnit;
    private String rbsDate;
    private String rbsTime;
    private long userId;

    public RbsModule(long rbsId, float rbsUnit, String rbsDate, String rbsTime, long userId){
        this.rbsId = rbsId;
        this.rbsUnit = rbsUnit;
        this.rbsDate = rbsDate;
        this.rbsTime = rbsTime;
        this.userId = userId;
    }

    public RbsModule(){

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setRbsId(long rbsId) {
        this.rbsId = rbsId;
    }

    public void setRbsUnit(float rbsUnit) {
        this.rbsUnit = rbsUnit;
    }

    public void setRbsDate(String rbsDate) {
        this.rbsDate = rbsDate;
    }

    public void setRbsTime(String rbsTime) {
        this.rbsTime = rbsTime;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRbsId() {
        return rbsId;
    }

    public float getRbsUnit() {
        return rbsUnit;
    }

    public String getRbsDate() {
        return rbsDate;
    }

    public String getRbsTime() {
        return rbsTime;
    }

    public long getUserId() {
        return userId;
    }
}
