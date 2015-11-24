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

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public void setReminder(int reminder) {
        this.reminder = reminder;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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