package com.example.shojib.project_moon.ModuleClass;

import java.io.Serializable;

/**
 * Created by hasan on 11/23/15.
 */
public class VaccineDateModule implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    private long vaccineId;
    private String vaccineDate;

    public VaccineDateModule(long vaccineId, String vaccineDate){
        this.vaccineId = vaccineId;
        this.vaccineDate = vaccineDate;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getVaccineId() {
        return vaccineId;
    }

    public String getVaccineDate() {
        return vaccineDate;
    }
}
