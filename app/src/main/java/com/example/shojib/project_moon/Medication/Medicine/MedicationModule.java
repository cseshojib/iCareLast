package com.example.shojib.project_moon.Medication.Medicine;

import java.io.Serializable;

public class MedicationModule implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    private long medicineId;
    private String medicineName;
    private String medicineReason;
    private long userId;
    private String reminder;


    public MedicationModule(long medicineId, String medicineName, String medicineReason, long userId, String reminder) {

        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicineReason=medicineReason;
        this.userId=userId;
        this.reminder = reminder;

    }

    public MedicationModule() {
    }

    public void setMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setMedicineReason(String medicineReason) {
        this.medicineReason = medicineReason;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
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

    public String getReminder() {
        return reminder;
    }
}