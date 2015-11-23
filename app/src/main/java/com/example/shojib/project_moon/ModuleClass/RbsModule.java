package com.example.shojib.project_moon.ModuleClass;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
