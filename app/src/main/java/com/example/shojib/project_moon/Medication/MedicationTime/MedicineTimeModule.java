package com.example.shojib.project_moon.Medication.MedicationTime;

import java.io.Serializable;

/**
 * Created by hasan on 11/23/15.
 */
public class MedicineTimeModule implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    private long medicineId;
    private String medicineTime;

    public MedicineTimeModule(long medicineId, String medicineTime){
        this.medicineId = medicineId;
        this.medicineTime = medicineTime;
    }

    public MedicineTimeModule() {
    }

    public void setMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }

    public void setMedicineTime(String medicineTime) {
        this.medicineTime = medicineTime;
    }

    public long getMedicineId() {
        return medicineId;
    }

    public String getMedicineTime() {
        return medicineTime;
    }
}
