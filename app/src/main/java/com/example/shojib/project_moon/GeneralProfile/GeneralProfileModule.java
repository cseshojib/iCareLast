package com.example.shojib.project_moon.GeneralProfile;

import java.io.Serializable;

/**
 * Created by Suuny on 11/23/2015.
 */
public class GeneralProfileModule implements Serializable {

    private static final long serilVersionUID=-7406082437623008161L;

   private long userId;
   private String profileName;
   private int age ;
   private String bloodGroup ;
   private String gender;
   private float height;
   private float weight;
   private float bloodPressure;
   private String disease;
    public GeneralProfileModule(){

    }

    public GeneralProfileModule(long userId, String profileName, int age, String bloodGroup, String gender, float height, float weight, float bloodPressure, String disease) {
        this.userId = userId;
        this.profileName = profileName;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bloodPressure = bloodPressure;
        this.disease = disease;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(float bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
