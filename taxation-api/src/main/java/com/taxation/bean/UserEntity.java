package com.taxation.bean;

import java.io.Serializable;
import java.util.Date;

public class UserEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4853287978342155935L;
	private String userId;
	
    private String userAccount;

    private String userPassword;

    private String userPhone;

    private String userName;

    private String userIdcard;

    private String userImage;

    private Integer userAge;

    private String userSex;

    private String userEmail;

    private String userWeixin;

    private String userQq;

    private String companyName;

    private String companyAddress;

    private String companyType;

    private String companyCode;

    private String companyImage;

    private String companyMobile;

    private String legalPersoon;

    private String legalPhone;

    private String legalIdNo;

    private String legalFrontalImage;

    private String legalOppositeImage;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;

    private String lastLoginIp;
    
    private String userMemberName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard == null ? null : userIdcard.trim();
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage == null ? null : userImage.trim();
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserWeixin() {
        return userWeixin;
    }

    public void setUserWeixin(String userWeixin) {
        this.userWeixin = userWeixin == null ? null : userWeixin.trim();
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq == null ? null : userQq.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    public String getCompanyImage() {
        return companyImage;
    }

    public void setCompanyImage(String companyImage) {
        this.companyImage = companyImage == null ? null : companyImage.trim();
    }

    public String getCompanyMobile() {
        return companyMobile;
    }

    public void setCompanyMobile(String companyMobile) {
        this.companyMobile = companyMobile == null ? null : companyMobile.trim();
    }

    public String getLegalPersoon() {
        return legalPersoon;
    }

    public void setLegalPersoon(String legalPersoon) {
        this.legalPersoon = legalPersoon == null ? null : legalPersoon.trim();
    }

    public String getLegalPhone() {
        return legalPhone;
    }

    public void setLegalPhone(String legalPhone) {
        this.legalPhone = legalPhone == null ? null : legalPhone.trim();
    }

    public String getLegalIdNo() {
        return legalIdNo;
    }

    public void setLegalIdNo(String legalIdNo) {
        this.legalIdNo = legalIdNo == null ? null : legalIdNo.trim();
    }

    public String getLegalFrontalImage() {
        return legalFrontalImage;
    }

    public void setLegalFrontalImage(String legalFrontalImage) {
        this.legalFrontalImage = legalFrontalImage == null ? null : legalFrontalImage.trim();
    }

    public String getLegalOppositeImage() {
        return legalOppositeImage;
    }

    public void setLegalOppositeImage(String legalOppositeImage) {
        this.legalOppositeImage = legalOppositeImage == null ? null : legalOppositeImage.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }
    
    public String getUserMemberName() {
        return userMemberName;
    }

    public void setUserMemberName(String userMemberName) {
        this.userMemberName = userMemberName == null ? null : userMemberName.trim();
    }
}