package com.example.shojib.project_moon.ModuleClass;

import java.io.Serializable;

public class MedicationModule implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    private long medicineId;
    private String medicineName;
    private String medicineReason;
    private long userId;
    private int reminder;


    public MedicationModule(long medicineId, String medicineName, String medicineReason, long userId, int reminder) {

        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicineReason=medicineReason;
        this.userId=userId;
        this.reminder = reminder;

    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getMedicineId() {
        return medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String getMedicineReason() {
        return medicineReason;
    }

    public long getUserId() {
        return userId;
    }

    public int getReminder() {
        return reminder;
    }
}