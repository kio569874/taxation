package com.taxation.bean;

import java.io.Serializable;

public class UserAddress implements Serializable {
    private String addressId;

    private String addressProvince;

    private String addressCity;

    private String addressArea;

    private String addressText;

    private Integer addressAtime;

    private Boolean addressDefault;

    private String userId;

    private String userName;

    private String userPhone;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId == null ? null : addressId.trim();
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince == null ? null : addressProvince.trim();
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity == null ? null : addressCity.trim();
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea == null ? null : addressArea.trim();
    }

    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText == null ? null : addressText.trim();
    }

    public Integer getAddressAtime() {
        return addressAtime;
    }

    public void setAddressAtime(Integer addressAtime) {
        this.addressAtime = addressAtime;
    }

    public Boolean getAddressDefault() {
        return addressDefault;
    }

    public void setAddressDefault(Boolean addressDefault) {
        this.addressDefault = addressDefault;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }
}