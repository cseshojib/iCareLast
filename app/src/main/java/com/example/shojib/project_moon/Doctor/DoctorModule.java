package com.example.shojib.project_moon.Doctor;

import java.io.Serializable;

public class DoctorModule implements Serializable {
    private static final long serialVersionUID = -7406082437623008161L;

    private long doctorId;
    private String doctorName;
    private String speciality;
    private String phone;
    private String address;
    private String appointmentDate;
    private String appointmentTime;
    private int reminder;
    private long userId;


    public DoctorModule(long doctorId, String doctorName, String speciality, String phone, String address, String appointmentDate, String appointmentTime, int reminder, long userId) {

        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.phone = phone;
        this.address = address;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reminder = reminder;
        this.userId = userId;

    }

    public DoctorModule(){

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public int getReminder() {
        return reminder;
    }

    public long getUserId() {
        return userId;
    }
}