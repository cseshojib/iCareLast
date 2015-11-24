package com.example.shojib.project_moon.Vaccine.Vaccination;

import java.io.Serializable;

/**
 * Created by hasan on 11/23/15.
 */
public class VaccinationModule implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    private long vaccineId;
    private String vaccineName;
    private String vaccineReason;
    private long userId;
    private int reminder;


    public VaccinationModule(long vaccineId, String vaccineName, String vaccineReason, long userId, int reminder) {

        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
        this.vaccineReason=vaccineReason;
        this.userId=userId;
        this.reminder = reminder;
    }

    public VaccinationModule() {

    }

    public void setVaccineId(long vaccineId) {
        this.vaccineId = vaccineId;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public void setVaccineReason(String vaccineReason) {
        this.vaccineReason = vaccineReason;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setReminder(int reminder) {
        this.reminder = reminder;
    }

    public long getVaccineId() {
        return vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getVaccineReason() {
        return vaccineReason;
    }

    public long getUserId() {
        return userId;
    }

    public int getReminder() {
        return reminder;
    }
}
